package edu.javacourse.unit.rest;

import edu.javacourse.unit.dto.CompanyDto;
import edu.javacourse.unit.manager.CompanyManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/company")
public class CompanyController {
    @Autowired
    private CompanyManager companyManager;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CompanyDto> findCompanies() {
        return companyManager.findCompanies();
    }
}
