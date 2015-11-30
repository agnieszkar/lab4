package com;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ExecutionException;

public class MainZad2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException, IOException {
        int d = 256;
        int k = 3;

        //generateRsaKeys(k, d);
        encryptWithCRT();
        decrypt();
    }

    private static void encrypt() throws IOException {
        RsaEngine rsaEngine = new RsaEngine();
        KeyReaderWriter keyReaderWriter = new KeyReaderWriter();
        RsaKey publicKey = keyReaderWriter.read("RSA_publicKey.txt");
        rsaEngine.crypt("plaintext.txt", "encrypted.txt", publicKey.getKeyLength()/8-11, publicKey);
    }

    private static void decrypt() throws IOException {
        RsaEngine rsaEngine = new RsaEngine();
        KeyReaderWriter keyReaderWriter = new KeyReaderWriter();
        RsaKey privateKey = keyReaderWriter.read("RSA_privateKey.txt");
        rsaEngine.crypt("encrypted.txt", "decrypted.txt", privateKey.getKeyLength()/8-11, privateKey);
    }

    private static void generateRsaKeys(int numberOfPrimes, int length) throws InterruptedException, ExecutionException, UnsupportedEncodingException, FileNotFoundException {
        RsaKeysGenerator rsaKeysGenerator = new RsaKeysGenerator();
        RsaKeysGenerator.RsaKeyPair keyPair = rsaKeysGenerator.generate(numberOfPrimes, length);
        KeyReaderWriter keyReaderWriter = new KeyReaderWriter();
        keyReaderWriter.write(keyPair.getPrivateKey(), "RSA_privateKey.txt");
        keyReaderWriter.write(keyPair.getPublicKey(), "RSA_publicKey.txt");
    }

    private static void encryptWithCRT() throws IOException, ExecutionException, InterruptedException {
        RsaWithCrtEngine rsaEngine = new RsaWithCrtEngine();
        KeyReaderWriter keyReaderWriter = new KeyReaderWriter();
        RsaKey publicKey = keyReaderWriter.read("RSA_publicKey.txt");
        rsaEngine.crypt("plaintext.txt", "encrypted.txt", publicKey.getKeyLength()/8-11, publicKey);
    }
}
