package com.careerconnect.controller;

import com.careerconnect.dto.UserDTO;
import com.careerconnect.service.UserService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model, Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            return "redirect:/dashboard";
        }
        model.addAttribute("userDTO", new UserDTO());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("userDTO") @Valid UserDTO userDTO,
                               BindingResult bindingResult,
                               Model model) {
        if (bindingResult.hasErrors()) {
            return "register";
        }

        if (userService.usernameExists(userDTO.getUsername())) {
            model.addAttribute("usernameError", "Username is already taken.");
            return "register";
        }

        userService.registerUser(userDTO.getUsername(), userDTO.getEmail(), userDTO.getPassword());
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            return "redirect:/dashboard";
        }
        return "login";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            // Add authenticated user data to model
            String username = authentication.getName();
            model.addAttribute("username", username);
            model.addAttribute("isAuthenticated", true);
            // Add any other user-specific data needed for the dashboard
        } else {
            model.addAttribute("isAuthenticated", false);
        }
        return "dashboard";
    }
}
