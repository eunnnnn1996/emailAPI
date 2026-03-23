package com.example.emailapi.domain.email.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendDirectEmailRequest {

    @Email
    @NotBlank
    private String toEmail;

    @NotBlank
    private String subject;

    @NotBlank
    private String body;

    @Future
    private LocalDateTime scheduledAt;
}
