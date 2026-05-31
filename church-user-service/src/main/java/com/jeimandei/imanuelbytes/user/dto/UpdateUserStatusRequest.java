package com.jeimandei.imanuelbytes.user.dto;

import com.jeimandei.imanuelbytes.common.enums.UserStatus;
import jakarta.validation.constraints.NotNull;

public class UpdateUserStatusRequest {

    @NotNull(message = "Status is required")
    private UserStatus status;

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }
}
