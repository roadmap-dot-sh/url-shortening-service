/*
 * UrlRepository.java
 *
 * Copyright (c) 2025 Nguyen. All rights reserved.
 * This software is the confidential and proprietary information of Nguyen.
 */

package com.example.urlshorteningservice.repository;

import com.example.urlshorteningservice.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * UrlRepository.java
 *
 * @author Nguyen
 */
public interface UrlRepository extends JpaRepository<Url, Long> {
    Optional<Url> findByShortCode(String shortCode);

    boolean existsByShortCode(String shortCode);
}
