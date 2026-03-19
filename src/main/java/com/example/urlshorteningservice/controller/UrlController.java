/*
 * UrlController.java
 *
 * Copyright (c) 2025 Nguyen. All rights reserved.
 * This software is the confidential and proprietary information of Nguyen.
 */

package com.example.urlshorteningservice.controller;

import com.example.urlshorteningservice.dto.UrlRequestDto;
import com.example.urlshorteningservice.dto.UrlResponseDto;
import com.example.urlshorteningservice.dto.UrlStatusDto;
import com.example.urlshorteningservice.service.UrlService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * UrlController.java
 *
 * @author Nguyen
 */
@RestController
@RequestMapping("/shorten")
@RequiredArgsConstructor
public class UrlController {
    private final UrlService urlService;

    @PostMapping
    public ResponseEntity<UrlResponseDto> createShortUrl(@Valid @RequestBody UrlRequestDto requestDto) {
        UrlResponseDto response = urlService.createShortUrl(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<UrlResponseDto> getOriginalUrl(@PathVariable String shortCode) {
        UrlResponseDto response = urlService.getOriginalUrl(shortCode);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{shortCode}")
    public ResponseEntity<UrlResponseDto> updateShortUrl(
            @PathVariable String shortCode,
            @Valid @RequestBody UrlRequestDto requestDto
    ) {
        UrlResponseDto response = urlService.updateShortUrl(shortCode, requestDto);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{shortCode}/stats")
    public ResponseEntity<UrlStatusDto> getUrlStatistics(@PathVariable String shortCode) {
        UrlStatusDto stats = urlService.getUrlStatistics(shortCode);
        return ResponseEntity.ok(stats);
    }

    @GetMapping("/{shortCode}")
    public String redirectToOriginalUrl(@PathVariable String shortCode) {
        UrlResponseDto urlResponse = urlService.getOriginalUrl(shortCode);
        return "redirect:" + urlResponse.getUrl();
    }


}
