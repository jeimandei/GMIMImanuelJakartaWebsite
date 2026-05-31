package com.jeimandei.imanuelbytes.cms.controller;

import com.jeimandei.imanuelbytes.cms.dto.CmsMenuRequest;
import com.jeimandei.imanuelbytes.cms.entity.CmsMenu;
import com.jeimandei.imanuelbytes.cms.repository.CmsMenuRepository;
import com.jeimandei.imanuelbytes.common.exception.ResourceNotFoundException;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cms/menus")
public class CmsMenuController {

    private final CmsMenuRepository cmsMenuRepository;

    public CmsMenuController(CmsMenuRepository cmsMenuRepository) {
        this.cmsMenuRepository = cmsMenuRepository;
    }

    @GetMapping
    public List<CmsMenu> getActive() {
        return cmsMenuRepository.findByActiveTrueOrderByDisplayOrderAsc();
    }

    @GetMapping("/group")
    public List<CmsMenu> getByGroup(@RequestParam String group) {
        return cmsMenuRepository.findByMenuGroupOrderByDisplayOrderAsc(group);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CmsMenu create(@Valid @RequestBody CmsMenuRequest request) {
        CmsMenu menu = new CmsMenu();
        apply(menu, request);
        return cmsMenuRepository.save(menu);
    }

    @PutMapping("/{id}")
    public CmsMenu update(@PathVariable Long id, @Valid @RequestBody CmsMenuRequest request) {
        CmsMenu menu = cmsMenuRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Menu item not found"));
        apply(menu, request);
        return cmsMenuRepository.save(menu);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        cmsMenuRepository.deleteById(id);
    }

    private void apply(CmsMenu menu, CmsMenuRequest request) {
        menu.setName(request.getName());
        menu.setUrl(request.getUrl());
        menu.setDisplayOrder(request.getDisplayOrder() == null ? 0 : request.getDisplayOrder());
        menu.setActive(request.getActive());
        menu.setMenuGroup(request.getMenuGroup());
    }
}
