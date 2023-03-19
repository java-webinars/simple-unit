package edu.javacourse.unit.manager;

import edu.javacourse.unit.dao.CompanyRepository;
import edu.javacourse.unit.dao.DocumentRepository;
import edu.javacourse.unit.systems.AlfrescoSystem;
import edu.javacourse.unit.systems.AmazonSystem;
import edu.javacourse.unit.systems.FileChecker;
import edu.javacourse.unit.systems.exception.AlfrescoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

class DocumentManagerTest
{
    public static final Long COMPANY_ID = 1L;

    private DocumentManager documentManager;

    @BeforeEach
    void setUp() {
        initDocumentManager();
    }

    @Test
    void uploadFile() {
        ByteArrayInputStream ba = new ByteArrayInputStream(new byte[20]);
        documentManager.uploadFile(ba, "test.txt", COMPANY_ID, true, true);
    }

    private void initDocumentManager() {
        documentManager = new DocumentManager();

        FileChecker fileChecker = new FileChecker();
        ReflectionTestUtils.setField(documentManager, "fileChecker", fileChecker);

        AlfrescoSystem alfrescoSystem = Mockito.mock(AlfrescoSystem.class);
        ReflectionTestUtils.setField(documentManager, "alfrescoSystem", alfrescoSystem);

        AmazonSystem amazonSystem = Mockito.mock(AmazonSystem.class);
        ReflectionTestUtils.setField(documentManager, "amazonSystem", amazonSystem);

        CompanyRepository companyDao = Mockito.mock(CompanyRepository.class);
        ReflectionTestUtils.setField(documentManager, "companyDao", companyDao);

        DocumentRepository documentDao = Mockito.mock(DocumentRepository.class);
        ReflectionTestUtils.setField(documentManager, "documentDao", documentDao);
    }
}