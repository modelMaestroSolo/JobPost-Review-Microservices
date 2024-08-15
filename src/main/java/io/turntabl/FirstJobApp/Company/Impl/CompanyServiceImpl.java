package io.turntabl.FirstJobApp.Company.Impl;

import io.turntabl.FirstJobApp.Company.Company;
import io.turntabl.FirstJobApp.Company.CompanyRepository;
import io.turntabl.FirstJobApp.Company.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> retrieveAllCompany() {
        return companyRepository.findAll();
    }

    @Override
    public boolean existsByEmail(Company company) {
        return companyRepository.existsByEmail(company.getEmail());
    }

    @Override
    public Company createCompany(Company company) {
        return companyRepository.save(company);
    }
}
