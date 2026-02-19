package com.architecture.Instruction.Instructions;

import com.architecture.CPU.CPU;
import com.architecture.Instruction.AbstractInstruction;

public final class LSH extends AbstractInstruction {
    public LSH() {
        super();
    }

    @Override
    public void execute() {
        CPU cpu = CPU.getInstance();
        cpu.AC = cpu.AC << 1;
    }
}