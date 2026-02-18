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

    public void setAddress(Address address, Word word) {
        memory[address.get()] = word;
    }

    public Word getAddress(Address address) {
        return memory[address.get()];
    }
}
