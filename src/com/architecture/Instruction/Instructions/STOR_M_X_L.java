package com.architecture.Instruction.Instructions;

import com.architecture.CPU.CPU;
import com.architecture.Globals;
import com.architecture.Instruction.AbstractInstruction;
import com.architecture.RAM.RAM;
import com.architecture.RAM.Word;

public final class STOR_M_X_L extends AbstractInstruction {
    private long OPMASK = 0b1111111100000000000000000000000000000000L;

    public STOR_M_X_L(short address) {
        super();
        setAddress(address);
    }

    @Override
    public void execute() {
        if (getAddress() == null) {
            throw new IllegalStateException("Address is null for STOR_M_X_L");
        }
        CPU cpu = CPU.getInstance();
        RAM ram = RAM.getInstance();
        long opcode = ram.get(getAddress()).getValue() & OPMASK;
        long newAddress = (cpu.AC & Globals.MASK12) << 20;
        ram.set(getAddress(), new Word(opcode | newAddress));
    }
}