package dev.donutquine.fnv;

/**
 * @since 1.1.0
 */
public class IntFnv1aHasher implements Hasher {
    public int hash;
    public final int prime;

    public IntFnv1aHasher() {
        this(FnvConstants.INIT32, FnvConstants.PRIME32);
    }

	public IntFnv1aHasher(int init, int prime) {
		this.hash = init;
		this.prime = prime;
	}

	@Override
	public long digest() {
        return hash;
	}

	@Override
	public Hasher update(byte value) {
        int hash = this.hash ^ ((int) value & 0xff);
        this.hash = hash * this.prime;
        return this;
	}
}
