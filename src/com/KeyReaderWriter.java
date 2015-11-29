package com;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.math.BigInteger;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Agnieszka on 29.11.2015.
 */
public class KeyReaderWriter {
    public void write(RsaKey rsaKey, String fileName) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter(fileName, "UTF-8");
        rsaKey.getFactors().forEach(factor -> {
            writer.print(factor + ":");
        });
        writer.println(rsaKey.getN() + ":" + rsaKey.getE());
        writer.close();
    }

    public RsaKey read(String fileName) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
        String line = bufferedReader.readLine();
        String[] splitted = line.split(":");

        return new RsaKey(new BigInteger(splitted[0]), new BigInteger(splitted[1]), new ArrayList<>());
    }
}
