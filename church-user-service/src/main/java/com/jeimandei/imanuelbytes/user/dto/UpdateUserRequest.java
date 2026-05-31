package com.jeimandei.imanuelbytes.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UpdateUserRequest {

    @NotBlank(message = "Full name is required")
    @Size(max = 120, message = "Full name max length is 120")
    private String fullName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email is invalid")
    private String email;

    @Size(max = 25, message = "Phone number max length is 25")
    private String phoneNumber;

    @Size(max = 255, message = "Profile image URL max length is 255")
    private String profileImageUrl;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }
}
