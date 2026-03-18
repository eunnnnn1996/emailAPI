package com.example.emailapi.domain.email.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class SendTemplateEmailRequest {

    @Email
    @NotBlank
    private String toEmail;

    @NotBlank
    private String templateCode;

    private Map<String, String> variables = new HashMap<>();
}
