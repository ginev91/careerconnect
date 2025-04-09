package com.careerconnect.controller;

import com.careerconnect.dto.ProfileUpdateDTO;
import com.careerconnect.model.User;
import com.careerconnect.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
        model.addAttribute("profileUpdateDTO", new ProfileUpdateDTO());
        return "profile";
    }

    // ✅ Handle updates (qualifications, later files)
    @PostMapping("/update")
    public String updateProfile(@Valid @ModelAttribute("profileUpdateDTO") ProfileUpdateDTO profileUpdateDTO,
                              BindingResult bindingResult,
                              Model model,
                              Principal principal) {
        if (bindingResult.hasErrors()) {
            User user = userService.findByUsername(principal.getName());
            model.addAttribute("user", user);
            return "profile";
        }

        userService.updateQualifications(principal.getName(), profileUpdateDTO.getQualifications());
        return "redirect:/profile";
    }
}
