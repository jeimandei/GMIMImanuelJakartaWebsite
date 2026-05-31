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
@Table(name = "cms_pages")
public class CmsPage extends BaseEntity {

    @Column(nullable = false, length = 180)
    private String title;

    @Column(nullable = false, unique = true, length = 200)
    private String slug;

    @Column(nullable = false, length = 40)
    private String pageType;

    @Column(nullable = false, length = 4000)
    private String content;

    @Column(length = 200)
    private String metaTitle;

    @Column(length = 500)
    private String metaDescription;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 16)
    private ContentStatus status = ContentStatus.DRAFT;

    @Column
    private LocalDateTime publishedAt;

    @Column(length = 80)
    private String createdBy;

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getSlug() { return slug; }
    public void setSlug(String slug) { this.slug = slug; }
    public String getPageType() { return pageType; }
    public void setPageType(String pageType) { this.pageType = pageType; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public String getMetaTitle() { return metaTitle; }
    public void setMetaTitle(String metaTitle) { this.metaTitle = metaTitle; }
    public String getMetaDescription() { return metaDescription; }
    public void setMetaDescription(String metaDescription) { this.metaDescription = metaDescription; }
    public ContentStatus getStatus() { return status; }
    public void setStatus(ContentStatus status) { this.status = status; }
    public LocalDateTime getPublishedAt() { return publishedAt; }
    public void setPublishedAt(LocalDateTime publishedAt) { this.publishedAt = publishedAt; }
    public String getCreatedBy() { return createdBy; }
    public void setCreatedBy(String createdBy) { this.createdBy = createdBy; }
}
