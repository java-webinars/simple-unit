package edu.javacourse.unit.manager;

import edu.javacourse.unit.dao.CompanyRepository;
import edu.javacourse.unit.dao.DocumentRepository;
import edu.javacourse.unit.systems.AlfrescoSystem;
import edu.javacourse.unit.systems.AmazonSystem;
import edu.javacourse.unit.systems.FileChecker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.ByteArrayInputStream;

@ExtendWith(MockitoExtension.class)
class DocumentManagerTest
{
    public static final Long COMPANY_ID = 1L;

    @Mock(name = "alfrescoSystem")
    private AlfrescoSystem alfrescoSystem;
    @Mock(name = "amazonSystem")
    private AmazonSystem amazonSystem;
    @Mock(name = "companyDao")
    private CompanyRepository companyDao;
    @Mock(name = "documentDao")
    private DocumentRepository documentDao;

    @InjectMocks
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
        FileChecker fileChecker = new FileChecker();
        ReflectionTestUtils.setField(documentManager, "fileChecker", fileChecker);
    }
}