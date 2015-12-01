package com;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Agnieszka on 29.11.2015.
 */
public class MessageReaderWriter {
    public void writeMessage(String fileName, BigInteger message) throws IOException {
        FileOutputStream outputStream = new FileOutputStream(fileName);
        //IOUtils.write(message.toByteArray(), outputStream);
        outputStream.write(message.toByteArray());
        outputStream.close();
    }

    public BigInteger readMessage(String inFileName, int messageSize) throws IOException {
        Path inputPath = Paths.get(inFileName);
        byte[] bytes = Files.readAllBytes(inputPath);
        BigInteger message = new BigInteger(bytes);
        return message;
    }
}
