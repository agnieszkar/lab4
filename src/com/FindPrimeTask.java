package com;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.concurrent.Callable;

/**
 * Created by Agnieszka on 28.11.2015.
 */
public class FindPrimeTask implements Callable<BigInteger>{

    private static final BigInteger THREE = new BigInteger("3");
    private static final BigInteger ONE = new BigInteger("1");
    private static final BigInteger TWO = new BigInteger("2");
    private static final BigInteger ZERO = new BigInteger("0");

    private final SecureRandom secureRandom;
    private final int length;

    public FindPrimeTask(int length, SecureRandom secureRandom){
        this.length = length;
        this.secureRandom = secureRandom;
    }

    @Override
    public BigInteger call() throws Exception {
        BigInteger numberToTest = new BigInteger(length, secureRandom);
        while (!passPrimalityTest(numberToTest, length/2 + 1)) {
            numberToTest = new BigInteger(length, secureRandom);
        }
        return numberToTest;
    }

    private boolean passPrimalityTest(BigInteger number, int testPrecision) {
        if (number.testBit(0) && !number.testBit(number.bitLength())) {
            BigInteger d = number.subtract(ONE);
            int s;
            for (s = 0; d.mod(TWO).equals(ZERO); s++) {
                d = d.divide(TWO);
            }
            for (int i = 0; i < testPrecision; i++) {
                BigInteger a = randomFromInterval(TWO, number.subtract(ONE));
                BigInteger x = a.modPow(d, number);
                if (!(x.equals(ONE) || x.equals(number.subtract(ONE)))) {
                    int r;
                    for (r = 1; r < s; r++) {
                        x = x.modPow(TWO, number);
                        if (x.equals(ONE)) {
                            return false;
                        }
                        if (x.equals(number.subtract(ONE))) {
                            break;
                        }
                    }
                    if (r == s) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    private BigInteger randomFromInterval(BigInteger min, BigInteger max) {
        BigInteger random = new BigInteger(max.bitLength(), secureRandom);
        while (random.compareTo(min) < 0 || random.compareTo(max) > 0) {
            random = new BigInteger(max.bitLength(), secureRandom);
        }
        return random;
    }
}
