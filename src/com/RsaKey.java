package com;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by Agnieszka on 29.11.2015.
 */
public class RsaKey {
    private final BigInteger n;
    private final BigInteger e;
    private final List<BigInteger> factors;

    public RsaKey(BigInteger n, BigInteger e, List<BigInteger> factors) {
        this.n = n;
        this.e = e;
        this.factors = factors;
    }

    public BigInteger getE() {
        return e;
    }

    public BigInteger getN() {
        return n;
    }

    public int getKeyLength(){
        return n.bitLength();
    }

    public List<BigInteger> getFactors() { return factors;}
}
