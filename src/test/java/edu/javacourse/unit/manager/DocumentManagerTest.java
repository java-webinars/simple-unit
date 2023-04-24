package edu.javacourse.unit.manager;

import edu.javacourse.unit.dao.CompanyRepository;
import edu.javacourse.unit.dao.DocumentRepository;
import edu.javacourse.unit.systems.AlfrescoSystem;
import edu.javacourse.unit.systems.AmazonSystem;
import edu.javacourse.unit.systems.FileChecker;
import edu.javacourse.unit.systems.exception.AlfrescoException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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

    @InjectMocks
    private DocumentManager documentManager;

    @BeforeEach
    void setUp() throws Exception {
        initDocumentManager();
    }

    @Test
    void uploadFile() {
        ByteArrayInputStream ba = new ByteArrayInputStream(new byte[20]);
        documentManager.uploadFile(ba, "test.txt", COMPANY_ID, true, true);
        documentManager.uploadFile(ba, "simple.txt", COMPANY_ID, true, true);
        documentManager.uploadFile(ba, "complex.txt", COMPANY_ID, true, true);

        Assertions.assertThrows(RuntimeException.class,
                () -> documentManager.uploadFile(ba, "exception.txt", COMPANY_ID, true, true));
    }

    private void initDocumentManager() throws Exception {
        FileChecker fileChecker = new FileChecker();
        ReflectionTestUtils.setField(documentManager, "fileChecker", fileChecker);

        when(alfrescoSystem.uploadFile(any(), any())).thenReturn("12345");
        when(alfrescoSystem.uploadFile(isA(InputStream.class), eq("simple.txt"))).thenReturn("67890");
        when(alfrescoSystem.uploadFile(isA(InputStream.class), eq("complex.txt")))
                .thenAnswer(new Answer<String>()
                {
                    @Override
                    public String answer(InvocationOnMock inv) throws Throwable {
                        String s = inv.getArgument(1, String.class);
                        return "Hello " + s;
                    }
                });

        when(alfrescoSystem.uploadFile(isA(InputStream.class), eq("exception.txt")))
                .thenThrow(new AlfrescoException("EXCEPTION"));
    }
}