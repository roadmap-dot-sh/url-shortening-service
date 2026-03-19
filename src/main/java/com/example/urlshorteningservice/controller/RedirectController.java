/*
 * RedirectController.java
 *
 * Copyright (c) 2025 Nguyen. All rights reserved.
 * This software is the confidential and proprietary information of Nguyen.
 */

package com.example.urlshorteningservice.controller;

import com.example.urlshorteningservice.dto.UrlResponseDto;
import com.example.urlshorteningservice.service.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * RedirectController.java
 *
 * @author Nguyen
 */
@Controller
@RequiredArgsConstructor
public class RedirectController {
    private final UrlService urlService;

    @GetMapping("/{shortCode}")
    public String redirectToOriginalUrl(@PathVariable String shortCode) {
        UrlResponseDto urlResponse = urlService.getOriginalUrl(shortCode);
        return "redirect:" + urlResponse.getUrl();
    }
}
