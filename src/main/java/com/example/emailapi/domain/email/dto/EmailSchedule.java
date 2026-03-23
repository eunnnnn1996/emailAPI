package com.example.emailapi.domain.email.dto;
import java.time.LocalDateTime;

import com.example.emailapi.domain.history.entity.EmailHistory;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "EMAIL_SCHEDULE")
@Getter
@Setter
@NoArgsConstructor
public class EmailSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SCHEDULE_ID")
    private Long scheduleId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "HISTORY_ID", nullable = false, unique = true)
    private EmailHistory emailHistory;

    @Column(name = "SCHEDULED_AT", nullable = false)
    private LocalDateTime scheduledAt;

    @Column(name = "SCHEDULE_STATUS", nullable = false, length = 20)
    private String scheduleStatus;

    @Column(name = "CANCELED_AT")
    private LocalDateTime canceledAt;

    @Column(name = "CREATED_AT", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;

        if (this.scheduleStatus == null) {
            this.scheduleStatus = "READY";
        }
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}