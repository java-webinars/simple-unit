package edu.javacourse.unit.systems;

import edu.javacourse.unit.systems.exception.AmazonException;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class AmazonSystem
{
    public String uploadFile(InputStream fileStream, String fileName) throws AmazonException {
        throw new AmazonException("AmazonSystem.uploadFile is not implemented");
    }
}
