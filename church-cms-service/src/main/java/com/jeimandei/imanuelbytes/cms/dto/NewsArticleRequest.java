package com.jeimandei.imanuelbytes.cms.dto;

import com.jeimandei.imanuelbytes.common.enums.ContentStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class NewsArticleRequest {

    @NotBlank
    @Size(max = 220)
    private String title;

    @NotBlank
    @Size(max = 240)
    private String slug;

    @NotBlank
    private String content;

    @Size(max = 1000)
    private String excerpt;

    @Size(max = 255)
    private String imageUrl;

    private Long authorId;
    private ContentStatus status;

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
}
