package com.jeimandei.imanuelbytes.media.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class YoutubeEmbedUrlValidator implements ConstraintValidator<ValidYoutubeEmbedUrl, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isBlank()) {
            return true;
        }
        return value.startsWith("https://www.youtube.com/embed/");
    }
}
