package io.turntabl.FirstJobApp.Company;

import java.util.List;

public interface CompanyService {

    List<Company> retrieveAllCompany();

    boolean existsByEmail(Company company);

    Company createCompany(Company company);
}
