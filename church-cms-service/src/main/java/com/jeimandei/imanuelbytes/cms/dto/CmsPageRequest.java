package com.jeimandei.imanuelbytes.cms.dto;

import com.jeimandei.imanuelbytes.common.enums.ContentStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CmsPageRequest {

    @NotBlank
    @Size(max = 180)
    private String title;

    @NotBlank
    @Size(max = 200)
    private String slug;

    @NotBlank
    private String pageType;

    @NotBlank
    private String content;

    private String metaTitle;
    private String metaDescription;
    private ContentStatus status;

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
}
