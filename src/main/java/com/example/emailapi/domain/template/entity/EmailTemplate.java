package com.example.emailapi.domain.template.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "EMAIL_TEMPLATE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmailTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TEMPLATE_ID")
    private Long id;

    @Column(name = "TEMPLATE_CODE", nullable = false, unique = true, length = 100)
    private String code;

    @Column(name = "TEMPLATE_NAME", nullable = false, length = 100)
    private String name;

    @Column(name = "SUBJECT_TEMPLATE", nullable = false, length = 300)
    private String subjectTemplate;

    @Lob
    @Column(name = "BODY_TEMPLATE", nullable = false)
    private String bodyTemplate;

    @Column(name = "USE_YN", nullable = false, length = 1)
    private String useYn;

    @Column(name = "CREATED_AT", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
        if (this.useYn == null || this.useYn.isBlank()) {
            this.useYn = "Y";
        }
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
