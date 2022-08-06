package edu.javacourse.unit.systems;

import java.io.InputStream;

public class FileChecker
{
    public boolean checkFile(InputStream file, String fileName) {
        return !fileName.endsWith(".exe");
    }
}
