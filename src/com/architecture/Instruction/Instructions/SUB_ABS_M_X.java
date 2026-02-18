package com.architecture.Instruction.Instructions;

import com.architecture.CPU.CPU;
import com.architecture.Instruction.AbstractInstruction;

public final class SUB_ABS_M_X extends AbstractInstruction {
    public SUB_ABS_M_X(short address) {
        super();
        setAddress(address);
    }

    @Override
    public void execute(CPU cpu) {

    }
}