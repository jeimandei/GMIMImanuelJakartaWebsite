package com.jeimandei.imanuelbytes.interaction.controller;

import com.jeimandei.imanuelbytes.common.enums.RequestStatus;
import com.jeimandei.imanuelbytes.common.exception.ResourceNotFoundException;
import com.jeimandei.imanuelbytes.interaction.dto.PrayerRequestInput;
import com.jeimandei.imanuelbytes.interaction.entity.PrayerRequest;
import com.jeimandei.imanuelbytes.interaction.repository.PrayerRequestRepository;
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
@RequestMapping("/api/prayer-requests")
public class PrayerRequestController {

    private final PrayerRequestRepository prayerRequestRepository;

    public PrayerRequestController(PrayerRequestRepository prayerRequestRepository) {
        this.prayerRequestRepository = prayerRequestRepository;
    }

    @PostMapping
    public PrayerRequest create(@Valid @RequestBody PrayerRequestInput input) {
        PrayerRequest request = new PrayerRequest();
        request.setName(input.getName());
        request.setEmail(input.getEmail());
        request.setPhone(input.getPhone());
        request.setMessage(input.getMessage());
        request.setConfidential(input.isConfidential());
        return prayerRequestRepository.save(request);
    }

    @GetMapping
    public List<PrayerRequest> list() {
        return prayerRequestRepository.findAll();
    }

    @PutMapping("/{id}/status")
    public PrayerRequest updateStatus(@PathVariable Long id, @RequestParam RequestStatus status) {
        PrayerRequest request = prayerRequestRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Prayer request not found"));
        request.setStatus(status);
        return prayerRequestRepository.save(request);
    }
}
