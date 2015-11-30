package com;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.math.BigInteger;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Agnieszka on 29.11.2015.
 */
public class KeyReaderWriter {
    public void write(RsaKey rsaKey, String fileName) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter(fileName, "UTF-8");
        writer.print(rsaKey.getN() + ":" + rsaKey.getE());
        rsaKey.getFactors().forEach(factor -> {
            writer.print(factor + ":");
        });
        writer.close();
    }

    public RsaKey read(String fileName) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
        String line = bufferedReader.readLine();
        String[] splitted = line.split(":");
        List<String> factorsList = new ArrayList<>(Arrays.asList(Arrays.copyOfRange(splitted,2, splitted.length)));
        List<BigInteger> factors = new ArrayList(factorsList.size());
        factorsList.forEach(factor -> factors.add(new BigInteger(factor)));
        return new RsaKey(new BigInteger(splitted[0]), new BigInteger(splitted[1]), factors);
    }
}
