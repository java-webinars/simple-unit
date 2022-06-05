package edu.javacourse.unit.manager;

import java.io.InputStream;

public class DocumentManager
{
    private AlfrescoManager alfrescoManager;

    public void uploadFile(InputStream file, String fileName, Long companyId,
                           boolean external, boolean restricted) {
        // CheckFile
        // Save to Alfresco
//        saveToAlfresco();
        alfrescoManager.uploadFile(file, fileName);
        // if (external) - Save to Amazon S3
        // if (restricted) - set permissions in Alfresco
        // Save to DB
    }
}
