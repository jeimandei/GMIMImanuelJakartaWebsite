package com.jeimandei.imanuelbytes.event.service.impl;

import com.jeimandei.imanuelbytes.common.enums.EventStatus;
import com.jeimandei.imanuelbytes.common.exception.ResourceNotFoundException;
import com.jeimandei.imanuelbytes.event.dto.EventRequest;
import com.jeimandei.imanuelbytes.event.dto.EventResponse;
import com.jeimandei.imanuelbytes.event.entity.Event;
import com.jeimandei.imanuelbytes.event.repository.EventRepository;
import com.jeimandei.imanuelbytes.event.service.EventService;
import java.time.LocalDateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<EventResponse> getEvents(Pageable pageable) {
        return eventRepository.findAll(pageable).map(this::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<EventResponse> getUpcoming(Pageable pageable) {
        return eventRepository.findByEventStartGreaterThanEqual(LocalDateTime.now(), pageable).map(this::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public EventResponse getById(Long id) {
        return toResponse(findById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public EventResponse getBySlug(String slug) {
        return toResponse(eventRepository.findBySlug(slug).orElseThrow(() -> new ResourceNotFoundException("Event not found")));
    }

    @Override
    public EventResponse create(EventRequest request) {
        Event event = new Event();
        apply(event, request);
        return toResponse(eventRepository.save(event));
    }

    @Override
    public EventResponse update(Long id, EventRequest request) {
        Event event = findById(id);
        apply(event, request);
        event.setUpdatedAt(LocalDateTime.now());
        return toResponse(eventRepository.save(event));
    }

    @Override
    public void delete(Long id) {
        eventRepository.delete(findById(id));
    }

    private Event findById(Long id) {
        return eventRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Event not found"));
    }

    private void apply(Event event, EventRequest request) {
        event.setTitle(request.getTitle());
        event.setSlug(request.getSlug());
        event.setDescription(request.getDescription());
        event.setLocation(request.getLocation());
        event.setEventStart(request.getEventStart());
        event.setEventEnd(request.getEventEnd());
        event.setImageUrl(request.getImageUrl());
        event.setFeatured(request.isFeatured());
        event.setStatus(request.getStatus() == null ? EventStatus.DRAFT : request.getStatus());
    }

    private EventResponse toResponse(Event event) {
        EventResponse response = new EventResponse();
        response.setId(event.getId());
        response.setTitle(event.getTitle());
        response.setSlug(event.getSlug());
        response.setDescription(event.getDescription());
        response.setLocation(event.getLocation());
        response.setEventStart(event.getEventStart());
        response.setEventEnd(event.getEventEnd());
        response.setImageUrl(event.getImageUrl());
        response.setStatus(event.getStatus());
        response.setFeatured(event.isFeatured());
        return response;
    }
}
