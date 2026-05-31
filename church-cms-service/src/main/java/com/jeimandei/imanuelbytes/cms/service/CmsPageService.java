package com.jeimandei.imanuelbytes.cms.service;

import com.jeimandei.imanuelbytes.cms.dto.CmsPageRequest;
import com.jeimandei.imanuelbytes.cms.dto.CmsPageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CmsPageService {

    Page<CmsPageResponse> getPages(Pageable pageable);

    CmsPageResponse getById(Long id);

    CmsPageResponse getBySlug(String slug);

    CmsPageResponse create(CmsPageRequest request);

    CmsPageResponse update(Long id, CmsPageRequest request);

    void delete(Long id);

    CmsPageResponse publish(Long id);

    CmsPageResponse unpublish(Long id);
}
