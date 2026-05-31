package com.jeimandei.imanuelbytes.cms.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CmsMenuRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String url;

    private Integer displayOrder;

    @NotNull
    private Boolean active;

    private String menuGroup;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }
    public Integer getDisplayOrder() { return displayOrder; }
    public void setDisplayOrder(Integer displayOrder) { this.displayOrder = displayOrder; }
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
    public String getMenuGroup() { return menuGroup; }
    public void setMenuGroup(String menuGroup) { this.menuGroup = menuGroup; }
}
