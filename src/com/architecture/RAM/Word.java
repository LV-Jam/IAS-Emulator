package com.architecture.RAM;

public class Word {
    private long value;
    private static final long MASK40 = 0xfffff_fffffL;

    public Word() {
        set(0L);
    };

    public Word(long value) {
        set(value);
    }

    public long get() {
        return value;
    }

    public void set(long value) {
        this.value = value & MASK40;
    }
}
