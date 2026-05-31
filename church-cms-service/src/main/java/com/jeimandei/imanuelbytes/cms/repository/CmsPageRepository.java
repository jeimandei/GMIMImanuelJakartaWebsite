package com.jeimandei.imanuelbytes.cms.repository;

import com.jeimandei.imanuelbytes.cms.entity.CmsPage;
import com.jeimandei.imanuelbytes.common.enums.ContentStatus;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CmsPageRepository extends JpaRepository<CmsPage, Long> {

    Optional<CmsPage> findBySlug(String slug);

    Page<CmsPage> findByStatus(ContentStatus status, Pageable pageable);
}
