package com.example.emailapi.domain.template.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.emailapi.domain.template.entity.EmailTemplate;

public interface EmailTemplateRepository extends JpaRepository<EmailTemplate, Long> {

    boolean existsByCode(String code);

    Optional<EmailTemplate> findByCodeAndUseYn(String code, String useYn);
}
