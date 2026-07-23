package dev.donutquine.fnv;

/**
 * @since 1.1.1
 */
public interface LongHasher extends Hasher<LongHasher> {
    public long digest();
}
