package com;

import java.util.concurrent.ExecutionException;

public class MainZad2 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ParallelPrimeGenerator parallelPrimeGenerator = new ParallelPrimeGenerator();
        parallelPrimeGenerator.getPrimes(3, 256).forEach(p -> {
            System.out.println("PrimeQ[" + p.abs() + "]\n");
        });
    }
}
