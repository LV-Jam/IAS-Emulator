package com.architecture.Instruction.Instructions;

import com.architecture.CPU.CPU;
import com.architecture.Instruction.AbstractInstruction;
import com.architecture.RAM.RAM;

public final class SUB_M_X extends AbstractInstruction {
    public SUB_M_X(short address) {
        super();
        setAddress(address);
    }

    @Override
    public void execute() {
        if (getAddress() == null) {
            throw new IllegalStateException("Address is null for SUB_M_X");
        }
        CPU cpu = CPU.getInstance();
        RAM ram = RAM.getInstance();
        cpu.AC -= ram.get(getAddress()).getValue();
    }
}