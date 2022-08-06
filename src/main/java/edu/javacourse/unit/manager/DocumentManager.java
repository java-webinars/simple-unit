package edu.javacourse.unit.manager;

import edu.javacourse.unit.dao.CompanyRepository;
import edu.javacourse.unit.dao.DocumentRepository;
import edu.javacourse.unit.domain.Company;
import edu.javacourse.unit.domain.Document;
import edu.javacourse.unit.systems.AlfrescoSystem;
import edu.javacourse.unit.systems.AmazonSystem;
import edu.javacourse.unit.systems.FileChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class DocumentManager
{
    @Autowired
    private FileChecker fileChecker;
    @Autowired
    private AlfrescoSystem alfrescoSystem;
    @Autowired
    private AmazonSystem amazonSystem;
    @Autowired
    private CompanyRepository companyDao;
    @Autowired
    private DocumentRepository documentDao;

    public void uploadFile(InputStream file, String fileName, Long companyId,
                           boolean external, boolean restricted) {
        if (fileChecker.checkFile(file, fileName)) {
            try {
                String alfrescoId = alfrescoSystem.uploadFile(file, fileName);

                String amazonId = null;
                if (external) {
                    amazonId = amazonSystem.uploadFile(file, fileName);
                }

                if (restricted) {
                    alfrescoSystem.setPermissions(alfrescoId);
                }

                saveDocument(fileName, companyId, alfrescoId, amazonId);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }

    private void saveDocument(String fileName, Long companyId, String alfrescoId, String amazonId) {
        Document doc = new Document();
        doc.setDocumentName(fileName);
        doc.setAlfrescoId(alfrescoId);
        doc.setAmazonId(amazonId);
        Company company = companyDao.getReferenceById(companyId);
        doc.setCompany(company);
        documentDao.save(doc);
    }
}
