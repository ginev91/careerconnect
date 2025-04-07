package com.careerconnect.controller;

import com.careerconnect.dto.LanguageStat;
import com.careerconnect.service.LanguageStatsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Mono;

import java.util.List;

@Controller
public class LanguageStatsViewController {

    private final LanguageStatsService statsService;

    public LanguageStatsViewController(LanguageStatsService statsService) {
        this.statsService = statsService;
    }

    @GetMapping("/languages/spring")
    public String showLanguageStats(Model model) {
        // WebFlux returns Flux<LanguageStat>, we convert to blocking list just for Thymeleaf
        List<LanguageStat> stats = statsService.fetchSpringBootLanguageStats().collectList().block();
        model.addAttribute("languages", stats);
        return "language-stats";
    }
}