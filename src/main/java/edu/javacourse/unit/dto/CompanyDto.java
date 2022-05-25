package edu.javacourse.unit.dto;

public class CompanyDto {
    private Long companyId;
    private String companyName;

    public CompanyDto() {
    }

    public CompanyDto(Long companyId, String companyName) {
        this.companyId = companyId;
        this.companyName = companyName;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
