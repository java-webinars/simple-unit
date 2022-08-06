package edu.javacourse.unit.systems;

import edu.javacourse.unit.systems.exception.AlfrescoException;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class AlfrescoSystem
{
    public String uploadFile(InputStream fileStream, String fileName) throws AlfrescoException {
        throw new AlfrescoException("AlfrescoSystem.uploadFile is not implemented");
    }

    public void setPermissions(String alfrescoId) throws AlfrescoException {
        throw new AlfrescoException("AlfrescoSystem.setPermissions is not implemented");
    }
}
