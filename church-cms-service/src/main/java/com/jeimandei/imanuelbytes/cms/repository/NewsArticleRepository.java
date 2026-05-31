package com.jeimandei.imanuelbytes.cms.repository;

import com.jeimandei.imanuelbytes.cms.entity.NewsArticle;
import com.jeimandei.imanuelbytes.common.enums.ContentStatus;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsArticleRepository extends JpaRepository<NewsArticle, Long> {

    Optional<NewsArticle> findBySlug(String slug);

    Page<NewsArticle> findByStatus(ContentStatus status, Pageable pageable);
}
