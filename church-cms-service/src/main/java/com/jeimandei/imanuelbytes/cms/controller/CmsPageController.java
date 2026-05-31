package com.jeimandei.imanuelbytes.cms.controller;

import com.jeimandei.imanuelbytes.cms.dto.CmsPageRequest;
import com.jeimandei.imanuelbytes.cms.dto.CmsPageResponse;
import com.jeimandei.imanuelbytes.cms.service.CmsPageService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cms/pages")
public class CmsPageController {

    private final CmsPageService cmsPageService;

    public CmsPageController(CmsPageService cmsPageService) {
        this.cmsPageService = cmsPageService;
    }

    @GetMapping
    public Page<CmsPageResponse> getPages(Pageable pageable) {
        return cmsPageService.getPages(pageable);
    }

    @GetMapping("/{id}")
    public CmsPageResponse getPage(@PathVariable Long id) {
        return cmsPageService.getById(id);
    }

    @GetMapping("/slug/{slug}")
    public CmsPageResponse getBySlug(@PathVariable String slug) {
        return cmsPageService.getBySlug(slug);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CmsPageResponse create(@Valid @RequestBody CmsPageRequest request) {
        return cmsPageService.create(request);
    }

    @PutMapping("/{id}")
    public CmsPageResponse update(@PathVariable Long id, @Valid @RequestBody CmsPageRequest request) {
        return cmsPageService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        cmsPageService.delete(id);
    }

    @PutMapping("/{id}/publish")
    public CmsPageResponse publish(@PathVariable Long id) {
        return cmsPageService.publish(id);
    }

    @PutMapping("/{id}/unpublish")
    public CmsPageResponse unpublish(@PathVariable Long id) {
        return cmsPageService.unpublish(id);
    }
}
