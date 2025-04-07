package com.careerconnect.rest.controller;

import com.careerconnect.dto.LanguageStat;
import com.careerconnect.service.LanguageStatsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/languages")
public class LanguageStatsController {

    private final LanguageStatsService languageStatsService;

    public LanguageStatsController(LanguageStatsService languageStatsService) {
        this.languageStatsService = languageStatsService;
    }

    @GetMapping("/spring")
    public Flux<LanguageStat> getSpringBootLangStats() {
        return languageStatsService.fetchSpringBootLanguageStats();
    }
}