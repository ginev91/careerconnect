package com.careerconnect.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/moderator")
@PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
public class ModeratorController {

    @GetMapping
    public String moderatorDashboard(Model model) {
        // Add any necessary model attributes
        return "moderator/dashboard";
    }

    @PostMapping("/reviews/{reviewId}/approve")
    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
    public String approveReview(@PathVariable Long reviewId) {
        // Implementation for approving reviews
        return "redirect:/moderator";
    }

    @PostMapping("/reviews/{reviewId}/reject")
    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
    public String rejectReview(@PathVariable Long reviewId) {
        // Implementation for rejecting reviews
        return "redirect:/moderator";
    }
} 