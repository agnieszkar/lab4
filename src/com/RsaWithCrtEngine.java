package com;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Agnieszka on 29.11.2015.
 */
public class RsaWithCrtEngine {
    MessageReaderWriter messageReaderWriter = new MessageReaderWriter();

    public void crypt(String inFileName, String outFileName, int messageSize, RsaKey rsaKey) throws IOException, InterruptedException, ExecutionException {
        BigInteger m = messageReaderWriter.readMessage(inFileName, messageSize);
        ExecutorService executorService = Executors.newFixedThreadPool(rsaKey.getFactors().size());
        List<ExtendedEuclideanAlgorithmTask> tasks = new ArrayList<>(rsaKey.getFactors().size());
        rsaKey.getFactors().forEach(factor -> {tasks.add(new ExtendedEuclideanAlgorithmTask(m, rsaKey.getE(),factor, rsaKey.getN()));});
        BigInteger sum = BigInteger.ZERO;
        List<Future<BigInteger>> f = executorService.invokeAll(tasks);
        for (Future<BigInteger> future : f) {
            sum = sum.add(future.get());
        }
        sum = sum.mod(rsaKey.getN());
        executorService.shutdown();
        messageReaderWriter.writeMessage(outFileName, sum);
    }
}
