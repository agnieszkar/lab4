package com;

import java.util.concurrent.ExecutionException;

/**
 * Created by Agnieszka on 28.11.2015.
 */
public class MainZad1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ParallelPrimeGenerator parallelPrimeGenerator = new ParallelPrimeGenerator();
        parallelPrimeGenerator.getPrimes(3, 256).forEach(p -> {
            System.out.println("PrimeQ[" + p.abs() + "]\n");
        });
    }
}
