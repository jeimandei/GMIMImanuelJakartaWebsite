package com.jeimandei.imanuelbytes.media.repository;

import com.jeimandei.imanuelbytes.media.entity.GalleryItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GalleryItemRepository extends JpaRepository<GalleryItem, Long> {
}
