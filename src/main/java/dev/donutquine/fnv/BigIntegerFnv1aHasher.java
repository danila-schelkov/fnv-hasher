package dev.donutquine.fnv;

import java.math.BigInteger;

/**
 * @since 1.1.0
 */
public class BigIntegerFnv1aHasher implements Hasher {
    public static final BigInteger INIT32 = new BigInteger("811c9dc5", 16);
    public static final BigInteger INIT64 = new BigInteger("cbf29ce484222325", 16);
    public static final BigInteger PRIME32 = new BigInteger("01000193", 16);
    public static final BigInteger PRIME64 = new BigInteger("100000001b3", 16);
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
	public long digest() {
        return hash.longValue();
	}

	@Override
	public Hasher update(byte value) {
        BigInteger hash = this.hash.xor(BigInteger.valueOf((int) value & 0xff));
        this.hash = hash.multiply(prime).mod(mod);
        return this;
	}
}
