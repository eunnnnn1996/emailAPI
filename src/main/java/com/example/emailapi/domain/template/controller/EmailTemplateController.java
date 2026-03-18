package com.example.emailapi.domain.template.controller;

import com.example.emailapi.domain.template.dto.CreateTemplateRequest;
import com.example.emailapi.domain.template.dto.TemplateResponse;
import com.example.emailapi.domain.template.dto.UpdateTemplateRequest;
import com.example.emailapi.domain.template.service.EmailTemplateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/templates")
public class EmailTemplateController {

    private final EmailTemplateService emailTemplateService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TemplateResponse create(@RequestBody @Valid CreateTemplateRequest request) {
        return emailTemplateService.create(request);
    }

    @GetMapping
    public List<TemplateResponse> findAll() {
        return emailTemplateService.findAll();
    }

    @GetMapping("/{id}")
    public TemplateResponse findOne(@PathVariable Long id) {
        return emailTemplateService.findOne(id);
    }

    @PutMapping("/{id}")
    public TemplateResponse update(@PathVariable Long id, @RequestBody @Valid UpdateTemplateRequest request) {
        return emailTemplateService.update(id, request);
    }
}
