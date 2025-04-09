package com.careerconnect.controller;

import com.careerconnect.careerconnectcommon.model.JobListing;
import com.careerconnect.service.JobListingService;
import com.careerconnect.service.CompanyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class JobListingControllerTest {

    private MockMvc mockMvc;

    @Mock
    private JobListingService jobListingService;

    @Mock
    private CompanyService companyService;

    @Mock
    private Model model;

    @InjectMocks
    private JobListingController jobListingController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(jobListingController).build();
    }

    @Test
    void showJobs_ShouldReturnJobsView() throws Exception {
        // Arrange
        List<JobListing> jobs = new ArrayList<>();
        JobListing job1 = new JobListing();
        job1.setTitle("Software Engineer");
        job1.setCompanyId(1L);
        jobs.add(job1);
        
        JobListing job2 = new JobListing();
        job2.setTitle("Data Scientist");
        job2.setCompanyId(2L);
        jobs.add(job2);
        
        when(jobListingService.getAll()).thenReturn(jobs);
        when(companyService.getAllCompanies()).thenReturn(new ArrayList<>());

        // Act & Assert
        mockMvc.perform(get("/jobs"))
            .andExpect(status().isOk())
            .andExpect(view().name("jobs"));
    }

    @Test
    void showAddForm_ShouldReturnJobAddView() throws Exception {
        // Arrange
        when(companyService.getAllCompanies()).thenReturn(new ArrayList<>());

        // Act & Assert
        mockMvc.perform(get("/jobs/add"))
            .andExpect(status().isOk())
            .andExpect(view().name("job-add"));
    }

    @Test
    void addJob_WithValidJob_ShouldRedirectToJobs() throws Exception {
        // Arrange
        JobListing jobListing = new JobListing();
        jobListing.setTitle("Software Engineer");
        jobListing.setCompanyId(1L);
        when(companyService.getAllCompanies()).thenReturn(new ArrayList<>());

        // Act & Assert
        mockMvc.perform(post("/jobs/add")
                .param("title", "Software Engineer")
                .param("companyId", "1")
                .param("description", "Description"))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/jobs"));
    }

    @Test
    void deleteJob_ShouldRedirectToJobs() throws Exception {
        // Act & Assert
        mockMvc.perform(get("/jobs/delete/1"))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/jobs"));

        verify(jobListingService, times(1)).delete(1L);
    }

    @Test
    void showJobStream_ShouldReturnLanguageStatsView() throws Exception {
        // Arrange
        List<JobListing> jobs = new ArrayList<>();
        JobListing job = new JobListing();
        job.setTitle("Software Engineer");
        job.setCompanyId(1L);
        jobs.add(job);
        
        when(jobListingService.getAll()).thenReturn(jobs);

        // Act & Assert
        mockMvc.perform(get("/jobs/stream"))
            .andExpect(status().isOk())
            .andExpect(view().name("language-stats"));
    }
} 