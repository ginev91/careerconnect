package com.careerconnect.controller;

import com.careerconnect.model.Company;
import com.careerconnect.model.ExperienceReview;
import com.careerconnect.model.User;
import com.careerconnect.service.CompanyService;
import com.careerconnect.service.ExperienceReviewService;
import com.careerconnect.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ExperienceReviewController.class)
public class ExperienceReviewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExperienceReviewService reviewService;

    @MockBean
    private CompanyService companyService;

    @MockBean
    private UserService userService;

    @Test
    void showReviewsForCompany_shouldReturnReviewsPage() throws Exception {
        Mockito.when(reviewService.getByCompanyId(1L)).thenReturn(List.of());

        mockMvc.perform(get("/reviews/company/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("reviews"))
                .andExpect(model().attributeExists("reviews"))
                .andExpect(model().attributeExists("companyId"));
    }

    @Test
    void showAddForm_shouldReturnFormView() throws Exception {
        Mockito.when(companyService.getAllCompanies()).thenReturn(List.of());
        Mockito.when(userService.getAllUsers()).thenReturn(List.of());

        mockMvc.perform(get("/reviews/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("review-add"))
                .andExpect(model().attributeExists("review"))
                .andExpect(model().attributeExists("companies"))
                .andExpect(model().attributeExists("users"));
    }

    @Test
    void addReview_valid_shouldRedirect() throws Exception {
        Company company = new Company();
        //  company.setId(1L);

        Mockito.when(companyService.getAllCompanies()).thenReturn(List.of(company));
        Mockito.when(userService.getAllUsers()).thenReturn(List.of());

        mockMvc.perform(post("/reviews/add")
                        .param("rating", "5")
                        .param("title", "Great Experience")
                        .param("comment", "It was amazing")
                        .param("company.id", "1")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/reviews/company/1"));
    }

    @Test
    void deleteReview_shouldRedirect() throws Exception {
        mockMvc.perform(get("/reviews/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/reviews"));

        Mockito.verify(reviewService).delete(1L);
    }
}