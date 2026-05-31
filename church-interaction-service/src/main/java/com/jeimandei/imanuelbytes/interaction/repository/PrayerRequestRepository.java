package com.jeimandei.imanuelbytes.interaction.repository;

import com.jeimandei.imanuelbytes.interaction.entity.PrayerRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrayerRequestRepository extends JpaRepository<PrayerRequest, Long> {
}
