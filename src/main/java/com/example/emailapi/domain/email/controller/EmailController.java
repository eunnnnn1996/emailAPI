package com.example.emailapi.domain.email.controller;

import com.example.emailapi.domain.email.dto.EmailHistoryResponse;
import com.example.emailapi.domain.email.dto.SendDirectEmailRequest;
import com.example.emailapi.domain.email.dto.SendTemplateEmailRequest;
import com.example.emailapi.domain.email.service.EmailSendService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/emails")
public class EmailController {

    private final EmailSendService emailSendService;

    @PostMapping("/send/direct")
    public EmailHistoryResponse sendDirect(@RequestBody @Valid SendDirectEmailRequest request) {
        return emailSendService.sendDirect(request);
    }

    //{{}} 치환
    @PostMapping("/send/template")
    public EmailHistoryResponse sendTemplate(@RequestBody @Valid SendTemplateEmailRequest request) {
        return emailSendService.sendByTemplate(request);
    }

    @GetMapping("/history")
    public List<EmailHistoryResponse> findAllHistory() {
        return emailSendService.findAllHistory();
    }
}
