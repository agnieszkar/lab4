package com;

import java.io.IOException;
import java.math.BigInteger;

/**
 * Created by Agnieszka on 29.11.2015.
 */
public class RsaWithCrtEngine {
    MessageReaderWriter messageReaderWriter = new MessageReaderWriter();

    public void crypt(String inFileName, String outFileName, int messageSize, RsaKey rsaKey) throws IOException {
        BigInteger m = messageReaderWriter.readMessage(inFileName, messageSize);
        BigInteger c = m.modPow(rsaKey.getE(), rsaKey.getN());
        messageReaderWriter.writeMessage(outFileName, c);
    }
}
