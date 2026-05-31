package com.jeimandei.imanuelbytes.event.repository;

import com.jeimandei.imanuelbytes.event.entity.Event;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {

    Optional<Event> findBySlug(String slug);

    Page<Event> findByEventStartGreaterThanEqual(LocalDateTime now, Pageable pageable);
}
