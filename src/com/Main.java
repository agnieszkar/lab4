package com;

import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ParallelPrimeGenerator parallelPrimeGenerator = new ParallelPrimeGenerator();
        for (BigInteger prime :  parallelPrimeGenerator.getPrimes(3, 256)){
            System.out.println("PrimeQ[" + prime.abs() + "]\n");
        }
    }
}
