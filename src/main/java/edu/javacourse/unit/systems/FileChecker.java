package edu.javacourse.unit.systems;

import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class FileChecker
{
    public boolean checkFile(InputStream file, String fileName) {
        return !fileName.endsWith(".exe");
    }
}
