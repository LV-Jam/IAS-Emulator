package com.architecture.Instruction;

import com.architecture.RAM.RAM;

import static com.architecture.Globals.MASK12;

public class Address {
    private short value;

    public Address() {
        setValue((short) 0);
    }

    ;

    public Address(short value) {
        setValue(value);
    }

    public short getValue() {
        return value;
    }

    public void setValue(short value) {
        if (value < 0 || value >= RAM.MEMORY_SIZE) {
            throw new IllegalArgumentException(
                String.format(
                        "Address \"%d\" out of bounds",
                        value
                )
            );
        }
        this.value = (short) (value & MASK12);
    }
}
