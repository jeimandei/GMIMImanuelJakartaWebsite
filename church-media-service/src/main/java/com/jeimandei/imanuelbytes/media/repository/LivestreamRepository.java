package com.jeimandei.imanuelbytes.media.repository;

import com.jeimandei.imanuelbytes.media.entity.Livestream;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivestreamRepository extends JpaRepository<Livestream, Long> {

    Optional<Livestream> findFirstByActiveTrueOrderByScheduledStartDesc();
}
