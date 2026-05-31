package com.jeimandei.imanuelbytes.cms.repository;

import com.jeimandei.imanuelbytes.cms.entity.CmsMenu;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CmsMenuRepository extends JpaRepository<CmsMenu, Long> {

    List<CmsMenu> findByActiveTrueOrderByDisplayOrderAsc();

    List<CmsMenu> findByMenuGroupOrderByDisplayOrderAsc(String menuGroup);
}
