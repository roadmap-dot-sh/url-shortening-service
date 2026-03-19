/*
 * UrlRequestDto.java
 *
 * Copyright (c) 2025 Nguyen. All rights reserved.
 * This software is the confidential and proprietary information of Nguyen.
 */

package com.example.urlshorteningservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/**
 * UrlRequestDto.java
 *
 * @author Nguyen
 */
@Data
public class UrlRequestDto {
    @NotBlank(message = "URL is required")
    @Pattern(
            regexp = "^(https?|ftp)://[^\\\\s/$.?#].[^\\\\s]*$",
            message = "Invalid URL format. URL must start with http://, https://, or ftp://"
    )
    private String url;
}
