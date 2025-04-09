package com.careerconnect.controller;

import com.careerconnect.model.Company;
import com.careerconnect.model.ExperienceReview;
import com.careerconnect.service.CompanyService;
import com.careerconnect.service.ExperienceReviewService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService companyService;
    private final ExperienceReviewService reviewService;

    public CompanyController(CompanyService companyService, ExperienceReviewService reviewService) {
        this.companyService = companyService;
        this.reviewService = reviewService;
    }

    @GetMapping
    public String showCompanies(Model model) {
        List<Company> companies = companyService.getAllCompanies();
        Map<Long, List<ExperienceReview>> companyReviews = companies.stream()
                .collect(Collectors.toMap(
                    Company::getId,
                    company -> reviewService.getByCompanyId(company.getId())
                ));

        model.addAttribute("companies", companies);
        model.addAttribute("companyReviews", companyReviews);
        return "companies";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("company", new Company());
        return "company-add";
    }

    @PostMapping("/add")
    public String addCompany(@ModelAttribute("company") @Valid Company company,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "company-add";
        }

        companyService.addCompany(company);
        return "redirect:/companies";
    }

    @GetMapping("/delete/{id}")
    public String deleteCompany(@PathVariable Long id) {
        companyService.deleteById(id);
        return "redirect:/companies";
    }
}
