package com.example.emailapi.domain.history.repository;

import com.example.emailapi.domain.history.entity.EmailHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailHistoryRepository extends JpaRepository<EmailHistory, Long> {
}
