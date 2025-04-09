package com.careerconnect.jobservice.controller;

import com.careerconnect.careerconnectcommon.model.JobListing;
import com.careerconnect.jobservice.service.JobListingRestService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(JobRestController.class)
public class JobRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JobListingRestService jobService;

    @Test
    void getAllJobs_returnsOk() throws Exception {
        Mockito.when(jobService.getAll()).thenReturn(List.of());

        mockMvc.perform(get("/api/jobs"))
                .andExpect(status().isOk());
    }
}