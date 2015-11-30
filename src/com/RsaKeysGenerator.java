package com;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Agnieszka on 28.11.2015.
 */
public class RsaKeysGenerator {
    ParallelPrimeGenerator parallelPrimeGenerator = new ParallelPrimeGenerator();

    public RsaKeyPair generate(int numberOfPrimes, int length) throws ExecutionException, InterruptedException, FileNotFoundException, UnsupportedEncodingException {
        List<BigInteger> primes = parallelPrimeGenerator.getPrimes(numberOfPrimes, length);
        BigInteger n = BigInteger.ONE;
        BigInteger fi = BigInteger.ONE;
        for (BigInteger prime : primes) {
            System.out.println(prime);
            n = n.multiply(prime);
            fi = fi.multiply(prime.subtract(BigInteger.ONE));
        }
        BigInteger e = BigInteger.valueOf(65537);
        BigInteger d = e.modInverse(fi);
        return new RsaKeyPair(n, e, d, primes);
    }

    class RsaKeyPair {
        private final RsaKey publicKey;
        private final RsaKey privateKey;

        RsaKeyPair(BigInteger n, BigInteger e, BigInteger d, List<BigInteger> factors) {
            this.publicKey = new RsaKey(n, e, factors);
            this.privateKey = new RsaKey(n, d, factors);
        }

        public RsaKey getPublicKey() {
            return publicKey;
        }

        public RsaKey getPrivateKey() {
            return privateKey;
        }
    }
}
