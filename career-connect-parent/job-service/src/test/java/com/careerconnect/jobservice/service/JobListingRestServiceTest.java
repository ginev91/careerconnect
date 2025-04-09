package com.careerconnect.jobservice.service;

import com.careerconnect.careerconnectcommon.model.JobListing;
import com.careerconnect.jobservice.repository.JobListingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JobListingRestServiceTest {

    @Mock
    private JobListingRepository repo;

    @InjectMocks
    private JobListingRestService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAll_shouldReturnJobList() {
        JobListing job = new JobListing();
        job.setTitle("Java Developer");

        when(repo.findAll()).thenReturn(List.of(job));

        List<JobListing> result = service.getAll();

        assertEquals(1, result.size());
        assertEquals("Java Developer", result.get(0).getTitle());
        verify(repo, times(1)).findAll();
    }

    @Test
    void save_shouldPersistJobListing() {
        JobListing job = new JobListing();
        job.setTitle("Backend Engineer");

        when(repo.save(job)).thenReturn(job);

        JobListing saved = service.save(job);

        assertNotNull(saved);
        assertEquals("Backend Engineer", saved.getTitle());
        verify(repo).save(job);
    }

    @Test
    void save_shouldThrowException_whenRepoFails() {
        JobListing job = new JobListing();
        job.setTitle("Failing Job");

        when(repo.save(job)).thenThrow(new RuntimeException("DB error"));

        RuntimeException ex = assertThrows(RuntimeException.class, () -> service.save(job));
        assertTrue(ex.getMessage().contains("Failed to save job"));

        verify(repo).save(job);
    }

    @Test
    void delete_shouldInvokeRepository() {
        Long id = 42L;

        service.delete(id);

        verify(repo).deleteById(id);
    }
}