package dev.donutquine.fnv;

import java.math.BigInteger;

public class FnvHasher {
    private FnvHasher() {}

    /**
     * The FNV-0 hash differs from the FNV-1 hash only by the initialisation value of the hash variable. <br>
     * <br>
     * A consequence of initializing the hash to 0 is that empty messages and all messages consisting of only byte 0,
     * regardless of their length, are hashed to 0.
     */
    public static int fnv0_32(byte[] data) {
        return new IntFnv1Hasher(0, FnvConstants.PRIME32).update(data).digest();
    }

    /**
     * The FNV-0a (own) hash differs from the FNV-1a hash only by the initialisation value of the hash variable. <br>
     * <br>
     * A consequence of initializing the hash to 0 is that empty messages and all messages consisting of only byte 0,
     * regardless of their length, are hashed to 0.
     */
    public static int fnv0a_32(byte[] data) {
        return new IntFnv1aHasher(0, FnvConstants.PRIME32).update(data).digest();
    }

    /**
     * The FNV-0 hash differs from the FNV-1 hash only by the initialisation value of the hash variable. <br>
     * <br>
     * A consequence of initializing the hash to 0 is that empty messages and all messages consisting of only byte 0,
     * regardless of their length, are hashed to 0.
     */
    public static long fnv0_64(byte[] data) {
        return new LongFnv1Hasher(0, FnvConstants.PRIME64).update(data).digest();
    }

    /**
     * The FNV-0a (own) hash differs from the FNV-1a hash only by the initialisation value of the hash variable. <br>
     * <br>
     * A consequence of initializing the hash to 0 is that empty messages and all messages consisting of only byte 0,
     * regardless of their length, are hashed to 0.
     */
    public static long fnv0a_64(byte[] data) {
        return new LongFnv1aHasher(0, FnvConstants.PRIME64).update(data).digest();
    }

    public static int fnv1_32(byte[] data) {
        return new IntFnv1Hasher().update(data).digest();
    }

    /**
     * The FNV-1a hash differs from the FNV-1 hash only by the order in which the multiply and XOR is performed,
     * which leads to slightly better avalanche characteristics.
     */
    public static int fnv1a_32(byte[] data) {
        return new IntFnv1aHasher().update(data).digest();
    }

    public static long fnv1_64(byte[] data) {
        return new LongFnv1Hasher().update(data).digest();
    }

    /**
     * The FNV-1a hash differs from the FNV-1 hash only by the order in which the multiply and XOR is performed,
     * which leads to slightly better avalanche characteristics.
     */
    public static long fnv1a_64(byte[] data) {
        return new LongFnv1aHasher().update(data).digest();
    }

    /**
     * The FNV-0 hash differs from the FNV-1 hash only by the initialisation value of the hash variable. <br>
     * <br>
     * A consequence of initializing the hash to 0 is that empty messages and all messages consisting of only byte 0,
     * regardless of their length, are hashed to 0.
     */
    @Deprecated
    public static BigInteger fnv0(byte[] data, BigInteger prime, BigInteger mod) {
        return fnv1(data, BigInteger.ZERO, prime, mod);
    }

    /**
     * The FNV-0a (own) hash differs from the FNV-1a hash only by the initialisation value of the hash variable. <br>
     * <br>
     * A consequence of initializing the hash to 0 is that empty messages and all messages consisting of only byte 0,
     * regardless of their length, are hashed to 0.
     */
    @Deprecated
    public static BigInteger fnv0a(byte[] data, BigInteger prime, BigInteger mod) {
        return fnv1a(data, BigInteger.ZERO, prime, mod);
    }

    public static BigInteger fnv1(byte[] data, BigInteger init, BigInteger prime, BigInteger mod) {
        return new BigIntegerFnv1Hasher(init, prime, mod).update(data).digest();
    }

    /**
     * The FNV-1a hash differs from the FNV-1 hash only by the order in which the multiply and XOR is performed,
     * which leads to slightly better avalanche characteristics.
     */
    public static BigInteger fnv1a(byte[] data, BigInteger init, BigInteger prime, BigInteger mod) {
        return new BigIntegerFnv1aHasher(init, prime, mod).update(data).digest();
    }
}
