package com.example.emailapi.domain.email.service;

import com.example.emailapi.domain.email.dto.EmailHistoryResponse;
import com.example.emailapi.domain.email.dto.SendDirectEmailRequest;
import com.example.emailapi.domain.email.dto.SendTemplateEmailRequest;
import com.example.emailapi.domain.history.entity.EmailHistory;
import com.example.emailapi.domain.history.repository.EmailHistoryRepository;
import com.example.emailapi.domain.template.entity.EmailTemplate;
import com.example.emailapi.domain.template.service.EmailTemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EmailSendService {

    private final JavaMailSender javaMailSender;
    private final EmailHistoryRepository emailHistoryRepository;
    private final EmailTemplateService emailTemplateService;

    @Transactional
    public EmailHistoryResponse sendDirect(SendDirectEmailRequest request) {
        return send(request.getToEmail(), request.getSubject(), request.getBody(), null, "DIRECT");
    }

    @Transactional
    public EmailHistoryResponse sendByTemplate(SendTemplateEmailRequest request) {
        EmailTemplate template = emailTemplateService.getActiveTemplateByCode(request.getTemplateCode());
        String subject = replaceVariables(template.getSubjectTemplate(), request.getVariables());
        String body = replaceVariables(template.getBodyTemplate(), request.getVariables());
        return send(request.getToEmail(), subject, body, template, "TEMPLATE");
    }

    public List<EmailHistoryResponse> findAllHistory() {
        return emailHistoryRepository.findAll(Sort.by(Sort.Direction.DESC, "id"))
                .stream()
                .map(EmailHistoryResponse::from)
                .toList();
    }

    private EmailHistoryResponse send(String toEmail, String subject, String body, EmailTemplate template, String requestType) {
        EmailHistory history = EmailHistory.builder()
                .template(template)
                .requestType(requestType)
                .toEmail(toEmail)
                .subject(subject)
                .body(body)
                .sendStatus("READY")
                .build();

        emailHistoryRepository.save(history);

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(toEmail);
            message.setSubject(subject);
            message.setText(body);
            javaMailSender.send(message);

            history.setSendStatus("SUCCESS");
            history.setSentAt(LocalDateTime.now());
        } catch (MailException e) {
            history.setSendStatus("FAIL");
            history.setFailReason(e.getMessage());
        }

        return EmailHistoryResponse.from(history);
    }

    private String replaceVariables(String template, Map<String, String> variables) {
        String result = template;

        if (variables == null || variables.isEmpty()) {
            return result;
        }

        for (Map.Entry<String, String> entry : variables.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue() == null ? "" : entry.getValue();
            result = result.replace("{{" + key + "}}", value);
            result = result.replace("${" + key + "}", value);
        }

        return result;
    }
}
