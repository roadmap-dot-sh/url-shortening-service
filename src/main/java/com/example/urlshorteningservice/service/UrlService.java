/*
 * UrlService.java
 *
 * Copyright (c) 2025 Nguyen. All rights reserved.
 * This software is the confidential and proprietary information of Nguyen.
 */

package com.example.urlshorteningservice.service;

import com.example.urlshorteningservice.dto.UrlRequestDto;
import com.example.urlshorteningservice.dto.UrlResponseDto;
import com.example.urlshorteningservice.dto.UrlStatusDto;
import com.example.urlshorteningservice.exception.UrlNotFoundException;
import com.example.urlshorteningservice.model.Url;
import com.example.urlshorteningservice.repository.UrlRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * UrlService.java
 *
 * @author Nguyen
 */
@Service
@RequiredArgsConstructor
public class UrlService {
    private final UrlRepository urlRepository;
    private static final int SHORT_CODE_LENGTH = 6;

    @Transactional
    public UrlResponseDto createShortUrl(UrlRequestDto urlRequestDto) {
        Url url = new Url();
        url.setUrl(urlRequestDto.getUrl());
        url.setShortCode(generateUniqueShortCode());

        Url savedUrl = urlRepository.save(url);

        return mapToResponseDto(savedUrl);
    }

    @Transactional
    public UrlResponseDto getOriginalUrl(String shortCode) {
        Url url = urlRepository.findByShortCode(shortCode)
                .orElseThrow(() -> new UrlNotFoundException("Short URL not found with code: " + shortCode));

        url.setAccessCount(url.getAccessCount() + 1);
        Url updatedUrl = urlRepository.save(url);

        return mapToResponseDto(updatedUrl);
    }

    @Transactional
    public UrlResponseDto updateShortUrl(String shortCode, UrlRequestDto requestDto) {
        Url url = urlRepository.findByShortCode(shortCode)
                .orElseThrow(() -> new UrlNotFoundException("Short URL not found with code: " + shortCode));

        url.setUrl(requestDto.getUrl());
        Url updatedUrl = urlRepository.save(url);

        return mapToResponseDto(updatedUrl);
    }

    @Transactional
    public void deleteShortUrl(String shortCode) {
        Url url = urlRepository.findByShortCode(shortCode)
                .orElseThrow(() -> new UrlNotFoundException("Short URL not found with code: " + shortCode));

        urlRepository.delete(url);
    }

    @Transactional(readOnly = true)
    public UrlStatusDto getUrlStatistics(String shortCode) {
        Url url = urlRepository.findByShortCode(shortCode)
                .orElseThrow(() -> new UrlNotFoundException("Short URL not found with code: " + shortCode));

        return UrlStatusDto.builder()
                .id(url.getId())
                .url(url.getUrl())
                .shortCode(url.getShortCode())
                .createdAt(url.getCreatedAt())
                .updatedAt(url.getUpdatedAt())
                .accessCount(url.getAccessCount())
                .build();
    }

    private String generateUniqueShortCode() {
        String shortCode;

        do {
            shortCode = RandomStringUtils.randomAlphanumeric(SHORT_CODE_LENGTH);
        } while (urlRepository.existsByShortCode(shortCode));

        return shortCode;
    }

    private UrlResponseDto mapToResponseDto(Url url) {
        return UrlResponseDto.builder()
                .id(url.getId())
                .url(url.getUrl())
                .shortCode(url.getShortCode())
                .createdAt(url.getCreatedAt())
                .updatedAt(url.getUpdatedAt())
                .build();
    }
}
