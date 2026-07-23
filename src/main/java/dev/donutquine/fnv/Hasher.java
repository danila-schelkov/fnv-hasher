package dev.donutquine.fnv;

/**
 * @since 1.1.0
 */
public interface Hasher {
    public long digest();

    public Hasher update(byte value);

    public default Hasher update(short value) {
        return this.update((byte) (value >> 0))
            .update((byte) (value >> 8));
    }

    public default Hasher update(int value) {
        return this.update((byte) (value >> 0))
            .update((byte) (value >> 8))
            .update((byte) (value >> 16))
            .update((byte) (value >> 24));
    }

    public default Hasher update(long value) {
        return this.update((byte) (value >> 0))
            .update((byte) (value >> 8))
            .update((byte) (value >> 16))
            .update((byte) (value >> 24))
            .update((byte) (value >> 32))
            .update((byte) (value >> 40))
            .update((byte) (value >> 48))
            .update((byte) (value >> 56));
    }

    public default Hasher update(byte[] values) {
        Hasher hasher = this;
        for (byte b : values) {
            hasher = hasher.update(b);
        }
        return hasher;
    }

    public default Hasher update(short[] values) {
        Hasher hasher = this;
        for (short b : values) {
            hasher = hasher.update(b);
        }
        return hasher;
    }

    public default Hasher update(int[] values) {
        Hasher hasher = this;
        for (int b : values) {
            hasher = hasher.update(b);
        }
        return hasher;
    }

    public default Hasher update(long[] values) {
        Hasher hasher = this;
        for (long b : values) {
            hasher = hasher.update(b);
        }
        return hasher;
    }
}
