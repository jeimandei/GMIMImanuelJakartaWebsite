package com.jeimandei.imanuelbytes.media.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "sermons")
public class Sermon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(nullable = false, length = 120)
    private String speaker;

    @Column(nullable = false)
    private LocalDate sermonDate;

    @Column(length = 4000)
    private String description;

    @Column(length = 255)
    private String youtubeUrl;

    @Column(length = 255)
    private String audioUrl;

    @Column(length = 255)
    private String scriptureReference;

    @Column(length = 120)
    private String seriesName;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getSpeaker() { return speaker; }
    public void setSpeaker(String speaker) { this.speaker = speaker; }
    public LocalDate getSermonDate() { return sermonDate; }
    public void setSermonDate(LocalDate sermonDate) { this.sermonDate = sermonDate; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getYoutubeUrl() { return youtubeUrl; }
    public void setYoutubeUrl(String youtubeUrl) { this.youtubeUrl = youtubeUrl; }
    public String getAudioUrl() { return audioUrl; }
    public void setAudioUrl(String audioUrl) { this.audioUrl = audioUrl; }
    public String getScriptureReference() { return scriptureReference; }
    public void setScriptureReference(String scriptureReference) { this.scriptureReference = scriptureReference; }
    public String getSeriesName() { return seriesName; }
    public void setSeriesName(String seriesName) { this.seriesName = seriesName; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
