package com.careerconnect.controller;

import com.careerconnect.model.User;
import com.careerconnect.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String adminDashboard(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin/dashboard";
    }

    @PostMapping("/users/{userId}/role")
    @PreAuthorize("hasRole('ADMIN')")
    public String updateUserRole(@PathVariable Long userId, @RequestParam String role) {
        // Implementation for updating user roles
        return "redirect:/admin";
    }

    @PostMapping("/users/{userId}/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteUser(@PathVariable Long userId) {
        // Implementation for deleting users
        return "redirect:/admin";
    }
} 