package com.architecture.CPU;

import com.architecture.Instruction.Address;
import com.architecture.Instruction.AbstractInstruction;
import com.architecture.Instruction.InstructionFactory;
import com.architecture.Instruction.Symbolic;
import com.architecture.RAM.RAM;
import com.architecture.RAM.Word;

public class CPU {
    private static CPU instance;

    private CPU() {
        this.RAM = com.architecture.RAM.RAM.getInstance();
    }

    public static CPU getInstance() {
        if (instance == null) {
            instance = new CPU();
            return instance;
        }
        return instance;
    }

    // Registers
    public long AC;  // Accumulator
    public long MQ;  // Multiplier-Quotient
    public short PC; // Program Counter

    public Symbolic IR; // com.architecture.Instruction Register
    public Address MAR; // Memory Address Register

    public AbstractInstruction IBR; // com.architecture.Instruction Buffer Register
    public Word MBR;      // Memory Buffer Register

    public RAM RAM;
}
