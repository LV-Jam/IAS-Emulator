package com.architecture.Instruction.Instructions;

import com.architecture.CPU.CPU;
import com.architecture.Instruction.AbstractInstruction;

public final class RSH extends AbstractInstruction {
    public RSH() {
        super();
    }

    @Override
    public void execute() {
        CPU cpu = CPU.getInstance();
        cpu.AC = cpu.AC >> 1;
    }
}