package com.jeimandei.imanuelbytes.media.repository;

import com.jeimandei.imanuelbytes.media.entity.Sermon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SermonRepository extends JpaRepository<Sermon, Long> {

    Page<Sermon> findAllByOrderBySermonDateDesc(Pageable pageable);
}
