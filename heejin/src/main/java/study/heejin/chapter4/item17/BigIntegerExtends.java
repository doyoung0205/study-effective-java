package study.heejin.chapter4.item17;

import java.math.BigInteger;
import java.util.Random;

public class BigIntegerExtends extends BigInteger {

    public BigIntegerExtends(byte[] val, int off, int len) {
        super(val, off, len);
    }

    public BigIntegerExtends(byte[] val) {
        super(val);
    }

    public BigIntegerExtends(int signum, byte[] magnitude, int off, int len) {
        super(signum, magnitude, off, len);
    }

    public BigIntegerExtends(int signum, byte[] magnitude) {
        super(signum, magnitude);
    }

    public BigIntegerExtends(String val, int radix) {
        super(val, radix);
    }

    public BigIntegerExtends(String val) {
        super(val);
    }

    public BigIntegerExtends(int numBits, Random rnd) {
        super(numBits, rnd);
    }

    public BigIntegerExtends(int bitLength, int certainty, Random rnd) {
        super(bitLength, certainty, rnd);
    }
}
