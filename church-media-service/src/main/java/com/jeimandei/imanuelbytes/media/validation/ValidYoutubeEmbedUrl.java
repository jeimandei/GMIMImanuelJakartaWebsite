package com.jeimandei.imanuelbytes.media.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = YoutubeEmbedUrlValidator.class)
public @interface ValidYoutubeEmbedUrl {

    String message() default "Must be a valid YouTube embed URL";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
