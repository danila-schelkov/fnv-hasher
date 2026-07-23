package dev.donutquine.fnv;

/**
 * @since 1.1.0
 */
public class LongFnv1aHasher implements LongHasher {
    public long hash;
    public final long prime;

    public LongFnv1aHasher() {
        this(FnvConstants.INIT64, FnvConstants.PRIME64);
    }

	public LongFnv1aHasher(long init, long prime) {
		this.hash = init;
		this.prime = prime;
	}

	@Override
	public long digest() {
        return hash;
	}

	@Override
	public LongHasher update(byte value) {
        long hash = this.hash ^ ((long) value & 0xff);
        this.hash = hash * this.prime;
        return this;
	}
}
