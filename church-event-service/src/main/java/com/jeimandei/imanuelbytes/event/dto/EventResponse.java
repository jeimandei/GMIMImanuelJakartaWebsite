package com.jeimandei.imanuelbytes.event.dto;

import com.jeimandei.imanuelbytes.common.enums.EventStatus;
import java.time.LocalDateTime;

public class EventResponse {

    private Long id;
    private String title;
    private String slug;
    private String description;
    private String location;
    private LocalDateTime eventStart;
    private LocalDateTime eventEnd;
    private String imageUrl;
    private EventStatus status;
    private boolean featured;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getSlug() { return slug; }
    public void setSlug(String slug) { this.slug = slug; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public LocalDateTime getEventStart() { return eventStart; }
    public void setEventStart(LocalDateTime eventStart) { this.eventStart = eventStart; }
    public LocalDateTime getEventEnd() { return eventEnd; }
    public void setEventEnd(LocalDateTime eventEnd) { this.eventEnd = eventEnd; }
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public EventStatus getStatus() { return status; }
    public void setStatus(EventStatus status) { this.status = status; }
    public boolean isFeatured() { return featured; }
    public void setFeatured(boolean featured) { this.featured = featured; }
}
