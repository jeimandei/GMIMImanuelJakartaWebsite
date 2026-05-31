package com.jeimandei.imanuelbytes.cms;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jeimandei.imanuelbytes.cms.controller.CmsContentBlockController;
import com.jeimandei.imanuelbytes.cms.entity.CmsContentBlock;
import com.jeimandei.imanuelbytes.cms.entity.CmsPage;
import com.jeimandei.imanuelbytes.cms.repository.CmsContentBlockRepository;
import com.jeimandei.imanuelbytes.cms.repository.CmsPageRepository;
import com.jeimandei.imanuelbytes.common.exception.GlobalExceptionHandler;
import java.util.List;
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

@WebMvcTest(controllers = CmsContentBlockController.class)
@AutoConfigureMockMvc(addFilters = false)
@Import(GlobalExceptionHandler.class)
class CmsContentBlockControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CmsContentBlockRepository blockRepository;

    @MockBean
    private CmsPageRepository pageRepository;

    @Test
    void getBlocks_returnsBlocks() throws Exception {
        CmsPage page = new CmsPage();
        ReflectionTestUtils.setField(page, "id", 1L);

        CmsContentBlock block = new CmsContentBlock();
        ReflectionTestUtils.setField(block, "id", 10L);
        block.setPage(page);
        block.setBlockType("HERO");
        block.setTitle("Welcome");
        block.setActive(true);

        when(pageRepository.findById(1L)).thenReturn(Optional.of(page));
        when(blockRepository.findByPageOrderByDisplayOrderAsc(page)).thenReturn(List.of(block));

        mockMvc.perform(get("/api/cms/pages/1/blocks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(10L))
                .andExpect(jsonPath("$[0].blockType").value("HERO"));
    }

    @Test
    void create_returnsCreated() throws Exception {
        CmsPage page = new CmsPage();
        ReflectionTestUtils.setField(page, "id", 1L);

        CmsContentBlock saved = new CmsContentBlock();
        ReflectionTestUtils.setField(saved, "id", 11L);
        saved.setPage(page);
        saved.setBlockType("TEXT");
        saved.setTitle("Intro");
        saved.setActive(true);

        when(pageRepository.findById(1L)).thenReturn(Optional.of(page));
        when(blockRepository.save(any(CmsContentBlock.class))).thenReturn(saved);

        String body = objectMapper.writeValueAsString(new Request("TEXT", "Intro", "Body", null, 1, true));

        mockMvc.perform(post("/api/cms/pages/1/blocks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(11L))
                .andExpect(jsonPath("$.blockType").value("TEXT"));
    }

    private record Request(String blockType, String title, String body, String imageUrl, Integer displayOrder, Boolean active) {}
}
