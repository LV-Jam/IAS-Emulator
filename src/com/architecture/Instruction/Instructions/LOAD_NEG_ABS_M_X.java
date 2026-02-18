package com.architecture.Instruction.Instructions;

import com.architecture.CPU.CPU;
import com.architecture.Instruction.AbstractInstruction;

public final class LOAD_NEG_ABS_M_X extends AbstractInstruction {
    public LOAD_NEG_ABS_M_X(short address) {
        super();
        setAddress(address);
    }

    @Override
    public void execute(CPU cpu) {

    }
}