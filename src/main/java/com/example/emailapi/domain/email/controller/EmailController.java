package com.example.emailapi.domain.email.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.emailapi.domain.email.dto.EmailHistoryResponse;
import com.example.emailapi.domain.email.dto.SendDirectEmailRequest;
import com.example.emailapi.domain.email.dto.SendTemplateEmailRequest;
import com.example.emailapi.domain.email.service.EmailSendService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

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
    public Page<EmailHistoryResponse> findAllHistory(@PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        return emailSendService.findAllHistory(pageable);
    }
}
