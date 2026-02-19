package com.architecture.Instruction.Instructions;

import com.architecture.CPU.CPU;
import com.architecture.Instruction.AbstractInstruction;

public final class JUMP_M_X_L extends AbstractInstruction {
    public JUMP_M_X_L(short address) {
        super();
        setAddress(address);
    }

    @Override
    public void execute() {
        if (getAddress() == null) {
            throw new IllegalStateException("Address is null for JUMP_M_X_L");
        }
        CPU cpu = CPU.getInstance();
        cpu.PC = getAddress().getValue();
        cpu.IBR = null; //clearing IBR to force load full word
    }
}