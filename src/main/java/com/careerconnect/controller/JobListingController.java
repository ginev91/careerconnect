package com.careerconnect.controller;

import com.careerconnect.model.Company;
import com.careerconnect.model.JobListing;
import com.careerconnect.service.CompanyService;
import com.careerconnect.service.JobListingService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/jobs")
public class JobListingController {

    private final JobListingService jobService;
    private final CompanyService companyService;

    public JobListingController(JobListingService jobService, CompanyService companyService) {
        this.jobService = jobService;
        this.companyService = companyService;
    }

    @GetMapping
    public String showJobs(Model model) {
        model.addAttribute("jobs", jobService.getAll());
        return "jobs";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("jobListing", new JobListing());
        model.addAttribute("companies", companyService.getAllCompanies());
        return "job-add";
    }

    @PostMapping("/add")
    public String addJob(@ModelAttribute("jobListing") @Valid JobListing jobListing,
                         BindingResult result,
                         Model model) {
        if (result.hasErrors()) {
            model.addAttribute("companies", companyService.getAllCompanies());
            return "job-add";
        }

        jobService.save(jobListing);
        return "redirect:/jobs";
    }

    @GetMapping("/delete/{id}")
    public String deleteJob(@PathVariable Long id) {
        jobService.delete(id);
        return "redirect:/jobs";
    }

    @GetMapping("/stream")
    public String showJobStream(Model model) {
        model.addAttribute("jobs", jobService.getAll());
        return "job-stream";
    }
}