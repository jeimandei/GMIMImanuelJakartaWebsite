package com.jeimandei.imanuelbytes.cms.repository;

import com.jeimandei.imanuelbytes.cms.entity.CmsContentBlock;
import com.jeimandei.imanuelbytes.cms.entity.CmsPage;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CmsContentBlockRepository extends JpaRepository<CmsContentBlock, Long> {

    List<CmsContentBlock> findByPageOrderByDisplayOrderAsc(CmsPage page);
}
