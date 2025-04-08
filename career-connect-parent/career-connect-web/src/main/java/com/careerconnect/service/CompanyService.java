package com.careerconnect.service;

import com.careerconnect.careerconnectcommon.model.Company;
import com.careerconnect.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public void addCompany(Company company) {
        companyRepository.save(company);
    }

    public Company getById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        companyRepository.deleteById(id);
    }
}
