/*
 * UrlResponseDto.java
 *
 * Copyright (c) 2025 Nguyen. All rights reserved.
 * This software is the confidential and proprietary information of Nguyen.
 */

package com.example.urlshorteningservice.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

/**
 * UrlResponseDto.java
 *
 * @author Nguyen
 */
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UrlResponseDto {
    Long id;
    String url;
    String shortCode;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
