package dev.donutquine.fnv;

/**
 * @since 1.1.0
 */
public class IntFnv1Hasher implements IntHasher {
    public int hash;
    public final int prime;

    public IntFnv1Hasher() {
        this(FnvConstants.INIT32, FnvConstants.PRIME32);
    }

	public IntFnv1Hasher(int init, int prime) {
		this.hash = init;
		this.prime = prime;
	}

	@Override
	public int digest() {
        return hash;
	}

	@Override
	public IntHasher update(byte value) {
        int hash = this.hash * this.prime;
        this.hash = hash ^ ((int) value & 0xff);
        return this;
	}
}
