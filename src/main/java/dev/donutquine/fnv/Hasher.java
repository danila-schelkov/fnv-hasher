package dev.donutquine.fnv;

/**
 * @since 1.1.0
 */
public interface Hasher<T extends Hasher<T>> {
    public T update(byte value);

    public default T update(short value) {
        return this.update((byte) (value >> 0))
            .update((byte) (value >> 8));
    }

    public default T update(int value) {
        return this.update((byte) (value >> 0))
            .update((byte) (value >> 8))
            .update((byte) (value >> 16))
            .update((byte) (value >> 24));
    }

    public default T update(long value) {
        return this.update((byte) (value >> 0))
            .update((byte) (value >> 8))
            .update((byte) (value >> 16))
            .update((byte) (value >> 24))
            .update((byte) (value >> 32))
            .update((byte) (value >> 40))
            .update((byte) (value >> 48))
            .update((byte) (value >> 56));
    }

    public default T update(byte[] values) {
        Hasher<T> hasher = this;
        for (byte b : values) {
            hasher = hasher.update(b);
        }
        return (T) hasher;
    }

    public default T update(short[] values) {
        Hasher<T> hasher = this;
        for (short b : values) {
            hasher = hasher.update(b);
        }
        return (T) hasher;
    }

    public default T update(int[] values) {
        Hasher<T> hasher = this;
        for (int b : values) {
            hasher = hasher.update(b);
        }
        return (T) hasher;
    }

    public default T update(long[] values) {
        Hasher<T> hasher = this;
        for (long b : values) {
            hasher = hasher.update(b);
        }
        return (T) hasher;
    }
}
