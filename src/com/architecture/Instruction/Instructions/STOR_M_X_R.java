package com.architecture.Instruction.Instructions;

import com.architecture.CPU.CPU;
import com.architecture.Instruction.AbstractInstruction;

public final class STOR_M_X_R extends AbstractInstruction {
    public STOR_M_X_R(short address) {
        super();
        setAddress(address);
    }

    @Override
    public void execute(CPU cpu) {

    }
}