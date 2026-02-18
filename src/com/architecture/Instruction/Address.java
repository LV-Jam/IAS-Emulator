package com.architecture.Instruction;

public class Address {
    private short value;
    private static final short MASK12 = (short) 0xfff;

    public Address() {
        set((short) 0);
    };

    public Address(short value) {
        set(value);
    }

    public short get() {
        return value;
    }

    public void set(short value) {
        this.value = (short) (value & MASK12);
    }
}
