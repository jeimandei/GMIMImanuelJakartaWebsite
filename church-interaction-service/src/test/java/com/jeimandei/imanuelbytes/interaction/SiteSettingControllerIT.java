package com.jeimandei.imanuelbytes.interaction;

import com.jeimandei.imanuelbytes.common.exception.GlobalExceptionHandler;
import com.jeimandei.imanuelbytes.interaction.controller.SiteSettingController;
import com.jeimandei.imanuelbytes.interaction.entity.SiteSetting;
import com.jeimandei.imanuelbytes.interaction.repository.SiteSettingRepository;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = SiteSettingController.class)
@AutoConfigureMockMvc(addFilters = false)
@Import(GlobalExceptionHandler.class)
class SiteSettingControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SiteSettingRepository siteSettingRepository;

    @Test
    void getByKey_returnsSetting() throws Exception {
        SiteSetting setting = new SiteSetting();
        ReflectionTestUtils.setField(setting, "id", 1L);
        setting.setSettingKey("church.name");
        setting.setSettingValue("GMIM Imanuel Jakarta");

        when(siteSettingRepository.findBySettingKey("church.name")).thenReturn(Optional.of(setting));

        mockMvc.perform(get("/api/settings/church.name"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.settingKey").value("church.name"))
                .andExpect(jsonPath("$.settingValue").value("GMIM Imanuel Jakarta"));
    }

    @Test
    void upsert_updatesValue() throws Exception {
        SiteSetting setting = new SiteSetting();
        ReflectionTestUtils.setField(setting, "id", 2L);
        setting.setSettingKey("church.phone");
        setting.setSettingValue("+62-21-0000000");

        when(siteSettingRepository.findBySettingKey("church.phone")).thenReturn(Optional.of(setting));
        when(siteSettingRepository.save(any(SiteSetting.class))).thenReturn(setting);

        mockMvc.perform(put("/api/settings/church.phone")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("\"+62-21-1111111\""))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.settingKey").value("church.phone"));
    }
}
