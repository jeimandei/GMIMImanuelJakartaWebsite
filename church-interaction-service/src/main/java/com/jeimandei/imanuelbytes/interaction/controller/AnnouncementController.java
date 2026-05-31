package com.jeimandei.imanuelbytes.interaction.controller;

import com.jeimandei.imanuelbytes.common.exception.ResourceNotFoundException;
import com.jeimandei.imanuelbytes.interaction.dto.AnnouncementInput;
import com.jeimandei.imanuelbytes.interaction.entity.Announcement;
import com.jeimandei.imanuelbytes.interaction.repository.AnnouncementRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/announcements")
public class AnnouncementController {

    private final AnnouncementRepository announcementRepository;

    public AnnouncementController(AnnouncementRepository announcementRepository) {
        this.announcementRepository = announcementRepository;
    }

    @GetMapping
    public List<Announcement> list() {
        return announcementRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Announcement create(@RequestBody AnnouncementInput input) {
        Announcement announcement = new Announcement();
        apply(announcement, input);
        return announcementRepository.save(announcement);
    }

    @PutMapping("/{id}")
    public Announcement update(@PathVariable Long id, @RequestBody AnnouncementInput input) {
        Announcement announcement = announcementRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Announcement not found"));
        apply(announcement, input);
        announcement.setUpdatedAt(LocalDateTime.now());
        return announcementRepository.save(announcement);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        announcementRepository.deleteById(id);
    }

    private void apply(Announcement announcement, AnnouncementInput input) {
        announcement.setTitle(input.getTitle());
        announcement.setMessage(input.getMessage());
        announcement.setStartDate(input.getStartDate());
        announcement.setEndDate(input.getEndDate());
        announcement.setActive(input.isActive());
        announcement.setPriority(input.getPriority() == null ? 0 : input.getPriority());
    }
}
