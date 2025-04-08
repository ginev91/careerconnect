package com.careerconnect.jobservice.controller;

import com.careerconnect.jobservice.service.JobListingRestService;
import com.careerconnect.careerconnectcommon.model.JobListing;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobRestController {

    private final JobListingRestService jobService;

    public JobRestController(JobListingRestService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public List<JobListing> getAllJobs() {
        return jobService.getAll();
    }

    @PostMapping
    public ResponseEntity<JobListing> createJob(@RequestBody JobListing job) {
        JobListing saved = jobService.save(job);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable Long id) {
        jobService.delete(id);
        return ResponseEntity.noContent().build();
    }
}