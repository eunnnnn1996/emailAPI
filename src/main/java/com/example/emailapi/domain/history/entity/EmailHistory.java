package com.example.emailapi.domain.history.entity;

import com.example.emailapi.domain.template.entity.EmailTemplate;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "EMAIL_HISTORY")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmailHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "HISTORY_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEMPLATE_ID")
    private EmailTemplate template;

    @Column(name = "REQUEST_TYPE", nullable = false, length = 20)
    private String requestType; // TEMPLATE / DIRECT

    @Column(name = "TO_EMAIL", nullable = false, length = 200)
    private String toEmail;

    @Column(name = "SUBJECT", nullable = false, length = 300)
    private String subject;

    @Lob
    @Column(name = "BODY", nullable = false)
    private String body;

    @Column(name = "SEND_STATUS", nullable = false, length = 20)
    private String sendStatus; // SUCCESS / FAIL

    @Column(name = "FAIL_REASON", length = 1000)
    private String failReason;

    @Column(name = "SENT_AT")
    private LocalDateTime sentAt;

    @Column(name = "CREATED_AT", nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
