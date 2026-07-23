package dev.donutquine.fnv;

import java.math.BigInteger;

/**
 * @since 1.1.0
 */
public class BigIntegerFnv1aHasher implements GenericHasher<BigInteger> {
    public static final BigInteger INIT32 = BigInteger.valueOf(FnvConstants.INIT32);
    public static final BigInteger INIT64 = BigInteger.valueOf(FnvConstants.INIT64);
    public static final BigInteger PRIME32 = BigInteger.valueOf(FnvConstants.PRIME32);
    public static final BigInteger PRIME64 = BigInteger.valueOf(FnvConstants.PRIME64);
    public static final BigInteger MOD32 = new BigInteger("2").pow(32);
    public static final BigInteger MOD64 = new BigInteger("2").pow(64);

    private BigInteger hash;
	private final BigInteger prime, mod;

    public BigIntegerFnv1aHasher(BigInteger init, BigInteger prime, BigInteger mod) {
        this.hash = init;
        this.prime = prime;
        this.mod = mod;
    }

	@Override
	public BigInteger digest() {
        return hash;
	}

	@Override
	public GenericHasher<BigInteger> update(byte value) {
        BigInteger hash = this.hash.xor(BigInteger.valueOf((int) value & 0xff));
        this.hash = hash.multiply(prime).mod(mod);
        return this;
	}
}
