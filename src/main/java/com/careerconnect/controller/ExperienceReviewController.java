package com.careerconnect.controller;

import com.careerconnect.model.Company;
import com.careerconnect.model.ExperienceReview;
import com.careerconnect.model.User;
import com.careerconnect.service.CompanyService;
import com.careerconnect.service.ExperienceReviewService;
import com.careerconnect.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/reviews")
public class ExperienceReviewController {

    private final ExperienceReviewService reviewService;
    private final CompanyService companyService;
    private final UserService userService;

    public ExperienceReviewController(ExperienceReviewService reviewService,
                                      CompanyService companyService,
                                      UserService userService) {
        this.reviewService = reviewService;
        this.companyService = companyService;
        this.userService = userService;
    }

    @GetMapping
    public String showReviews(Model model) {
        model.addAttribute("reviews", reviewService.getAll());
        return "reviews";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("review", new ExperienceReview());
        model.addAttribute("companies", companyService.getAllCompanies());
        model.addAttribute("users", userService.getAllUsers());
        return "review-add";
    }

    @PostMapping("/add")
    public String addReview(@ModelAttribute("review") @Valid ExperienceReview review,
                            BindingResult result,
                            Model model) {
        if (result.hasErrors()) {
            model.addAttribute("companies", companyService.getAllCompanies());
            model.addAttribute("users", userService.getAllUsers());
            return "review-add";
        }
        reviewService.save(review);
        return "redirect:/reviews";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        reviewService.delete(id);
        return "redirect:/reviews";
    }
}