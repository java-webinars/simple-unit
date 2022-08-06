package edu.javacourse.unit.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "document")
public class Document
{
    @Id
    @Column(name = "document_id")
    private Long documentId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id", referencedColumnName = "company_id")
    private Company company;
    @Column(name = "file_name")
    private String documentName;
    @Column(name = "alfresco_id")
    private String alfrescoId;
    @Column(name = "amazon_id")
    private String amazonId;

    public Long getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Long documentId) {
        this.documentId = documentId;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getAlfrescoId() {
        return alfrescoId;
    }

    public void setAlfrescoId(String alfrescoId) {
        this.alfrescoId = alfrescoId;
    }

    public String getAmazonId() {
        return amazonId;
    }

    public void setAmazonId(String amazonId) {
        this.amazonId = amazonId;
    }
}
