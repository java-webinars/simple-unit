package edu.javacourse.unit.manager;

import edu.javacourse.unit.dao.CompanyRepository;
import edu.javacourse.unit.dto.CompanyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyManager {

    @Autowired
    private CompanyRepository companyDao;

    public List<CompanyDto> findCompanies() {
        return companyDao.findAll().stream()
                .map(c -> new CompanyDto(c.getCompanyId(), c.getCompanyName()))
                .collect(Collectors.toList());
    }
}
