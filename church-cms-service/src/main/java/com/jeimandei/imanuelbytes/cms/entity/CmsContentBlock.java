package com.jeimandei.imanuelbytes.cms.entity;

import com.jeimandei.imanuelbytes.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "cms_content_blocks")
public class CmsContentBlock extends BaseEntity {

    @ManyToOne(optional = false)
    @JoinColumn(name = "page_id", nullable = false)
    private CmsPage page;

    @Column(nullable = false, length = 40)
    private String blockType;

    @Column(length = 200)
    private String title;

    @Column(length = 4000)
    private String body;

    @Column(length = 255)
    private String imageUrl;

    @Column(nullable = false)
    private Integer displayOrder = 0;

    @Column(nullable = false)
    private boolean active = true;

    public CmsPage getPage() { return page; }
    public void setPage(CmsPage page) { this.page = page; }
    public String getBlockType() { return blockType; }
    public void setBlockType(String blockType) { this.blockType = blockType; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getBody() { return body; }
    public void setBody(String body) { this.body = body; }
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public Integer getDisplayOrder() { return displayOrder; }
    public void setDisplayOrder(Integer displayOrder) { this.displayOrder = displayOrder; }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}
