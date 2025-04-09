package com.careerconnect.controller;

import com.careerconnect.dto.ProfileUpdateDTO;
import com.careerconnect.model.User;
import com.careerconnect.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.security.Principal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ProfileControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @Mock
    private Model model;

    @Mock
    private Principal principal;

    @InjectMocks
    private ProfileController profileController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(profileController).build();
    }

    @Test
    void showProfile_ShouldReturnProfileView() throws Exception {
        // Arrange
        User user = new User();
        user.setUsername("testuser");
        user.setQualifications("Java, Spring Boot");
        
        when(principal.getName()).thenReturn("testuser");
        when(userService.findByUsername("testuser")).thenReturn(user);

        // Act & Assert
        mockMvc.perform(get("/profile")
                .principal(principal))
            .andExpect(status().isOk())
            .andExpect(view().name("profile"))
            .andExpect(model().attributeExists("user"))
            .andExpect(model().attributeExists("profileUpdateDTO"))
            .andExpect(model().attribute("user", user));

        verify(userService, times(1)).findByUsername("testuser");
    }

    @Test
    void updateProfile_WithValidData_ShouldUpdateAndRedirect() throws Exception {
        // Arrange
        String validQualifications = "Java, Spring Boot, React, and other relevant technologies";
        when(principal.getName()).thenReturn("testuser");

        // Act & Assert
        mockMvc.perform(post("/profile/update")
                .principal(principal)
                .param("qualifications", validQualifications))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/profile"));

        verify(userService, times(1)).updateQualifications("testuser", validQualifications);
    }

    @Test
    void updateProfile_WithEmptyQualifications_ShouldShowErrors() throws Exception {
        // Arrange
        String emptyQualifications = "";
        when(principal.getName()).thenReturn("testuser");
        User user = new User();
        user.setUsername("testuser");
        when(userService.findByUsername("testuser")).thenReturn(user);

        // Act & Assert
        mockMvc.perform(post("/profile/update")
                .principal(principal)
                .param("qualifications", emptyQualifications))
            .andExpect(status().isOk())
            .andExpect(view().name("profile"))
            .andExpect(model().attributeExists("user"))
            .andExpect(model().attributeHasFieldErrors("profileUpdateDTO", "qualifications"));

        verify(userService, never()).updateQualifications(any(), any());
    }

    @Test
    void updateProfile_WithTooShortQualifications_ShouldShowErrors() throws Exception {
        // Arrange
        String shortQualifications = "Java";
        when(principal.getName()).thenReturn("testuser");
        User user = new User();
        user.setUsername("testuser");
        when(userService.findByUsername("testuser")).thenReturn(user);

        // Act & Assert
        mockMvc.perform(post("/profile/update")
                .principal(principal)
                .param("qualifications", shortQualifications))
            .andExpect(status().isOk())
            .andExpect(view().name("profile"))
            .andExpect(model().attributeExists("user"))
            .andExpect(model().attributeHasFieldErrors("profileUpdateDTO", "qualifications"));

        verify(userService, never()).updateQualifications(any(), any());
    }

    @Test
    void showProfile_WithNonExistentUser_ShouldHandleError() throws Exception {
        // Arrange
        when(principal.getName()).thenReturn("nonexistent");
        when(userService.findByUsername("nonexistent")).thenReturn(null);

        // Act & Assert
        mockMvc.perform(get("/profile")
                .principal(principal))
            .andExpect(status().isOk())
            .andExpect(view().name("profile"))
            .andExpect(model().attributeExists("user"))
            .andExpect(model().attributeExists("profileUpdateDTO"));

        verify(userService, times(1)).findByUsername("nonexistent");
    }
} 