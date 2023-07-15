package edu.javacourse.unit.manager;

import edu.javacourse.unit.dao.CompanyRepository;
import edu.javacourse.unit.dao.DocumentRepository;
import edu.javacourse.unit.domain.Document;
import edu.javacourse.unit.systems.AlfrescoSystem;
import edu.javacourse.unit.systems.AmazonSystem;
import edu.javacourse.unit.systems.FileChecker;
import edu.javacourse.unit.systems.exception.AlfrescoException;
import edu.javacourse.unit.systems.exception.AmazonException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

    private FileChecker fileChecker;

    @Captor
    private ArgumentCaptor<Document> docCaptor;

    @InjectMocks
    private DocumentManager documentManager;

    @BeforeEach
    void setUp() throws Exception {
        initDocumentManager();
    }

    @Test
    void uploadFile() throws AlfrescoException, AmazonException {
        ByteArrayInputStream ba = new ByteArrayInputStream(new byte[20]);
//        ArgumentCaptor<Document> docCaptor = ArgumentCaptor.forClass(Document.class);

        documentManager.uploadFile(ba, "test.txt", COMPANY_ID, true, true);

        verify(fileChecker, times(1)).checkFile(any(), any());
        verify(alfrescoSystem, times(1)).uploadFile(any(), any());
        verify(amazonSystem, times(1)).uploadFile(any(), any());
        verify(alfrescoSystem, times(1)).setPermissions(any());
        verify(companyDao, times(1)).getReferenceById(eq(COMPANY_ID));
        verify(documentDao, times(1)).save(any());

        verify(documentDao).save(docCaptor.capture());
        Document doc = docCaptor.getValue();
        Assertions.assertEquals("Alfresco12345", doc.getAlfrescoId());
        Assertions.assertEquals("Amazon12345", doc.getAmazonId());
    }

    private void initDocumentManager() throws Exception {
        fileChecker = Mockito.spy(new FileChecker());
        ReflectionTestUtils.setField(documentManager, "fileChecker", fileChecker);

        when(alfrescoSystem.uploadFile(any(), any())).thenReturn("Alfresco12345");
        when(amazonSystem.uploadFile(any(), any())).thenReturn("Amazon12345");
    }
}