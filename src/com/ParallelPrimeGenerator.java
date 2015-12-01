package com;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Agnieszka on 28.11.2015.
 */
public class ParallelPrimeGenerator {
    private final SecureRandom secureRandom = new SecureRandom();

    public List<BigInteger> getPrimes(int numberOfPrimes, final int length) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfPrimes);
        List<BigInteger> primes = new ArrayList<>(numberOfPrimes);
        List<FindPrimeTask> tasks = new ArrayList<>(numberOfPrimes);
        for (int i = 0; i < numberOfPrimes; i++) {
            tasks.add(new FindPrimeTask(length, secureRandom));
        }
        for (Future<BigInteger> future : executorService.invokeAll(tasks)) {
            primes.add(future.get());
        }
        System.out.println("Primes : " + primes.size());
        executorService.shutdown();
        return primes;
    }
}
