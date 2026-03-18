package com.example.emailapi.domain.template.dto;

import java.time.LocalDateTime;

import com.example.emailapi.domain.template.entity.EmailTemplate;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TemplateResponse {
    private Long id;
    private String code;
    private String name;
    private String subjectTemplate;
    private String bodyTemplate;
    private String useYn;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;

    public static TemplateResponse from(EmailTemplate template) {
        return TemplateResponse.builder()
                .id(template.getId())
                .code(template.getCode())
                .name(template.getName())
                .subjectTemplate(template.getSubjectTemplate())
                .bodyTemplate(template.getBodyTemplate())
                .useYn(template.getUseYn())
                .createdAt(template.getCreatedAt())
                .updatedAt(template.getUpdatedAt())
                .build();
    }
}
