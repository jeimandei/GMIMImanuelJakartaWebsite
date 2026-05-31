package com.jeimandei.imanuelbytes.interaction.controller;

import com.jeimandei.imanuelbytes.common.enums.RequestStatus;
import com.jeimandei.imanuelbytes.common.exception.ResourceNotFoundException;
import com.jeimandei.imanuelbytes.interaction.dto.ContactMessageInput;
import com.jeimandei.imanuelbytes.interaction.entity.ContactMessage;
import com.jeimandei.imanuelbytes.interaction.repository.ContactMessageRepository;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/contact-messages")
public class ContactMessageController {

    private final ContactMessageRepository contactMessageRepository;

    public ContactMessageController(ContactMessageRepository contactMessageRepository) {
        this.contactMessageRepository = contactMessageRepository;
    }

    @PostMapping
    public ContactMessage create(@Valid @RequestBody ContactMessageInput input) {
        ContactMessage message = new ContactMessage();
        message.setName(input.getName());
        message.setEmail(input.getEmail());
        message.setSubject(input.getSubject());
        message.setMessage(input.getMessage());
        return contactMessageRepository.save(message);
    }

    @GetMapping
    public List<ContactMessage> list() {
        return contactMessageRepository.findAll();
    }

    @PutMapping("/{id}/status")
    public ContactMessage updateStatus(@PathVariable Long id, @RequestParam RequestStatus status) {
        ContactMessage message = contactMessageRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Contact message not found"));
        message.setStatus(status);
        return contactMessageRepository.save(message);
    }
}
