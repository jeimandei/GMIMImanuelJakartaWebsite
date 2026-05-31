package com.jeimandei.imanuelbytes.cms.entity;

import com.jeimandei.imanuelbytes.common.entity.BaseEntity;
import com.jeimandei.imanuelbytes.common.enums.ContentStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "news_articles")
public class NewsArticle extends BaseEntity {

    @Column(nullable = false, length = 220)
    private String title;

    @Column(nullable = false, unique = true, length = 240)
    private String slug;

    @Column(nullable = false, length = 4000)
    private String content;

    @Column(length = 1000)
    private String excerpt;

    @Column(length = 255)
    private String imageUrl;

    @Column
    private Long authorId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 16)
    private ContentStatus status = ContentStatus.DRAFT;

    @Column
    private LocalDateTime publishedAt;

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getSlug() { return slug; }
    public void setSlug(String slug) { this.slug = slug; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public String getExcerpt() { return excerpt; }
    public void setExcerpt(String excerpt) { this.excerpt = excerpt; }
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public Long getAuthorId() { return authorId; }
    public void setAuthorId(Long authorId) { this.authorId = authorId; }
    public ContentStatus getStatus() { return status; }
    public void setStatus(ContentStatus status) { this.status = status; }
    public LocalDateTime getPublishedAt() { return publishedAt; }
    public void setPublishedAt(LocalDateTime publishedAt) { this.publishedAt = publishedAt; }
}
