package com.jeimandei.imanuelbytes.cms;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jeimandei.imanuelbytes.cms.controller.NewsArticleController;
import com.jeimandei.imanuelbytes.cms.entity.NewsArticle;
import com.jeimandei.imanuelbytes.cms.repository.NewsArticleRepository;
import com.jeimandei.imanuelbytes.common.enums.ContentStatus;
import com.jeimandei.imanuelbytes.common.exception.GlobalExceptionHandler;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = NewsArticleController.class)
@AutoConfigureMockMvc(addFilters = false)
@Import(GlobalExceptionHandler.class)
class NewsArticleControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private NewsArticleRepository newsArticleRepository;

    @Test
    void getBySlug_returnsArticle() throws Exception {
        NewsArticle article = new NewsArticle();
        ReflectionTestUtils.setField(article, "id", 1L);
        article.setTitle("Sunday Service");
        article.setSlug("sunday-service");
        article.setContent("Content");
        article.setStatus(ContentStatus.PUBLISHED);

        when(newsArticleRepository.findBySlug("sunday-service")).thenReturn(Optional.of(article));

        mockMvc.perform(get("/api/news/slug/sunday-service"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.slug").value("sunday-service"))
                .andExpect(jsonPath("$.status").value("PUBLISHED"));
    }

    @Test
    void create_returnsCreated() throws Exception {
        NewsArticle saved = new NewsArticle();
        ReflectionTestUtils.setField(saved, "id", 2L);
        saved.setTitle("Youth Fellowship");
        saved.setSlug("youth-fellowship");
        saved.setContent("Body");
        saved.setStatus(ContentStatus.DRAFT);

        when(newsArticleRepository.save(any(NewsArticle.class))).thenReturn(saved);

        String body = objectMapper.writeValueAsString(new Request("Youth Fellowship", "youth-fellowship", "Body"));

        mockMvc.perform(post("/api/news")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(2L))
                .andExpect(jsonPath("$.title").value("Youth Fellowship"));
    }

    private record Request(String title, String slug, String content) {}
}
