package com.careerconnect.controller;

import com.careerconnect.model.User;
import com.careerconnect.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    private final UserService userService;

    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    // ✅ Show profile page
    @GetMapping
    public String showProfile(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        return "profile";
    }

    // ✅ Handle updates (qualifications, later files)
    @PostMapping("/update")
    public String updateProfile(@ModelAttribute("user") User updatedUser, Principal principal) {
        userService.updateQualifications(principal.getName(), updatedUser.getQualifications());
        return "redirect:/profile";
    }
}
