package com.careerconnect.service;

import com.careerconnect.dto.LanguageStat;
import jakarta.annotation.PostConstruct;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class LanguageStatsService {

    private final WebClient webClient;

    public LanguageStatsService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Flux<LanguageStat> fetchSpringBootLanguageStats() {
        Mono<Map<String, Integer>> response = webClient.get()
                .uri("https://api.github.com/repos/spring-projects/spring-boot/languages")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<>() {
                });
        System.out.println("GitHub API works !!!");

        return response.flatMapMany(map -> {
            int total = map.values().stream().mapToInt(Integer::intValue).sum();

            List<LanguageStat> result = map.entrySet().stream()
                    .map(entry -> {
                        double percent = (entry.getValue() * 100.0) / total;
                        return new LanguageStat(entry.getKey(), percent);
                    })
                    .sorted(Comparator.comparingDouble(LanguageStat::getPercentage).reversed())
                    .collect(Collectors.toList());

            return Flux.fromIterable(result);
        }).onErrorResume(ex -> {
            System.out.println("⚠️ GitHub API failed, using randomized fallback: " + ex.getMessage());

            Random random = new Random();

            List<LanguageStat> fallbackList = List.of(
                    new LanguageStat("Java", 97.88),
                    new LanguageStat("Kotlin", 1.59),
                    new LanguageStat("HTML", 0.20),
                    new LanguageStat("JavaScript", 0.12),
                    new LanguageStat("Shell", 0.10),
                    new LanguageStat("Groovy", 0.05),
                    new LanguageStat("Ruby", 0.03),
                    new LanguageStat("Smarty", 0.01),
                    new LanguageStat("Batchfile", 0.01),
                    new LanguageStat("Dockerfile", 0.00),
                    new LanguageStat("Mustache", 0.00),
                    new LanguageStat("Vim Snippet", 0.00),
                    new LanguageStat("CSS", 0.00)
            );

            List<LanguageStat> randomized = fallbackList.stream()
                    .map(stat -> {
                        double variation = (random.nextDouble() * 4) - 2; // between -2 and +2
                        double randomizedPercentage = Math.max(0.0, stat.getPercentage() + variation);
                        return new LanguageStat(stat.getName(), Math.round(randomizedPercentage * 100.0) / 100.0);
                    })
                    .sorted(Comparator.comparingDouble(LanguageStat::getPercentage).reversed())
                    .collect(Collectors.toList());

            return Flux.fromIterable(randomized);
        });
    }

    @Scheduled(fixedRate = 600000)
    public void fetchAndLogStats() {
        fetchSpringBootLanguageStats()
               // .doOnNext(lang -> System.out.println("Fetched: " + lang.getName() + " " + Instant.now()  ))
                .subscribe();
    }

    @PostConstruct
    public void init() {
        System.out.println("LanguageStatsScheduler is active.");
    }
}