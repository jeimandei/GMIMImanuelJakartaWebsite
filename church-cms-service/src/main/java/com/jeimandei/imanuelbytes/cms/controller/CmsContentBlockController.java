package com.jeimandei.imanuelbytes.cms.controller;

import com.jeimandei.imanuelbytes.cms.dto.CmsContentBlockDto;
import com.jeimandei.imanuelbytes.cms.entity.CmsContentBlock;
import com.jeimandei.imanuelbytes.cms.entity.CmsPage;
import com.jeimandei.imanuelbytes.cms.repository.CmsContentBlockRepository;
import com.jeimandei.imanuelbytes.cms.repository.CmsPageRepository;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cms/pages/{pageId}/blocks")
public class CmsContentBlockController {

    private final CmsContentBlockRepository blockRepository;
    private final CmsPageRepository pageRepository;

    public CmsContentBlockController(CmsContentBlockRepository blockRepository, CmsPageRepository pageRepository) {
        this.blockRepository = blockRepository;
        this.pageRepository = pageRepository;
    }

    @GetMapping
    public List<CmsContentBlock> getBlocks(@PathVariable Long pageId) {
        CmsPage page = pageRepository.findById(pageId).orElseThrow(() -> new ResourceNotFoundException("Page not found"));
        return blockRepository.findByPageOrderByDisplayOrderAsc(page);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CmsContentBlock create(@PathVariable Long pageId, @Valid @RequestBody CmsContentBlockDto dto) {
        CmsPage page = pageRepository.findById(pageId).orElseThrow(() -> new ResourceNotFoundException("Page not found"));
        CmsContentBlock block = new CmsContentBlock();
        apply(block, dto);
        block.setPage(page);
        return blockRepository.save(block);
    }

    @PutMapping("/{blockId}")
    public CmsContentBlock update(@PathVariable Long pageId, @PathVariable Long blockId, @Valid @RequestBody CmsContentBlockDto dto) {
        CmsContentBlock block = blockRepository.findById(blockId).orElseThrow(() -> new ResourceNotFoundException("Block not found"));
        if (!block.getPage().getId().equals(pageId)) {
            throw new ResourceNotFoundException("Block not found");
        }
        apply(block, dto);
        return blockRepository.save(block);
    }

    @DeleteMapping("/{blockId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long pageId, @PathVariable Long blockId) {
        CmsContentBlock block = blockRepository.findById(blockId).orElseThrow(() -> new ResourceNotFoundException("Block not found"));
        if (!block.getPage().getId().equals(pageId)) {
            throw new ResourceNotFoundException("Block not found");
        }
        blockRepository.delete(block);
    }

    private void apply(CmsContentBlock block, CmsContentBlockDto dto) {
        block.setBlockType(dto.getBlockType());
        block.setTitle(dto.getTitle());
        block.setBody(dto.getBody());
        block.setImageUrl(dto.getImageUrl());
        block.setDisplayOrder(dto.getDisplayOrder() == null ? 0 : dto.getDisplayOrder());
        block.setActive(dto.getActive());
    }
}
