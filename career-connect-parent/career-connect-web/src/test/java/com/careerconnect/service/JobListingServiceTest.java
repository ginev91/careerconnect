package com.careerconnect.service;

import com.careerconnect.careerconnectcommon.model.JobListing;
import com.careerconnect.repository.JobListingRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class JobListingServiceTest {

    @Mock
    private JobListingRepository repo;

    @InjectMocks
    private JobListingService service;

    @Test
    void testGetAllReturnsList() {
        JobListing job = new JobListing();
        job.setTitle("Test Job");
        when(repo.findAll()).thenReturn(List.of(job));

        List<JobListing> result = service.getAll();

        assertEquals(1, result.size());
        assertEquals("Test Job", result.get(0).getTitle());
    }

    @Test
    void testSaveJob() {
        JobListing job = new JobListing();
        job.setTitle("Save Me");

        when(repo.save(job)).thenReturn(job);

        JobListing saved = service.save(job);
        assertEquals("Save Me", saved.getTitle());
    }

    @Test
    void testDeleteJob() {
        service.delete(1L);
        verify(repo, times(1)).deleteById(1L);
    }
}