package com.example.emailapi.domain.template.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.emailapi.domain.template.dto.CreateTemplateRequest;
import com.example.emailapi.domain.template.dto.TemplateResponse;
import com.example.emailapi.domain.template.dto.UpdateTemplateRequest;
import com.example.emailapi.domain.template.service.EmailTemplateService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

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
    public Page<TemplateResponse> findAll(@PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        return emailTemplateService.findAll(pageable);
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