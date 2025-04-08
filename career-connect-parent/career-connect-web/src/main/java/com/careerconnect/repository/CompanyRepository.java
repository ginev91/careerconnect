package com.careerconnect.repository;

import com.careerconnect.careerconnectcommon.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
