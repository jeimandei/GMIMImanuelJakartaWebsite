package com.jeimandei.imanuelbytes.interaction.entity;

import com.jeimandei.imanuelbytes.common.enums.RequestStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "prayer_requests")
public class PrayerRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String name;

    @Column(nullable = false, length = 150)
    private String email;

    @Column(length = 25)
    private String phone;

    @Column(nullable = false, length = 2000)
    private String message;

    @Column(nullable = false)
    private boolean confidential;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 16)
    private RequestStatus status = RequestStatus.NEW;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public boolean isConfidential() { return confidential; }
    public void setConfidential(boolean confidential) { this.confidential = confidential; }
    public RequestStatus getStatus() { return status; }
    public void setStatus(RequestStatus status) { this.status = status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}
