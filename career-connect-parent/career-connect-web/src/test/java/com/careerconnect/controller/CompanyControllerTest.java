package com.careerconnect.controller;

import com.careerconnect.model.Company;
import com.careerconnect.service.CompanyService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CompanyController.class)
class CompanyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CompanyService companyService;

    @Test
    void showCompanies_shouldReturnCompaniesPage() throws Exception {
        Mockito.when(companyService.getAllCompanies()).thenReturn(List.of());

        mockMvc.perform(get("/companies"))
                .andExpect(status().isOk())
                .andExpect(view().name("companies"))
                .andExpect(model().attributeExists("companies"));
    }

    @Test
    void showAddForm_shouldReturnFormView() throws Exception {
        mockMvc.perform(get("/companies/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("company-add"))
                .andExpect(model().attributeExists("company"));
    }

    @Test
    void addCompany_shouldRedirectAfterSuccess() throws Exception {
        mockMvc.perform(post("/companies/add")
                        .param("name", "Test Company")
                        .param("location", "Sofia")
                        .param("description", "Test description")
                        .param("website", "http://example.com")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/companies"));

        verify(companyService).addCompany(Mockito.any(Company.class));
    }

    @Test
    void deleteCompany_shouldRedirect() throws Exception {
        mockMvc.perform(get("/companies/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/companies"));

        verify(companyService).deleteById(1L);
    }

    @Test
    void addCompany_withInvalidInput_shouldReturnFormView() throws Exception {
        mockMvc.perform(post("/companies/add")
                        .param("name", "")  // Invalid name
                        .param("location", "Sofia")
                        .param("description", "")
                        .param("website", "http://example.com")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andExpect(view().name("company-add"));
    }
}