package com.jeimandei.imanuelbytes.interaction.repository;

import com.jeimandei.imanuelbytes.interaction.entity.ContactMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactMessageRepository extends JpaRepository<ContactMessage, Long> {
}
