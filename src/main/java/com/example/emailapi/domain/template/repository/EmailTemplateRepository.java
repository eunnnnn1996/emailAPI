package com.example.emailapi.domain.template.repository;

import com.example.emailapi.domain.template.entity.EmailTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmailTemplateRepository extends JpaRepository<EmailTemplate, Long> {

    boolean existsByCode(String code);

    Optional<EmailTemplate> findByCodeAndUseYn(String code, String useYn);
}
