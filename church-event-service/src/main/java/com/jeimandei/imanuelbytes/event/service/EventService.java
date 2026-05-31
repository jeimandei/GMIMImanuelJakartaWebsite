package com.jeimandei.imanuelbytes.event.service;

import com.jeimandei.imanuelbytes.event.dto.EventRequest;
import com.jeimandei.imanuelbytes.event.dto.EventResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EventService {

    Page<EventResponse> getEvents(Pageable pageable);

    Page<EventResponse> getUpcoming(Pageable pageable);

    EventResponse getById(Long id);

    EventResponse getBySlug(String slug);

    EventResponse create(EventRequest request);

    EventResponse update(Long id, EventRequest request);

    void delete(Long id);
}
