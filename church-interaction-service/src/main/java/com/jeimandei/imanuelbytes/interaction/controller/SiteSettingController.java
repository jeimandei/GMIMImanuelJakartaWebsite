package com.jeimandei.imanuelbytes.interaction.controller;

import com.jeimandei.imanuelbytes.common.exception.ResourceNotFoundException;
import com.jeimandei.imanuelbytes.interaction.entity.SiteSetting;
import com.jeimandei.imanuelbytes.interaction.repository.SiteSettingRepository;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/api/settings")
public class SiteSettingController {

    private final SiteSettingRepository siteSettingRepository;

    public SiteSettingController(SiteSettingRepository siteSettingRepository) {
        this.siteSettingRepository = siteSettingRepository;
    }

    @GetMapping
    public List<SiteSetting> list() {
        return siteSettingRepository.findAll();
    }

    @GetMapping("/{key}")
    public SiteSetting getByKey(@PathVariable String key) {
        return siteSettingRepository.findBySettingKey(key)
                .orElseThrow(() -> new ResourceNotFoundException("Setting not found: " + key));
    }

    @PutMapping("/{key}")
    public SiteSetting upsert(@PathVariable String key, @RequestBody @NotBlank String value) {
        SiteSetting setting = siteSettingRepository.findBySettingKey(key)
                .orElseGet(() -> {
                    SiteSetting s = new SiteSetting();
                    s.setSettingKey(key);
                    return s;
                });
        setting.setSettingValue(value);
        setting.setUpdatedAt(LocalDateTime.now());
        return siteSettingRepository.save(setting);
    }
}
