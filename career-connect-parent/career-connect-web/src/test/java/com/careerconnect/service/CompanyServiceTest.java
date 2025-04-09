package com.careerconnect.service;

import com.careerconnect.model.Company;
import com.careerconnect.repository.CompanyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class CompanyServiceTest {

    @Mock
    private CompanyRepository companyRepository;

    @InjectMocks
    private CompanyService companyService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllCompanies_ShouldReturnAllCompanies() {
        Company c1 = new Company();
        Company c2 = new Company();

        when(companyRepository.findAll()).thenReturn(Arrays.asList(c1, c2));

        List<Company> result = companyService.getAllCompanies();

        assertThat(result).hasSize(2);
        verify(companyRepository, times(1)).findAll();
    }

    @Test
    void addCompany_ShouldCallSave() {
        Company company = new Company();
        companyService.addCompany(company);

        verify(companyRepository, times(1)).save(company);
    }

    @Test
    void getById_ShouldReturnCompany_WhenFound() {
        Company company = new Company();
        // company.setId(1L);

        when(companyRepository.findById(1L)).thenReturn(Optional.of(company));

        Company result = companyService.getById(1L);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
    }

    @Test
    void getById_ShouldReturnNull_WhenNotFound() {
        when(companyRepository.findById(99L)).thenReturn(Optional.empty());

        Company result = companyService.getById(99L);

        assertThat(result).isNull();
    }

    @Test
    void deleteById_ShouldCallDeleteById() {
        companyService.deleteById(5L);

        verify(companyRepository, times(1)).deleteById(5L);
    }
}