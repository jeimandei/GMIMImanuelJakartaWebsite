package com.jeimandei.imanuelbytes.media.controller;

import com.jeimandei.imanuelbytes.common.exception.ResourceNotFoundException;
import com.jeimandei.imanuelbytes.media.dto.LivestreamRequest;
import com.jeimandei.imanuelbytes.media.dto.LivestreamResponse;
import com.jeimandei.imanuelbytes.media.entity.Livestream;
import com.jeimandei.imanuelbytes.media.repository.LivestreamRepository;
import jakarta.validation.Valid;
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
@RequestMapping("/api/livestreams")
public class LivestreamController {

    private final LivestreamRepository livestreamRepository;

    public LivestreamController(LivestreamRepository livestreamRepository) {
        this.livestreamRepository = livestreamRepository;
    }

    @GetMapping("/active")
    public LivestreamResponse getActive() {
        return livestreamRepository.findFirstByActiveTrueOrderByScheduledStartDesc()
                .map(this::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("No active livestream"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LivestreamResponse create(@Valid @RequestBody LivestreamRequest request) {
        Livestream livestream = new Livestream();
        apply(livestream, request);
        return toResponse(livestreamRepository.save(livestream));
    }

    @PutMapping("/{id}")
    public LivestreamResponse update(@PathVariable Long id, @Valid @RequestBody LivestreamRequest request) {
        Livestream livestream = livestreamRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Livestream not found"));
        apply(livestream, request);
        return toResponse(livestreamRepository.save(livestream));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        livestreamRepository.deleteById(id);
    }

    private void apply(Livestream livestream, LivestreamRequest request) {
        livestream.setTitle(request.getTitle());
        livestream.setYoutubeEmbedUrl(request.getYoutubeEmbedUrl());
        livestream.setDescription(request.getDescription());
        livestream.setActive(request.isActive());
        livestream.setScheduledStart(request.getScheduledStart());
        livestream.setUpdatedAt(java.time.LocalDateTime.now());
    }

    private LivestreamResponse toResponse(Livestream livestream) {
        LivestreamResponse response = new LivestreamResponse();
        response.setId(livestream.getId());
        response.setTitle(livestream.getTitle());
        response.setYoutubeEmbedUrl(livestream.getYoutubeEmbedUrl());
        response.setDescription(livestream.getDescription());
        response.setActive(livestream.isActive());
        response.setScheduledStart(livestream.getScheduledStart());
        return response;
    }
}
