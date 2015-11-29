package com;


import org.apache.commons.io.IOUtils;

import java.io.*;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;


/**
 * Created by Agnieszka on 28.11.2015.
 */
public class RsaEngine{
    MessageReaderWriter messageReaderWriter = new MessageReaderWriter();

    public void crypt(String inFileName, String outFileName, int messageSize, RsaKey rsaKey) throws IOException {
        BigInteger m = messageReaderWriter.readMessage(inFileName, messageSize);
        BigInteger c = m.modPow(rsaKey.getE(), rsaKey.getN());
        messageReaderWriter.writeMessage(outFileName, c);
    }
}
