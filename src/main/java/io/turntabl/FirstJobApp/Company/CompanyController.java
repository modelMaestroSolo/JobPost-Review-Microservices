package io.turntabl.FirstJobApp.Company;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.awt.image.ReplicateScaleFilter;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies(){
        return ResponseEntity.ok(companyService.retrieveAllCompany());
    }

    @PostMapping
    public ResponseEntity<String> registerCompany(@RequestBody Company company){
        boolean isRegistered = companyService.existsByEmail(company);
        if(isRegistered)
            return new ResponseEntity<>("Company already registered", HttpStatus.BAD_REQUEST);
        Company registeredCompany = companyService.createCompany(company);
        Long id = registeredCompany.getId();
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
        return ResponseEntity.created(location).body("Company registered successfully!" + id);
    }


}
