package com.careerconnect.service;

import com.careerconnect.model.InterviewProcessStep;
import com.careerconnect.repository.InterviewProcessStepRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.List;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class InterviewProcessStepServiceTest {

    @Mock
    private InterviewProcessStepRepository repo;

    @InjectMocks
    private InterviewProcessStepService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAll_ReturnsSteps() {
        InterviewProcessStep step1 = new InterviewProcessStep();
        InterviewProcessStep step2 = new InterviewProcessStep();
        when(repo.findAll()).thenReturn(Arrays.asList(step1, step2));

        List<InterviewProcessStep> result = service.getAll();

        assertThat(result).hasSize(2);
        verify(repo, times(1)).findAll();
    }

    @Test
    void testSave_CallsRepository() {
        InterviewProcessStep step = new InterviewProcessStep();

        service.save(step);

        verify(repo, times(1)).save(step);
    }

    @Test
    void testDelete_CallsRepository() {
        Long id = 1L;

        service.delete(id);

        verify(repo, times(1)).deleteById(id);
    }
}
