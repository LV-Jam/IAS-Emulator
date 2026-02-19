package com.architecture.Instruction.Instructions;

import com.architecture.CPU.CPU;
import com.architecture.Instruction.AbstractInstruction;
import com.architecture.RAM.RAM;
import com.architecture.RAM.Word;

public final class STOR_M_X extends AbstractInstruction {
    public STOR_M_X(short address) {
        super();
        setAddress(address);
    }

    @Override
    public void execute() {
        if (getAddress() == null) {
            throw new IllegalStateException("Address is null for STOR_M_X");
        }
        CPU cpu = CPU.getInstance();
        RAM ram = RAM.getInstance();
        ram.set(getAddress(), new Word(cpu.AC));
    }
}