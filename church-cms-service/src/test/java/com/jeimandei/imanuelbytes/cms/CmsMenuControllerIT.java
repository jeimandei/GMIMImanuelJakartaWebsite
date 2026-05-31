package com.jeimandei.imanuelbytes.cms;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jeimandei.imanuelbytes.cms.controller.CmsMenuController;
import com.jeimandei.imanuelbytes.cms.entity.CmsMenu;
import com.jeimandei.imanuelbytes.cms.repository.CmsMenuRepository;
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

@WebMvcTest(controllers = CmsMenuController.class)
@AutoConfigureMockMvc(addFilters = false)
@Import(GlobalExceptionHandler.class)
class CmsMenuControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CmsMenuRepository cmsMenuRepository;

    @Test
    void getActive_returnsMenus() throws Exception {
        CmsMenu menu = new CmsMenu();
        ReflectionTestUtils.setField(menu, "id", 1L);
        menu.setName("Home");
        menu.setUrl("/");
        menu.setDisplayOrder(1);
        menu.setActive(true);

        when(cmsMenuRepository.findByActiveTrueOrderByDisplayOrderAsc()).thenReturn(List.of(menu));

        mockMvc.perform(get("/api/cms/menus"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Home"));
    }

    @Test
    void create_returnsCreated() throws Exception {
        CmsMenu saved = new CmsMenu();
        ReflectionTestUtils.setField(saved, "id", 2L);
        saved.setName("About");
        saved.setUrl("/about");
        saved.setDisplayOrder(2);
        saved.setActive(true);

        when(cmsMenuRepository.save(any(CmsMenu.class))).thenReturn(saved);

        String body = objectMapper.writeValueAsString(new Request("About", "/about", 2, true, "main"));

        mockMvc.perform(post("/api/cms/menus")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(2L))
                .andExpect(jsonPath("$.name").value("About"));
    }

    private record Request(String name, String url, Integer displayOrder, Boolean active, String menuGroup) {}
}
