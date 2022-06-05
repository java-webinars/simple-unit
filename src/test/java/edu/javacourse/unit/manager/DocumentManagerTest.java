package edu.javacourse.unit.manager;


import org.junit.jupiter.api.Test;

class DocumentManagerTest
{
    @Test
    void uploadFile() {
        DocumentManager dm = new DocumentManager();
        dm.uploadFile(null, null, null, true, true);
    }
}