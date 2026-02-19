package com.architecture.RAM;

import com.architecture.Instruction.Address;

public class RAM {
    private static RAM instance;
    private RAM() {
        memory = new Word[MEMORY_SIZE];
    }

    public static RAM getInstance() {
        if (instance == null) {
            instance = new RAM();
            return instance;
        }
        return instance;
    }

    public static final short MEMORY_SIZE = 4096;
    public Word[] memory;

    public void set(Address address, Word word) {
        memory[address.getValue()] = word;
    }

    public Word get(Address address) {
        return memory[address.getValue()];
    }

    public Word[] getArray() {
        return memory;
    }
}
