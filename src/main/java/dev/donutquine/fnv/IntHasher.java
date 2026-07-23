package dev.donutquine.fnv;

/**
 * @since 1.1.1
 */
public interface IntHasher extends Hasher<IntHasher> {
    public int digest();
}
