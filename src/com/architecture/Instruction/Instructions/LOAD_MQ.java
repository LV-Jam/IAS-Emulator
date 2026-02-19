package com.architecture.Instruction.Instructions;

import com.architecture.CPU.CPU;
import com.architecture.Instruction.AbstractInstruction;

public final class LOAD_MQ extends AbstractInstruction {
    public LOAD_MQ() {
        super();
    }

    @Override
    public void execute() {
        CPU cpu = CPU.getInstance();
        cpu.AC = cpu.MQ;
    }
}