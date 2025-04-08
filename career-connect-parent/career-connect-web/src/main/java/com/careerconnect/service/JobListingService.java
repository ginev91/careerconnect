package com.careerconnect.service;

import com.careerconnect.careerconnectcommon.model.JobListing;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.List;
import java.util.Arrays;

@Service
public class JobListingService {

    private final WebClient webClient;

    public JobListingService(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("http://localhost:8081/api/jobs").build();
    }

    public List<JobListing> getAll() {
        return webClient.get()
                .retrieve()
                .bodyToMono(JobListing[].class)
                .map(Arrays::asList)
                .block();
    }

    public JobListing save(JobListing job) {
        return webClient.post()
                .bodyValue(job)
                .retrieve()
                .bodyToMono(JobListing.class)
                .block();
    }

    public void delete(Long id) {
        webClient.delete()
                .uri("/{id}", id)
                .retrieve()
                .toBodilessEntity()
                .block();
    }
}