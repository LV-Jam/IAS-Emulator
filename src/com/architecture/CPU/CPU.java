package com.architecture.CPU;

import com.architecture.Globals;
import com.architecture.Instruction.Address;
import com.architecture.Instruction.AbstractInstruction;
import com.architecture.Instruction.InstructionFactory;
import com.architecture.Instruction.Symbolic;
import com.architecture.Nullable;
import com.architecture.RAM.RAM;
import com.architecture.RAM.Word;

public class CPU {
    private static CPU instance;

    private CPU() {
        this.ram = com.architecture.RAM.RAM.getInstance();
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

    public Symbolic IR; // Instruction Register
    public Address MAR; // Memory Address Register

    public AbstractInstruction IBR; // Instruction Buffer Register
    public Word MBR;      // Memory Buffer Register

    public RAM ram;

    public void fetchAndDecode() {
        MAR = new Address(PC);
        MBR = ram.get(MAR);
        if (IBR == null) {
            IBR = MBR.getRight();
            AbstractInstruction left = MBR.getLeft();
            IR = Globals.symbolicFromClass(left.getClass());
            MAR = left.getAddress();
        } else {
            IR = Globals.symbolicFromClass(IBR.getClass());
            MAR = IBR.getAddress();
            PC++;
        }
    }

    public void execute() {
        InstructionFactory
                .getInstance()
                .create(IR, new Nullable<>(MAR))
                .execute();
    }
}
