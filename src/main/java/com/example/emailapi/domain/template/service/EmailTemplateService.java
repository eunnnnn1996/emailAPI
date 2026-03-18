package com.example.emailapi.domain.template.service;

import com.example.emailapi.domain.template.dto.CreateTemplateRequest;
import com.example.emailapi.domain.template.dto.TemplateResponse;
import com.example.emailapi.domain.template.dto.UpdateTemplateRequest;
import com.example.emailapi.domain.template.entity.EmailTemplate;
import com.example.emailapi.domain.template.repository.EmailTemplateRepository;
import com.example.emailapi.global.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EmailTemplateService {

    private final EmailTemplateRepository emailTemplateRepository;

    @Transactional
    public TemplateResponse create(CreateTemplateRequest request) {
        if (emailTemplateRepository.existsByCode(request.getCode())) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "이미 존재하는 template code 입니다.");
        }

        EmailTemplate template = EmailTemplate.builder()
                .code(request.getCode())
                .name(request.getName())
                .subjectTemplate(request.getSubjectTemplate())
                .bodyTemplate(request.getBodyTemplate())
                .useYn(request.getUseYn())
                .build();

        return TemplateResponse.from(emailTemplateRepository.save(template));
    }

    public List<TemplateResponse> findAll() {
        return emailTemplateRepository.findAll()
                .stream()
                .map(TemplateResponse::from)
                .toList();
    }

    public TemplateResponse findOne(Long id) {
        return TemplateResponse.from(getTemplate(id));
    }

    @Transactional
    public TemplateResponse update(Long id, UpdateTemplateRequest request) {
        EmailTemplate template = getTemplate(id);
        template.setName(request.getName());
        template.setSubjectTemplate(request.getSubjectTemplate());
        template.setBodyTemplate(request.getBodyTemplate());
        template.setUseYn(request.getUseYn());
        return TemplateResponse.from(template);
    }

    public EmailTemplate getActiveTemplateByCode(String code) {
        return emailTemplateRepository.findByCodeAndUseYn(code, "Y")
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "사용 가능한 템플릿을 찾을 수 없습니다."));
    }

    private EmailTemplate getTemplate(Long id) {
        return emailTemplateRepository.findById(id)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "템플릿을 찾을 수 없습니다."));
    }
}
