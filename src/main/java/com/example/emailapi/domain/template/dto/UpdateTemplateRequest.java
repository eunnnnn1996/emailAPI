package com.example.emailapi.domain.template.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateTemplateRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String subjectTemplate;

    @NotBlank
    private String bodyTemplate;

    @Pattern(regexp = "Y|N", message = "useYn must be Y or N")
    private String useYn = "Y";
}
