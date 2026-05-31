package com.jeimandei.imanuelbytes.media.controller;

import com.jeimandei.imanuelbytes.common.exception.ResourceNotFoundException;
import com.jeimandei.imanuelbytes.media.dto.SermonRequest;
import com.jeimandei.imanuelbytes.media.dto.SermonResponse;
import com.jeimandei.imanuelbytes.media.entity.Sermon;
import com.jeimandei.imanuelbytes.media.repository.SermonRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
@RequestMapping("/api/sermons")
public class SermonController {

    private final SermonRepository sermonRepository;

    public SermonController(SermonRepository sermonRepository) {
        this.sermonRepository = sermonRepository;
    }

    @GetMapping
    public Page<SermonResponse> getAll(Pageable pageable) {
        return sermonRepository.findAllByOrderBySermonDateDesc(pageable).map(this::toResponse);
    }

    @GetMapping("/{id}")
    public SermonResponse getById(@PathVariable Long id) {
        return toResponse(sermonRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Sermon not found")));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SermonResponse create(@Valid @RequestBody SermonRequest request) {
        Sermon sermon = new Sermon();
        apply(sermon, request);
        return toResponse(sermonRepository.save(sermon));
    }

    @PutMapping("/{id}")
    public SermonResponse update(@PathVariable Long id, @Valid @RequestBody SermonRequest request) {
        Sermon sermon = sermonRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Sermon not found"));
        apply(sermon, request);
        return toResponse(sermonRepository.save(sermon));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        sermonRepository.deleteById(id);
    }

    private void apply(Sermon sermon, SermonRequest request) {
        sermon.setTitle(request.getTitle());
        sermon.setSpeaker(request.getSpeaker());
        sermon.setSermonDate(request.getSermonDate());
        sermon.setDescription(request.getDescription());
        sermon.setYoutubeUrl(request.getYoutubeUrl());
        sermon.setAudioUrl(request.getAudioUrl());
        sermon.setScriptureReference(request.getScriptureReference());
        sermon.setSeriesName(request.getSeriesName());
        sermon.setUpdatedAt(java.time.LocalDateTime.now());
    }

    private SermonResponse toResponse(Sermon sermon) {
        SermonResponse response = new SermonResponse();
        response.setId(sermon.getId());
        response.setTitle(sermon.getTitle());
        response.setSpeaker(sermon.getSpeaker());
        response.setSermonDate(sermon.getSermonDate());
        response.setDescription(sermon.getDescription());
        response.setYoutubeUrl(sermon.getYoutubeUrl());
        response.setAudioUrl(sermon.getAudioUrl());
        response.setScriptureReference(sermon.getScriptureReference());
        response.setSeriesName(sermon.getSeriesName());
        return response;
    }
}
