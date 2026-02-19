package com.architecture.Instruction.Instructions;

import com.architecture.CPU.CPU;
import com.architecture.Instruction.AbstractInstruction;

public final class JUMP_PLUS_M_X_L extends AbstractInstruction {
    public JUMP_PLUS_M_X_L(short address) {
        super();
        setAddress(address);
    }

    @Override
    public void execute() {
        if (getAddress() == null) {
            throw new IllegalStateException("Address is null for JUMP_PLUS_M_X_L");
        }
        CPU cpu = CPU.getInstance();
        if (cpu.AC >= 0) {
            cpu.PC = getAddress().getValue();
            cpu.IBR = null; //clearing IBR to force load full word
        }
    }
}