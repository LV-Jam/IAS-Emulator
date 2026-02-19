package com.architecture.Instruction.Instructions;

import com.architecture.CPU.CPU;
import com.architecture.Instruction.AbstractInstruction;
import com.architecture.RAM.RAM;

public final class LOAD_NEG_M_X extends AbstractInstruction {
    public LOAD_NEG_M_X(short address) {
        super();
        setAddress(address);
    }

    @Override
    public void execute() {
        if (getAddress() == null) {
            throw new IllegalStateException("Address is null for LOAD_NEG_M_X");
        }
        CPU cpu = CPU.getInstance();
        RAM ram = RAM.getInstance();
        cpu.AC = - ram.get(this.getAddress()).getValue();
    }
}