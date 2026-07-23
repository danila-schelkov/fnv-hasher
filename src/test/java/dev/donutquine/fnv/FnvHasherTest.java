package dev.donutquine.fnv;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import java.math.BigInteger;
import java.util.concurrent.TimeUnit;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Regression tests for all FNV hasher implementations.
 * Verifies that each hasher's digest() matches the canonical FnvHasher output
 * for all update methods (byte, short, int, long, byte[], short[], int[], long[]).
 */
@Timeout(value = 1, unit = TimeUnit.SECONDS)
class FnvHasherTest {
    @Test
    void intFnv1_singleByte_values() {
        for (byte b = -128; b < 127; b++) {
            long expected = FnvHasher.fnv1_32(new byte[]{b});
            long actual = new IntFnv1Hasher().update(b).digest();
            assertEquals(expected, actual, "Failed for byte value " + (b & 0xFF));
        }
    }

    @Test
    void intFnv1_short() {
        short value = 0x1234;
        byte[] asBytes = new byte[]{(byte) (value >> 0), (byte) (value >> 8)};
        long expected = FnvHasher.fnv1_32(asBytes);
        long actual = new IntFnv1Hasher().update(value).digest();
        assertEquals(expected, actual);
    }

    @Test
    void intFnv1_short_values() {
        for (short s = -32768; s < 32767; s++) {
            byte[] asBytes = new byte[]{(byte) (s >> 0), (byte) (s >> 8)};
            long expected = FnvHasher.fnv1_32(asBytes);
            long actual = new IntFnv1Hasher().update(s).digest();
            assertEquals(expected, actual, "Failed for short value " + s);
        }
    }

    @Test
    void intFnv1_int() {
        int value = 0xDEADBEEF;
        byte[] asBytes = new byte[]{
            (byte) (value >> 0), (byte) (value >> 8),
            (byte) (value >> 16), (byte) (value >> 24)
        };
        long expected = FnvHasher.fnv1_32(asBytes);
        long actual = new IntFnv1Hasher().update(value).digest();
        assertEquals(expected, actual);
    }

    @Test
    void intFnv1_int_values() {
        for (int i = 0; i < 1000; i++) {
            byte[] asBytes = new byte[]{
                (byte) (i >> 0), (byte) (i >> 8),
                (byte) (i >> 16), (byte) (i >> 24)
            };
            long expected = FnvHasher.fnv1_32(asBytes);
            long actual = new IntFnv1Hasher().update(i).digest();
            assertEquals(expected, actual, "Failed for int value " + i);
        }
    }

    @Test
    void intFnv1_long() {
        long value = 0x0123456789ABCDEFL;
        byte[] asBytes = new byte[]{
            (byte) (value >> 0), (byte) (value >> 8),
            (byte) (value >> 16), (byte) (value >> 24),
            (byte) (value >> 32), (byte) (value >> 40),
            (byte) (value >> 48), (byte) (value >> 56)
        };
        long expected = FnvHasher.fnv1_32(asBytes);
        long actual = new IntFnv1Hasher().update(value).digest();
        assertEquals(expected, actual);
    }

    @Test
    void intFnv1_long_values() {
        for (long l = 0; l < 1000; l++) {
            byte[] asBytes = new byte[]{
                (byte) (l >> 0), (byte) (l >> 8),
                (byte) (l >> 16), (byte) (l >> 24),
                (byte) (l >> 32), (byte) (l >> 40),
                (byte) (l >> 48), (byte) (l >> 56)
            };
            long expected = FnvHasher.fnv1_32(asBytes);
            long actual = new IntFnv1Hasher().update(l).digest();
            assertEquals(expected, actual, "Failed for long value " + l);
        }
    }

    @Test
    void intFnv1_byteArray() {
        byte[] data = "hello world".getBytes();
        long expected = FnvHasher.fnv1_32(data);
        long actual = new IntFnv1Hasher().update(data).digest();
        assertEquals(expected, actual);
    }

    @Test
    void intFnv1_byteArray_empty() {
        byte[] data = new byte[0];
        long expected = FnvHasher.fnv1_32(data);
        long actual = new IntFnv1Hasher().update(data).digest();
        assertEquals(expected, actual);
    }

    @Test
    void intFnv1_shortArray() {
        short[] values = {0x1234, 0x5678, (short) 0x9ABC};
        byte[] asBytes = new byte[values.length * 2];
        for (int i = 0; i < values.length; i++) {
            asBytes[i * 2] = (byte) (values[i] >> 0);
            asBytes[i * 2 + 1] = (byte) (values[i] >> 8);
        }
        long expected = FnvHasher.fnv1_32(asBytes);
        long actual = new IntFnv1Hasher().update(values).digest();
        assertEquals(expected, actual);
    }

    @Test
    void intFnv1_intArray() {
        int[] values = {0xDEADBEEF, 0xCAFE0123};
        byte[] asBytes = new byte[values.length * 4];
        for (int i = 0; i < values.length; i++) {
            asBytes[i * 4] = (byte) (values[i] >> 0);
            asBytes[i * 4 + 1] = (byte) (values[i] >> 8);
            asBytes[i * 4 + 2] = (byte) (values[i] >> 16);
            asBytes[i * 4 + 3] = (byte) (values[i] >> 24);
        }
        long expected = FnvHasher.fnv1_32(asBytes);
        long actual = new IntFnv1Hasher().update(values).digest();
        assertEquals(expected, actual);
    }

    @Test
    void intFnv1_longArray() {
        long[] values = {0x0123456789ABCDEFL, 0xFEDCBA9876543210L};
        byte[] asBytes = new byte[values.length * 8];
        for (int i = 0; i < values.length; i++) {
            asBytes[i * 8] = (byte) (values[i] >> 0);
            asBytes[i * 8 + 1] = (byte) (values[i] >> 8);
            asBytes[i * 8 + 2] = (byte) (values[i] >> 16);
            asBytes[i * 8 + 3] = (byte) (values[i] >> 24);
            asBytes[i * 8 + 4] = (byte) (values[i] >> 32);
            asBytes[i * 8 + 5] = (byte) (values[i] >> 40);
            asBytes[i * 8 + 6] = (byte) (values[i] >> 48);
            asBytes[i * 8 + 7] = (byte) (values[i] >> 56);
        }
        long expected = FnvHasher.fnv1_32(asBytes);
        long actual = new IntFnv1Hasher().update(values).digest();
        assertEquals(expected, actual);
    }

    @Test
    void intFnv1a_singleByte() {
        for (byte b = -128; b < 127; b++) {
            long expected = FnvHasher.fnv1a_32(new byte[]{b});
            long actual = new IntFnv1aHasher().update(b).digest();
            assertEquals(expected, actual, "Failed for byte value " + (b & 0xFF));
        }
    }

    @Test
    void intFnv1a_short() {
        short value = 0x1234;
        byte[] asBytes = new byte[]{(byte) (value >> 0), (byte) (value >> 8)};
        long expected = FnvHasher.fnv1a_32(asBytes);
        long actual = new IntFnv1aHasher().update(value).digest();
        assertEquals(expected, actual);
    }

    @Test
    void intFnv1a_short_values() {
        for (short s = -32768; s < 32767; s++) {
            byte[] asBytes = new byte[]{(byte) (s >> 0), (byte) (s >> 8)};
            long expected = FnvHasher.fnv1a_32(asBytes);
            long actual = new IntFnv1aHasher().update(s).digest();
            assertEquals(expected, actual, "Failed for short value " + s);
        }
    }

    @Test
    void intFnv1a_int() {
        int value = 0xDEADBEEF;
        byte[] asBytes = new byte[]{
            (byte) (value >> 0), (byte) (value >> 8),
            (byte) (value >> 16), (byte) (value >> 24)
        };
        long expected = FnvHasher.fnv1a_32(asBytes);
        long actual = new IntFnv1aHasher().update(value).digest();
        assertEquals(expected, actual);
    }

    @Test
    void intFnv1a_int_values() {
        for (int i = 0; i < 1000; i++) {
            byte[] asBytes = new byte[]{
                (byte) (i >> 0), (byte) (i >> 8),
                (byte) (i >> 16), (byte) (i >> 24)
            };
            long expected = FnvHasher.fnv1a_32(asBytes);
            long actual = new IntFnv1aHasher().update(i).digest();
            assertEquals(expected, actual, "Failed for int value " + i);
        }
    }

    @Test
    void intFnv1a_long() {
        long value = 0x0123456789ABCDEFL;
        byte[] asBytes = new byte[]{
            (byte) (value >> 0), (byte) (value >> 8),
            (byte) (value >> 16), (byte) (value >> 24),
            (byte) (value >> 32), (byte) (value >> 40),
            (byte) (value >> 48), (byte) (value >> 56)
        };
        long expected = FnvHasher.fnv1a_32(asBytes);
        long actual = new IntFnv1aHasher().update(value).digest();
        assertEquals(expected, actual);
    }

    @Test
    void intFnv1a_long_values() {
        for (long l = 0; l < 1000; l++) {
            byte[] asBytes = new byte[]{
                (byte) (l >> 0), (byte) (l >> 8),
                (byte) (l >> 16), (byte) (l >> 24),
                (byte) (l >> 32), (byte) (l >> 40),
                (byte) (l >> 48), (byte) (l >> 56)
            };
            long expected = FnvHasher.fnv1a_32(asBytes);
            long actual = new IntFnv1aHasher().update(l).digest();
            assertEquals(expected, actual, "Failed for long value " + l);
        }
    }

    @Test
    void intFnv1a_byteArray() {
        byte[] data = "hello world".getBytes();
        long expected = FnvHasher.fnv1a_32(data);
        long actual = new IntFnv1aHasher().update(data).digest();
        assertEquals(expected, actual);
    }

    @Test
    void intFnv1a_byteArray_empty() {
        byte[] data = new byte[0];
        long expected = FnvHasher.fnv1a_32(data);
        long actual = new IntFnv1aHasher().update(data).digest();
        assertEquals(expected, actual);
    }

    @Test
    void intFnv1a_shortArray() {
        short[] values = {0x1234, 0x5678, (short) 0x9ABC};
        byte[] asBytes = new byte[values.length * 2];
        for (int i = 0; i < values.length; i++) {
            asBytes[i * 2] = (byte) (values[i] >> 0);
            asBytes[i * 2 + 1] = (byte) (values[i] >> 8);
        }
        long expected = FnvHasher.fnv1a_32(asBytes);
        long actual = new IntFnv1aHasher().update(values).digest();
        assertEquals(expected, actual);
    }

    @Test
    void intFnv1a_intArray() {
        int[] values = {0xDEADBEEF, 0xCAFE0123};
        byte[] asBytes = new byte[values.length * 4];
        for (int i = 0; i < values.length; i++) {
            asBytes[i * 4] = (byte) (values[i] >> 0);
            asBytes[i * 4 + 1] = (byte) (values[i] >> 8);
            asBytes[i * 4 + 2] = (byte) (values[i] >> 16);
            asBytes[i * 4 + 3] = (byte) (values[i] >> 24);
        }
        long expected = FnvHasher.fnv1a_32(asBytes);
        long actual = new IntFnv1aHasher().update(values).digest();
        assertEquals(expected, actual);
    }

    @Test
    void intFnv1a_longArray() {
        long[] values = {0x0123456789ABCDEFL, 0xFEDCBA9876543210L};
        byte[] asBytes = new byte[values.length * 8];
        for (int i = 0; i < values.length; i++) {
            asBytes[i * 8] = (byte) (values[i] >> 0);
            asBytes[i * 8 + 1] = (byte) (values[i] >> 8);
            asBytes[i * 8 + 2] = (byte) (values[i] >> 16);
            asBytes[i * 8 + 3] = (byte) (values[i] >> 24);
            asBytes[i * 8 + 4] = (byte) (values[i] >> 32);
            asBytes[i * 8 + 5] = (byte) (values[i] >> 40);
            asBytes[i * 8 + 6] = (byte) (values[i] >> 48);
            asBytes[i * 8 + 7] = (byte) (values[i] >> 56);
        }
        long expected = FnvHasher.fnv1a_32(asBytes);
        long actual = new IntFnv1aHasher().update(values).digest();
        assertEquals(expected, actual);
    }

    @Test
    void longFnv1_singleByte() {
        for (byte b = -128; b < 127; b++) {
            long expected = FnvHasher.fnv1_64(new byte[]{b});
            long actual = new LongFnv1Hasher().update(b).digest();
            assertEquals(expected, actual, "Failed for byte value " + (b & 0xFF));
        }
    }

    @Test
    void longFnv1_short() {
        short value = 0x1234;
        byte[] asBytes = new byte[]{(byte) (value >> 0), (byte) (value >> 8)};
        long expected = FnvHasher.fnv1_64(asBytes);
        long actual = new LongFnv1Hasher().update(value).digest();
        assertEquals(expected, actual);
    }

    @Test
    void longFnv1_short_values() {
        for (short s = -32768; s < 32767; s++) {
            byte[] asBytes = new byte[]{(byte) (s >> 0), (byte) (s >> 8)};
            long expected = FnvHasher.fnv1_64(asBytes);
            long actual = new LongFnv1Hasher().update(s).digest();
            assertEquals(expected, actual, "Failed for short value " + s);
        }
    }

    @Test
    void longFnv1_int() {
        int value = 0xDEADBEEF;
        byte[] asBytes = new byte[]{
            (byte) (value >> 0), (byte) (value >> 8),
            (byte) (value >> 16), (byte) (value >> 24)
        };
        long expected = FnvHasher.fnv1_64(asBytes);
        long actual = new LongFnv1Hasher().update(value).digest();
        assertEquals(expected, actual);
    }

    @Test
    void longFnv1_int_values() {
        for (int i = 0; i < 1000; i++) {
            byte[] asBytes = new byte[]{
                (byte) (i >> 0), (byte) (i >> 8),
                (byte) (i >> 16), (byte) (i >> 24)
            };
            long expected = FnvHasher.fnv1_64(asBytes);
            long actual = new LongFnv1Hasher().update(i).digest();
            assertEquals(expected, actual, "Failed for int value " + i);
        }
    }

    @Test
    void longFnv1_long() {
        long value = 0x0123456789ABCDEFL;
        byte[] asBytes = new byte[]{
            (byte) (value >> 0), (byte) (value >> 8),
            (byte) (value >> 16), (byte) (value >> 24),
            (byte) (value >> 32), (byte) (value >> 40),
            (byte) (value >> 48), (byte) (value >> 56)
        };
        long expected = FnvHasher.fnv1_64(asBytes);
        long actual = new LongFnv1Hasher().update(value).digest();
        assertEquals(expected, actual);
    }

    @Test
    void longFnv1_long_values() {
        for (long l = 0; l < 1000; l++) {
            byte[] asBytes = new byte[]{
                (byte) (l >> 0), (byte) (l >> 8),
                (byte) (l >> 16), (byte) (l >> 24),
                (byte) (l >> 32), (byte) (l >> 40),
                (byte) (l >> 48), (byte) (l >> 56)
            };
            long expected = FnvHasher.fnv1_64(asBytes);
            long actual = new LongFnv1Hasher().update(l).digest();
            assertEquals(expected, actual, "Failed for long value " + l);
        }
    }

    @Test
    void longFnv1_byteArray() {
        byte[] data = "hello world".getBytes();
        long expected = FnvHasher.fnv1_64(data);
        long actual = new LongFnv1Hasher().update(data).digest();
        assertEquals(expected, actual);
    }

    @Test
    void longFnv1_byteArray_empty() {
        byte[] data = new byte[0];
        long expected = FnvHasher.fnv1_64(data);
        long actual = new LongFnv1Hasher().update(data).digest();
        assertEquals(expected, actual);
    }

    @Test
    void longFnv1_shortArray() {
        short[] values = {0x1234, 0x5678, (short) 0x9ABC};
        byte[] asBytes = new byte[values.length * 2];
        for (int i = 0; i < values.length; i++) {
            asBytes[i * 2] = (byte) (values[i] >> 0);
            asBytes[i * 2 + 1] = (byte) (values[i] >> 8);
        }
        long expected = FnvHasher.fnv1_64(asBytes);
        long actual = new LongFnv1Hasher().update(values).digest();
        assertEquals(expected, actual);
    }

    @Test
    void longFnv1_intArray() {
        int[] values = {0xDEADBEEF, 0xCAFE0123};
        byte[] asBytes = new byte[values.length * 4];
        for (int i = 0; i < values.length; i++) {
            asBytes[i * 4] = (byte) (values[i] >> 0);
            asBytes[i * 4 + 1] = (byte) (values[i] >> 8);
            asBytes[i * 4 + 2] = (byte) (values[i] >> 16);
            asBytes[i * 4 + 3] = (byte) (values[i] >> 24);
        }
        long expected = FnvHasher.fnv1_64(asBytes);
        long actual = new LongFnv1Hasher().update(values).digest();
        assertEquals(expected, actual);
    }

    @Test
    void longFnv1_longArray() {
        long[] values = {0x0123456789ABCDEFL, 0xFEDCBA9876543210L};
        byte[] asBytes = new byte[values.length * 8];
        for (int i = 0; i < values.length; i++) {
            asBytes[i * 8] = (byte) (values[i] >> 0);
            asBytes[i * 8 + 1] = (byte) (values[i] >> 8);
            asBytes[i * 8 + 2] = (byte) (values[i] >> 16);
            asBytes[i * 8 + 3] = (byte) (values[i] >> 24);
            asBytes[i * 8 + 4] = (byte) (values[i] >> 32);
            asBytes[i * 8 + 5] = (byte) (values[i] >> 40);
            asBytes[i * 8 + 6] = (byte) (values[i] >> 48);
            asBytes[i * 8 + 7] = (byte) (values[i] >> 56);
        }
        long expected = FnvHasher.fnv1_64(asBytes);
        long actual = new LongFnv1Hasher().update(values).digest();
        assertEquals(expected, actual);
    }

    @Test
    void longFnv1a_singleByte() {
        for (byte b = -128; b < 127; b++) {
            long expected = FnvHasher.fnv1a_64(new byte[]{b});
            long actual = new LongFnv1aHasher().update(b).digest();
            assertEquals(expected, actual, "Failed for byte value " + (b & 0xFF));
        }
    }

    @Test
    void longFnv1a_short() {
        short value = 0x1234;
        byte[] asBytes = new byte[]{(byte) (value >> 0), (byte) (value >> 8)};
        long expected = FnvHasher.fnv1a_64(asBytes);
        long actual = new LongFnv1aHasher().update(value).digest();
        assertEquals(expected, actual);
    }

    @Test
    void longFnv1a_short_values() {
        for (short s = -32768; s < 32767; s++) {
            byte[] asBytes = new byte[]{(byte) (s >> 0), (byte) (s >> 8)};
            long expected = FnvHasher.fnv1a_64(asBytes);
            long actual = new LongFnv1aHasher().update(s).digest();
            assertEquals(expected, actual, "Failed for short value " + s);
        }
    }

    @Test
    void longFnv1a_int() {
        int value = 0xDEADBEEF;
        byte[] asBytes = new byte[]{
            (byte) (value >> 0), (byte) (value >> 8),
            (byte) (value >> 16), (byte) (value >> 24)
        };
        long expected = FnvHasher.fnv1a_64(asBytes);
        long actual = new LongFnv1aHasher().update(value).digest();
        assertEquals(expected, actual);
    }

    @Test
    void longFnv1a_int_values() {
        for (int i = 0; i < 1000; i++) {
            byte[] asBytes = new byte[]{
                (byte) (i >> 0), (byte) (i >> 8),
                (byte) (i >> 16), (byte) (i >> 24)
            };
            long expected = FnvHasher.fnv1a_64(asBytes);
            long actual = new LongFnv1aHasher().update(i).digest();
            assertEquals(expected, actual, "Failed for int value " + i);
        }
    }

    @Test
    void longFnv1a_long() {
        long value = 0x0123456789ABCDEFL;
        byte[] asBytes = new byte[]{
            (byte) (value >> 0), (byte) (value >> 8),
            (byte) (value >> 16), (byte) (value >> 24),
            (byte) (value >> 32), (byte) (value >> 40),
            (byte) (value >> 48), (byte) (value >> 56)
        };
        long expected = FnvHasher.fnv1a_64(asBytes);
        long actual = new LongFnv1aHasher().update(value).digest();
        assertEquals(expected, actual);
    }

    @Test
    void longFnv1a_long_values() {
        for (long l = 0; l < 1000; l++) {
            byte[] asBytes = new byte[]{
                (byte) (l >> 0), (byte) (l >> 8),
                (byte) (l >> 16), (byte) (l >> 24),
                (byte) (l >> 32), (byte) (l >> 40),
                (byte) (l >> 48), (byte) (l >> 56)
            };
            long expected = FnvHasher.fnv1a_64(asBytes);
            long actual = new LongFnv1aHasher().update(l).digest();
            assertEquals(expected, actual, "Failed for long value " + l);
        }
    }

    @Test
    void longFnv1a_byteArray() {
        byte[] data = "hello world".getBytes();
        long expected = FnvHasher.fnv1a_64(data);
        long actual = new LongFnv1aHasher().update(data).digest();
        assertEquals(expected, actual);
    }

    @Test
    void longFnv1a_byteArray_empty() {
        byte[] data = new byte[0];
        long expected = FnvHasher.fnv1a_64(data);
        long actual = new LongFnv1aHasher().update(data).digest();
        assertEquals(expected, actual);
    }

    @Test
    void longFnv1a_shortArray() {
        short[] values = {0x1234, 0x5678, (short) 0x9ABC};
        byte[] asBytes = new byte[values.length * 2];
        for (int i = 0; i < values.length; i++) {
            asBytes[i * 2] = (byte) (values[i] >> 0);
            asBytes[i * 2 + 1] = (byte) (values[i] >> 8);
        }
        long expected = FnvHasher.fnv1a_64(asBytes);
        long actual = new LongFnv1aHasher().update(values).digest();
        assertEquals(expected, actual);
    }

    @Test
    void longFnv1a_intArray() {
        int[] values = {0xDEADBEEF, 0xCAFE0123};
        byte[] asBytes = new byte[values.length * 4];
        for (int i = 0; i < values.length; i++) {
            asBytes[i * 4] = (byte) (values[i] >> 0);
            asBytes[i * 4 + 1] = (byte) (values[i] >> 8);
            asBytes[i * 4 + 2] = (byte) (values[i] >> 16);
            asBytes[i * 4 + 3] = (byte) (values[i] >> 24);
        }
        long expected = FnvHasher.fnv1a_64(asBytes);
        long actual = new LongFnv1aHasher().update(values).digest();
        assertEquals(expected, actual);
    }

    @Test
    void longFnv1a_longArray() {
        long[] values = {0x0123456789ABCDEFL, 0xFEDCBA9876543210L};
        byte[] asBytes = new byte[values.length * 8];
        for (int i = 0; i < values.length; i++) {
            asBytes[i * 8] = (byte) (values[i] >> 0);
            asBytes[i * 8 + 1] = (byte) (values[i] >> 8);
            asBytes[i * 8 + 2] = (byte) (values[i] >> 16);
            asBytes[i * 8 + 3] = (byte) (values[i] >> 24);
            asBytes[i * 8 + 4] = (byte) (values[i] >> 32);
            asBytes[i * 8 + 5] = (byte) (values[i] >> 40);
            asBytes[i * 8 + 6] = (byte) (values[i] >> 48);
            asBytes[i * 8 + 7] = (byte) (values[i] >> 56);
        }
        long expected = FnvHasher.fnv1a_64(asBytes);
        long actual = new LongFnv1aHasher().update(values).digest();
        assertEquals(expected, actual);
    }

    @Test
    void bigIntFnv1_singleByte() {
        for (byte b = -128; b < 127; b++) {
            BigInteger expected = FnvHasher.fnv1(
                new byte[]{b},
                BigIntegerFnv1Hasher.INIT32,
                BigIntegerFnv1Hasher.PRIME32,
                BigIntegerFnv1Hasher.MOD32
            );
            long actual = new BigIntegerFnv1Hasher(
                BigIntegerFnv1Hasher.INIT32,
                BigIntegerFnv1Hasher.PRIME32,
                BigIntegerFnv1Hasher.MOD32
            ).update(b).digest();
            assertEquals(expected.longValue(), actual, "Failed for byte value " + (b & 0xFF));
        }
    }

    @Test
    void bigIntFnv1_short() {
        short value = 0x1234;
        byte[] asBytes = new byte[]{(byte) (value >> 0), (byte) (value >> 8)};
        BigInteger expected = FnvHasher.fnv1(
            asBytes,
            BigIntegerFnv1Hasher.INIT32,
            BigIntegerFnv1Hasher.PRIME32,
            BigIntegerFnv1Hasher.MOD32
        );
        long actual = new BigIntegerFnv1Hasher(
            BigIntegerFnv1Hasher.INIT32,
            BigIntegerFnv1Hasher.PRIME32,
            BigIntegerFnv1Hasher.MOD32
        ).update(value).digest();
        assertEquals(expected.longValue(), actual);
    }

    @Test
    void bigIntFnv1_int() {
        int value = 0xDEADBEEF;
        byte[] asBytes = new byte[]{
            (byte) (value >> 0), (byte) (value >> 8),
            (byte) (value >> 16), (byte) (value >> 24)
        };
        BigInteger expected = FnvHasher.fnv1(
            asBytes,
            BigIntegerFnv1Hasher.INIT32,
            BigIntegerFnv1Hasher.PRIME32,
            BigIntegerFnv1Hasher.MOD32
        );
        long actual = new BigIntegerFnv1Hasher(
            BigIntegerFnv1Hasher.INIT32,
            BigIntegerFnv1Hasher.PRIME32,
            BigIntegerFnv1Hasher.MOD32
        ).update(value).digest();
        assertEquals(expected.longValue(), actual);
    }

    @Test
    void bigIntFnv1_long() {
        long value = 0x0123456789ABCDEFL;
        byte[] asBytes = new byte[]{
            (byte) (value >> 0), (byte) (value >> 8),
            (byte) (value >> 16), (byte) (value >> 24),
            (byte) (value >> 32), (byte) (value >> 40),
            (byte) (value >> 48), (byte) (value >> 56)
        };
        BigInteger expected = FnvHasher.fnv1(
            asBytes,
            BigIntegerFnv1Hasher.INIT32,
            BigIntegerFnv1Hasher.PRIME32,
            BigIntegerFnv1Hasher.MOD32
        );
        long actual = new BigIntegerFnv1Hasher(
            BigIntegerFnv1Hasher.INIT32,
            BigIntegerFnv1Hasher.PRIME32,
            BigIntegerFnv1Hasher.MOD32
        ).update(value).digest();
        assertEquals(expected.longValue(), actual);
    }

    @Test
    void bigIntFnv1_byteArray() {
        byte[] data = "hello world".getBytes();
        BigInteger expected = FnvHasher.fnv1(
            data,
            BigIntegerFnv1Hasher.INIT32,
            BigIntegerFnv1Hasher.PRIME32,
            BigIntegerFnv1Hasher.MOD32
        );
        long actual = new BigIntegerFnv1Hasher(
            BigIntegerFnv1Hasher.INIT32,
            BigIntegerFnv1Hasher.PRIME32,
            BigIntegerFnv1Hasher.MOD32
        ).update(data).digest();
        assertEquals(expected.longValue(), actual);
    }

    @Test
    void bigIntFnv1_byteArray_empty() {
        byte[] data = new byte[0];
        BigInteger expected = FnvHasher.fnv1(
            data,
            BigIntegerFnv1Hasher.INIT32,
            BigIntegerFnv1Hasher.PRIME32,
            BigIntegerFnv1Hasher.MOD32
        );
        long actual = new BigIntegerFnv1Hasher(
            BigIntegerFnv1Hasher.INIT32,
            BigIntegerFnv1Hasher.PRIME32,
            BigIntegerFnv1Hasher.MOD32
        ).update(data).digest();
        assertEquals(expected.longValue(), actual);
    }

    @Test
    void bigIntFnv1_shortArray() {
        short[] values = {0x1234, 0x5678, (short) 0x9ABC};
        byte[] asBytes = new byte[values.length * 2];
        for (int i = 0; i < values.length; i++) {
            asBytes[i * 2] = (byte) (values[i] >> 0);
            asBytes[i * 2 + 1] = (byte) (values[i] >> 8);
        }
        BigInteger expected = FnvHasher.fnv1(
            asBytes,
            BigIntegerFnv1Hasher.INIT32,
            BigIntegerFnv1Hasher.PRIME32,
            BigIntegerFnv1Hasher.MOD32
        );
        long actual = new BigIntegerFnv1Hasher(
            BigIntegerFnv1Hasher.INIT32,
            BigIntegerFnv1Hasher.PRIME32,
            BigIntegerFnv1Hasher.MOD32
        ).update(values).digest();
        assertEquals(expected.longValue(), actual);
    }

    @Test
    void bigIntFnv1_intArray() {
        int[] values = {0xDEADBEEF, 0xCAFE0123};
        byte[] asBytes = new byte[values.length * 4];
        for (int i = 0; i < values.length; i++) {
            asBytes[i * 4] = (byte) (values[i] >> 0);
            asBytes[i * 4 + 1] = (byte) (values[i] >> 8);
            asBytes[i * 4 + 2] = (byte) (values[i] >> 16);
            asBytes[i * 4 + 3] = (byte) (values[i] >> 24);
        }
        BigInteger expected = FnvHasher.fnv1(
            asBytes,
            BigIntegerFnv1Hasher.INIT32,
            BigIntegerFnv1Hasher.PRIME32,
            BigIntegerFnv1Hasher.MOD32
        );
        long actual = new BigIntegerFnv1Hasher(
            BigIntegerFnv1Hasher.INIT32,
            BigIntegerFnv1Hasher.PRIME32,
            BigIntegerFnv1Hasher.MOD32
        ).update(values).digest();
        assertEquals(expected.longValue(), actual);
    }

    @Test
    void bigIntFnv1_longArray() {
        long[] values = {0x0123456789ABCDEFL, 0xFEDCBA9876543210L};
        byte[] asBytes = new byte[values.length * 8];
        for (int i = 0; i < values.length; i++) {
            asBytes[i * 8] = (byte) (values[i] >> 0);
            asBytes[i * 8 + 1] = (byte) (values[i] >> 8);
            asBytes[i * 8 + 2] = (byte) (values[i] >> 16);
            asBytes[i * 8 + 3] = (byte) (values[i] >> 24);
            asBytes[i * 8 + 4] = (byte) (values[i] >> 32);
            asBytes[i * 8 + 5] = (byte) (values[i] >> 40);
            asBytes[i * 8 + 6] = (byte) (values[i] >> 48);
            asBytes[i * 8 + 7] = (byte) (values[i] >> 56);
        }
        BigInteger expected = FnvHasher.fnv1(
            asBytes,
            BigIntegerFnv1Hasher.INIT32,
            BigIntegerFnv1Hasher.PRIME32,
            BigIntegerFnv1Hasher.MOD32
        );
        long actual = new BigIntegerFnv1Hasher(
            BigIntegerFnv1Hasher.INIT32,
            BigIntegerFnv1Hasher.PRIME32,
            BigIntegerFnv1Hasher.MOD32
        ).update(values).digest();
        assertEquals(expected.longValue(), actual);
    }

    @Test
    void bigIntFnv1a_singleByte() {
        for (byte b = -128; b < 127; b++) {
            BigInteger expected = FnvHasher.fnv1a(
                new byte[]{b},
                BigIntegerFnv1aHasher.INIT32,
                BigIntegerFnv1aHasher.PRIME32,
                BigIntegerFnv1aHasher.MOD32
            );
            long actual = new BigIntegerFnv1aHasher(
                BigIntegerFnv1aHasher.INIT32,
                BigIntegerFnv1aHasher.PRIME32,
                BigIntegerFnv1aHasher.MOD32
            ).update(b).digest();
            assertEquals(expected.longValue(), actual, "Failed for byte value " + (b & 0xFF));
        }
    }

    @Test
    void bigIntFnv1a_short() {
        short value = 0x1234;
        byte[] asBytes = new byte[]{(byte) (value >> 0), (byte) (value >> 8)};
        BigInteger expected = FnvHasher.fnv1a(
            asBytes,
            BigIntegerFnv1aHasher.INIT32,
            BigIntegerFnv1aHasher.PRIME32,
            BigIntegerFnv1aHasher.MOD32
        );
        long actual = new BigIntegerFnv1aHasher(
            BigIntegerFnv1aHasher.INIT32,
            BigIntegerFnv1aHasher.PRIME32,
            BigIntegerFnv1aHasher.MOD32
        ).update(value).digest();
        assertEquals(expected.longValue(), actual);
    }

    @Test
    void bigIntFnv1a_int() {
        int value = 0xDEADBEEF;
        byte[] asBytes = new byte[]{
            (byte) (value >> 0), (byte) (value >> 8),
            (byte) (value >> 16), (byte) (value >> 24)
        };
        BigInteger expected = FnvHasher.fnv1a(
            asBytes,
            BigIntegerFnv1aHasher.INIT32,
            BigIntegerFnv1aHasher.PRIME32,
            BigIntegerFnv1aHasher.MOD32
        );
        long actual = new BigIntegerFnv1aHasher(
            BigIntegerFnv1aHasher.INIT32,
            BigIntegerFnv1aHasher.PRIME32,
            BigIntegerFnv1aHasher.MOD32
        ).update(value).digest();
        assertEquals(expected.longValue(), actual);
    }

    @Test
    void bigIntFnv1a_long() {
        long value = 0x0123456789ABCDEFL;
        byte[] asBytes = new byte[]{
            (byte) (value >> 0), (byte) (value >> 8),
            (byte) (value >> 16), (byte) (value >> 24),
            (byte) (value >> 32), (byte) (value >> 40),
            (byte) (value >> 48), (byte) (value >> 56)
        };
        BigInteger expected = FnvHasher.fnv1a(
            asBytes,
            BigIntegerFnv1aHasher.INIT32,
            BigIntegerFnv1aHasher.PRIME32,
            BigIntegerFnv1aHasher.MOD32
        );
        long actual = new BigIntegerFnv1aHasher(
            BigIntegerFnv1aHasher.INIT32,
            BigIntegerFnv1aHasher.PRIME32,
            BigIntegerFnv1aHasher.MOD32
        ).update(value).digest();
        assertEquals(expected.longValue(), actual);
    }

    @Test
    void bigIntFnv1a_byteArray() {
        byte[] data = "hello world".getBytes();
        BigInteger expected = FnvHasher.fnv1a(
            data,
            BigIntegerFnv1aHasher.INIT32,
            BigIntegerFnv1aHasher.PRIME32,
            BigIntegerFnv1aHasher.MOD32
        );
        long actual = new BigIntegerFnv1aHasher(
            BigIntegerFnv1aHasher.INIT32,
            BigIntegerFnv1aHasher.PRIME32,
            BigIntegerFnv1aHasher.MOD32
        ).update(data).digest();
        assertEquals(expected.longValue(), actual);
    }

    @Test
    void bigIntFnv1a_byteArray_empty() {
        byte[] data = new byte[0];
        BigInteger expected = FnvHasher.fnv1a(
            data,
            BigIntegerFnv1aHasher.INIT32,
            BigIntegerFnv1aHasher.PRIME32,
            BigIntegerFnv1aHasher.MOD32
        );
        long actual = new BigIntegerFnv1aHasher(
            BigIntegerFnv1aHasher.INIT32,
            BigIntegerFnv1aHasher.PRIME32,
            BigIntegerFnv1aHasher.MOD32
        ).update(data).digest();
        assertEquals(expected.longValue(), actual);
    }

    @Test
    void bigIntFnv1a_shortArray() {
        short[] values = {0x1234, 0x5678, (short) 0x9ABC};
        byte[] asBytes = new byte[values.length * 2];
        for (int i = 0; i < values.length; i++) {
            asBytes[i * 2] = (byte) (values[i] >> 0);
            asBytes[i * 2 + 1] = (byte) (values[i] >> 8);
        }
        BigInteger expected = FnvHasher.fnv1a(
            asBytes,
            BigIntegerFnv1aHasher.INIT32,
            BigIntegerFnv1aHasher.PRIME32,
            BigIntegerFnv1aHasher.MOD32
        );
        long actual = new BigIntegerFnv1aHasher(
            BigIntegerFnv1aHasher.INIT32,
            BigIntegerFnv1aHasher.PRIME32,
            BigIntegerFnv1aHasher.MOD32
        ).update(values).digest();
        assertEquals(expected.longValue(), actual);
    }

    @Test
    void bigIntFnv1a_intArray() {
        int[] values = {0xDEADBEEF, 0xCAFE0123};
        byte[] asBytes = new byte[values.length * 4];
        for (int i = 0; i < values.length; i++) {
            asBytes[i * 4] = (byte) (values[i] >> 0);
            asBytes[i * 4 + 1] = (byte) (values[i] >> 8);
            asBytes[i * 4 + 2] = (byte) (values[i] >> 16);
            asBytes[i * 4 + 3] = (byte) (values[i] >> 24);
        }
        BigInteger expected = FnvHasher.fnv1a(
            asBytes,
            BigIntegerFnv1aHasher.INIT32,
            BigIntegerFnv1aHasher.PRIME32,
            BigIntegerFnv1aHasher.MOD32
        );
        long actual = new BigIntegerFnv1aHasher(
            BigIntegerFnv1aHasher.INIT32,
            BigIntegerFnv1aHasher.PRIME32,
            BigIntegerFnv1aHasher.MOD32
        ).update(values).digest();
        assertEquals(expected.longValue(), actual);
    }

    @Test
    void bigIntFnv1a_longArray() {
        long[] values = {0x0123456789ABCDEFL, 0xFEDCBA9876543210L};
        byte[] asBytes = new byte[values.length * 8];
        for (int i = 0; i < values.length; i++) {
            asBytes[i * 8] = (byte) (values[i] >> 0);
            asBytes[i * 8 + 1] = (byte) (values[i] >> 8);
            asBytes[i * 8 + 2] = (byte) (values[i] >> 16);
            asBytes[i * 8 + 3] = (byte) (values[i] >> 24);
            asBytes[i * 8 + 4] = (byte) (values[i] >> 32);
            asBytes[i * 8 + 5] = (byte) (values[i] >> 40);
            asBytes[i * 8 + 6] = (byte) (values[i] >> 48);
            asBytes[i * 8 + 7] = (byte) (values[i] >> 56);
        }
        BigInteger expected = FnvHasher.fnv1a(
            asBytes,
            BigIntegerFnv1aHasher.INIT32,
            BigIntegerFnv1aHasher.PRIME32,
            BigIntegerFnv1aHasher.MOD32
        );
        long actual = new BigIntegerFnv1aHasher(
            BigIntegerFnv1aHasher.INIT32,
            BigIntegerFnv1aHasher.PRIME32,
            BigIntegerFnv1aHasher.MOD32
        ).update(values).digest();
        assertEquals(expected.longValue(), actual);
    }

    @Test
    void bigIntFnv1_64bit_singleByte() {
        for (byte b = -128; b < 127; b++) {
            BigInteger expected = FnvHasher.fnv1(
                new byte[]{b},
                BigIntegerFnv1Hasher.INIT64,
                BigIntegerFnv1Hasher.PRIME64,
                BigIntegerFnv1Hasher.MOD64
            );
            long actual = new BigIntegerFnv1Hasher(
                BigIntegerFnv1Hasher.INIT64,
                BigIntegerFnv1Hasher.PRIME64,
                BigIntegerFnv1Hasher.MOD64
            ).update(b).digest();
            assertEquals(expected.longValue(), actual, "Failed for byte value " + (b & 0xFF));
        }
    }

    @Test
    void bigIntFnv1a_64bit_singleByte() {
        for (byte b = -128; b < 127; b++) {
            BigInteger expected = FnvHasher.fnv1a(
                new byte[]{b},
                BigIntegerFnv1aHasher.INIT64,
                BigIntegerFnv1aHasher.PRIME64,
                BigIntegerFnv1aHasher.MOD64
            );
            long actual = new BigIntegerFnv1aHasher(
                BigIntegerFnv1aHasher.INIT64,
                BigIntegerFnv1aHasher.PRIME64,
                BigIntegerFnv1aHasher.MOD64
            ).update(b).digest();
            assertEquals(expected.longValue(), actual, "Failed for byte value " + (b & 0xFF));
        }
    }
}
