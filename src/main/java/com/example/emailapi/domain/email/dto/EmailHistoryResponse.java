package com.example.emailapi.domain.email.dto;

import com.example.emailapi.domain.history.entity.EmailHistory;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmailHistoryResponse {
    private Long id;
    private Long templateId;
    private String requestType;
    private String toEmail;
    private String subject;
    private String body;
    private String sendStatus;
    private String failReason;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime sentAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    public static EmailHistoryResponse from(EmailHistory history) {
        return EmailHistoryResponse.builder()
                .id(history.getId())
                .templateId(history.getTemplate() != null ? history.getTemplate().getId() : null)
                .requestType(history.getRequestType())
                .toEmail(history.getToEmail())
                .subject(history.getSubject())
                .body(history.getBody())
                .sendStatus(history.getSendStatus())
                .failReason(history.getFailReason())
                .sentAt(history.getSentAt())
                .createdAt(history.getCreatedAt())
                .build();
    }
}
