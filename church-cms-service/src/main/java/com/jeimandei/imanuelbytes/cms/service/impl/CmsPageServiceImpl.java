package com.jeimandei.imanuelbytes.cms.service.impl;

import com.jeimandei.imanuelbytes.cms.dto.CmsPageRequest;
import com.jeimandei.imanuelbytes.cms.dto.CmsPageResponse;
import com.jeimandei.imanuelbytes.cms.entity.CmsPage;
import com.jeimandei.imanuelbytes.cms.repository.CmsPageRepository;
import com.jeimandei.imanuelbytes.cms.service.CmsPageService;
import com.jeimandei.imanuelbytes.common.enums.ContentStatus;
import com.jeimandei.imanuelbytes.common.exception.ResourceNotFoundException;
import java.time.LocalDateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CmsPageServiceImpl implements CmsPageService {

    private final CmsPageRepository cmsPageRepository;

    public CmsPageServiceImpl(CmsPageRepository cmsPageRepository) {
        this.cmsPageRepository = cmsPageRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CmsPageResponse> getPages(Pageable pageable) {
        return cmsPageRepository.findAll(pageable).map(this::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public CmsPageResponse getById(Long id) {
        return toResponse(findById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public CmsPageResponse getBySlug(String slug) {
        return toResponse(cmsPageRepository.findBySlug(slug).orElseThrow(() -> new ResourceNotFoundException("CMS page not found")));
    }

    @Override
    public CmsPageResponse create(CmsPageRequest request) {
        CmsPage page = new CmsPage();
        apply(page, request);
        return toResponse(cmsPageRepository.save(page));
    }

    @Override
    public CmsPageResponse update(Long id, CmsPageRequest request) {
        CmsPage page = findById(id);
        apply(page, request);
        return toResponse(cmsPageRepository.save(page));
    }

    @Override
    public void delete(Long id) {
        cmsPageRepository.delete(findById(id));
    }

    @Override
    public CmsPageResponse publish(Long id) {
        CmsPage page = findById(id);
        page.setStatus(ContentStatus.PUBLISHED);
        page.setPublishedAt(LocalDateTime.now());
        return toResponse(cmsPageRepository.save(page));
    }

    @Override
    public CmsPageResponse unpublish(Long id) {
        CmsPage page = findById(id);
        page.setStatus(ContentStatus.DRAFT);
        page.setPublishedAt(null);
        return toResponse(cmsPageRepository.save(page));
    }

    private CmsPage findById(Long id) {
        return cmsPageRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("CMS page not found"));
    }

    private void apply(CmsPage page, CmsPageRequest request) {
        page.setTitle(request.getTitle());
        page.setSlug(request.getSlug());
        page.setPageType(request.getPageType());
        page.setContent(request.getContent());
        page.setMetaTitle(request.getMetaTitle());
        page.setMetaDescription(request.getMetaDescription());
        page.setStatus(request.getStatus() == null ? ContentStatus.DRAFT : request.getStatus());
    }

    private CmsPageResponse toResponse(CmsPage page) {
        CmsPageResponse response = new CmsPageResponse();
        response.setId(page.getId());
        response.setTitle(page.getTitle());
        response.setSlug(page.getSlug());
        response.setPageType(page.getPageType());
        response.setContent(page.getContent());
        response.setMetaTitle(page.getMetaTitle());
        response.setMetaDescription(page.getMetaDescription());
        response.setStatus(page.getStatus());
        response.setPublishedAt(page.getPublishedAt());
        return response;
    }
}
