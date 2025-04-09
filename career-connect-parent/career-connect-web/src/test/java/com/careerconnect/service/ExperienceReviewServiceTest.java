package com.careerconnect.service;

import com.careerconnect.model.ExperienceReview;
import com.careerconnect.repository.ExperienceReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class ExperienceReviewServiceTest {

    @Mock
    private ExperienceReviewRepository repo;

    @InjectMocks
    private ExperienceReviewService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getByCompanyId_ShouldReturnListOfReviews() {
        Long companyId = 1L;
        ExperienceReview review1 = new ExperienceReview();
        ExperienceReview review2 = new ExperienceReview();

        when(repo.findByCompanyId(companyId)).thenReturn(Arrays.asList(review1, review2));

        List<ExperienceReview> reviews = service.getByCompanyId(companyId);

        assertThat(reviews).hasSize(2);
        verify(repo, times(1)).findByCompanyId(companyId);
    }

    @Test
    void save_ShouldCallRepoSave() {
        ExperienceReview review = new ExperienceReview();

        service.save(review);

        verify(repo, times(1)).save(review);
    }

    @Test
    void delete_ShouldCallRepoDeleteById() {
        Long id = 42L;

        service.delete(id);

        verify(repo, times(1)).deleteById(id);
    }
}