package dev.donutquine.fnv;

/**
 * @since 1.1.1
 */
public interface GenericHasher<T> extends Hasher<GenericHasher<T>> {
    public T digest();
}
