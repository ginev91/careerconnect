package com.careerconnect.controller;

import com.careerconnect.model.Company;
import com.careerconnect.model.InterviewProcessStep;
import com.careerconnect.service.CompanyService;
import com.careerconnect.service.InterviewProcessStepService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/steps")
public class InterviewProcessController {

    private final InterviewProcessStepService stepService;
    private final CompanyService companyService;

    public InterviewProcessController(InterviewProcessStepService stepService,
                                      CompanyService companyService) {
        this.stepService = stepService;
        this.companyService = companyService;
    }

    @GetMapping
    public String showSteps(Model model) {
        model.addAttribute("steps", stepService.getAll());
        return "steps";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("step", new InterviewProcessStep());
        model.addAttribute("companies", companyService.getAllCompanies());
        return "step-add";
    }

    @PostMapping("/add")
    public String addStep(@ModelAttribute("step") @Valid InterviewProcessStep step,
                          BindingResult result,
                          Model model) {
        if (result.hasErrors()) {
            model.addAttribute("companies", companyService.getAllCompanies());
            return "step-add";
        }

        stepService.save(step);
        return "redirect:/steps";
    }

    @GetMapping("/delete/{id}")
    public String deleteStep(@PathVariable Long id) {
        stepService.delete(id);
        return "redirect:/steps";
    }
}