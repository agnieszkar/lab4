package com;

import java.math.BigInteger;
import java.util.concurrent.Callable;

/**
 * Created by Agnieszka on 30.11.2015.
 */
public class ExtendedEuclideanAlgorithmTask implements Callable<BigInteger> {
    private final BigInteger factor;
    private final BigInteger e;
    private final BigInteger message;
    private final BigInteger N;

    public ExtendedEuclideanAlgorithmTask(BigInteger message, BigInteger e, BigInteger factor, BigInteger N){
        this.factor = factor;
        this.e = e;
        this.message = message;
        this.N = N;
    }

    @Override
    public BigInteger call() throws Exception {
        BigInteger a = message.modPow(e, factor.mod(factor.subtract(BigInteger.ONE)));
        BigInteger n = N.divide(factor);
        BigInteger s = n.modInverse(factor);
        return a.multiply(n).multiply(s);
    }


}
