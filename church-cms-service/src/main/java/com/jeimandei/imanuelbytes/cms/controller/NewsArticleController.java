package com.jeimandei.imanuelbytes.cms.controller;

import com.jeimandei.imanuelbytes.cms.dto.NewsArticleRequest;
import com.jeimandei.imanuelbytes.cms.dto.NewsArticleResponse;
import com.jeimandei.imanuelbytes.cms.entity.NewsArticle;
import com.jeimandei.imanuelbytes.cms.repository.NewsArticleRepository;
import com.jeimandei.imanuelbytes.common.enums.ContentStatus;
import com.jeimandei.imanuelbytes.common.exception.ResourceNotFoundException;
import jakarta.validation.Valid;
import java.time.LocalDateTime;
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
@RequestMapping("/api/news")
public class NewsArticleController {

    private final NewsArticleRepository newsArticleRepository;

    public NewsArticleController(NewsArticleRepository newsArticleRepository) {
        this.newsArticleRepository = newsArticleRepository;
    }

    @GetMapping
    public Page<NewsArticleResponse> getAll(Pageable pageable) {
        return newsArticleRepository.findAll(pageable).map(this::toResponse);
    }

    @GetMapping("/{id}")
    public NewsArticleResponse getById(@PathVariable Long id) {
        return toResponse(findById(id));
    }

    @GetMapping("/slug/{slug}")
    public NewsArticleResponse getBySlug(@PathVariable String slug) {
        return toResponse(newsArticleRepository.findBySlug(slug)
                .orElseThrow(() -> new ResourceNotFoundException("Article not found")));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NewsArticleResponse create(@Valid @RequestBody NewsArticleRequest request) {
        NewsArticle article = new NewsArticle();
        apply(article, request);
        return toResponse(newsArticleRepository.save(article));
    }

    @PutMapping("/{id}")
    public NewsArticleResponse update(@PathVariable Long id, @Valid @RequestBody NewsArticleRequest request) {
        NewsArticle article = findById(id);
        apply(article, request);
        return toResponse(newsArticleRepository.save(article));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        newsArticleRepository.delete(findById(id));
    }

    @PutMapping("/{id}/publish")
    public NewsArticleResponse publish(@PathVariable Long id) {
        NewsArticle article = findById(id);
        article.setStatus(ContentStatus.PUBLISHED);
        article.setPublishedAt(LocalDateTime.now());
        return toResponse(newsArticleRepository.save(article));
    }

    @PutMapping("/{id}/unpublish")
    public NewsArticleResponse unpublish(@PathVariable Long id) {
        NewsArticle article = findById(id);
        article.setStatus(ContentStatus.DRAFT);
        article.setPublishedAt(null);
        return toResponse(newsArticleRepository.save(article));
    }

    private NewsArticle findById(Long id) {
        return newsArticleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Article not found"));
    }

    private void apply(NewsArticle article, NewsArticleRequest request) {
        article.setTitle(request.getTitle());
        article.setSlug(request.getSlug());
        article.setContent(request.getContent());
        article.setExcerpt(request.getExcerpt());
        article.setImageUrl(request.getImageUrl());
        article.setAuthorId(request.getAuthorId());
        article.setStatus(request.getStatus() == null ? ContentStatus.DRAFT : request.getStatus());
    }

    private NewsArticleResponse toResponse(NewsArticle article) {
        NewsArticleResponse response = new NewsArticleResponse();
        response.setId(article.getId());
        response.setTitle(article.getTitle());
        response.setSlug(article.getSlug());
        response.setContent(article.getContent());
        response.setExcerpt(article.getExcerpt());
        response.setImageUrl(article.getImageUrl());
        response.setAuthorId(article.getAuthorId());
        response.setStatus(article.getStatus());
        response.setPublishedAt(article.getPublishedAt());
        response.setCreatedAt(article.getCreatedAt());
        response.setUpdatedAt(article.getUpdatedAt());
        return response;
    }
}
