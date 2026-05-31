package com.jeimandei.imanuelbytes.event.entity;

import com.jeimandei.imanuelbytes.common.entity.BaseEntity;
import com.jeimandei.imanuelbytes.common.enums.EventStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "events")
public class Event {

    @jakarta.persistence.Id
    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(nullable = false, unique = true, length = 220)
    private String slug;

    @Column(nullable = false, length = 4000)
    private String description;

    @Column(length = 255)
    private String location;

    @Column(nullable = false)
    private LocalDateTime eventStart;

    @Column
    private LocalDateTime eventEnd;

    @Column(length = 255)
    private String imageUrl;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 16)
    private EventStatus status = EventStatus.DRAFT;

    @Column(nullable = false)
    private boolean featured;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();

    public Long getId() { return id; }
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
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
