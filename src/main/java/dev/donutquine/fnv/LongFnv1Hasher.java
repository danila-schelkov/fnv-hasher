package dev.donutquine.fnv;

/**
 * @since 1.1.0
 */
public class LongFnv1Hasher implements LongHasher {
    public long hash;
    public final long prime;

    public LongFnv1Hasher() {
        this(FnvConstants.INIT64, FnvConstants.PRIME64);
    }

	public LongFnv1Hasher(long init, long prime) {
		this.hash = init;
		this.prime = prime;
	}

	@Override
	public long digest() {
        return hash;
	}

	@Override
	public LongHasher update(byte value) {
        long hash = this.hash * this.prime;
        this.hash = hash ^ ((long) value & 0xff);
        return this;
	}
}
