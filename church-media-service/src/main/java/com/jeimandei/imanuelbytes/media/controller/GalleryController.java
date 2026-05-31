package com.jeimandei.imanuelbytes.media.controller;

import com.jeimandei.imanuelbytes.common.exception.ResourceNotFoundException;
import com.jeimandei.imanuelbytes.media.dto.GalleryItemRequest;
import com.jeimandei.imanuelbytes.media.dto.GalleryItemResponse;
import com.jeimandei.imanuelbytes.media.entity.GalleryItem;
import com.jeimandei.imanuelbytes.media.repository.GalleryItemRepository;
import jakarta.validation.Valid;
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
@RequestMapping("/api/gallery")
public class GalleryController {

    private final GalleryItemRepository galleryItemRepository;

    public GalleryController(GalleryItemRepository galleryItemRepository) {
        this.galleryItemRepository = galleryItemRepository;
    }

    @GetMapping
    public List<GalleryItemResponse> getAll() {
        return galleryItemRepository.findAll().stream().map(this::toResponse).toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GalleryItemResponse create(@Valid @RequestBody GalleryItemRequest request) {
        GalleryItem item = new GalleryItem();
        apply(item, request);
        return toResponse(galleryItemRepository.save(item));
    }

    @PutMapping("/{id}")
    public GalleryItemResponse update(@PathVariable Long id, @Valid @RequestBody GalleryItemRequest request) {
        GalleryItem item = galleryItemRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Gallery item not found"));
        apply(item, request);
        return toResponse(galleryItemRepository.save(item));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        galleryItemRepository.deleteById(id);
    }

    private void apply(GalleryItem item, GalleryItemRequest request) {
        item.setTitle(request.getTitle());
        item.setDescription(request.getDescription());
        item.setImageUrl(request.getImageUrl());
        item.setAlbumName(request.getAlbumName());
        item.setDisplayOrder(request.getDisplayOrder() == null ? 0 : request.getDisplayOrder());
        item.setActive(request.isActive());
    }

    private GalleryItemResponse toResponse(GalleryItem item) {
        GalleryItemResponse response = new GalleryItemResponse();
        response.setId(item.getId());
        response.setTitle(item.getTitle());
        response.setDescription(item.getDescription());
        response.setImageUrl(item.getImageUrl());
        response.setAlbumName(item.getAlbumName());
        response.setDisplayOrder(item.getDisplayOrder());
        response.setActive(item.isActive());
        return response;
    }
}
