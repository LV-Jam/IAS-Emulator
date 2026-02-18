package com.architecture.Instruction.Instructions;

import com.architecture.CPU.CPU;
import com.architecture.Instruction.AbstractInstruction;
import com.architecture.Instruction.Symbolic;

public final class _ extends AbstractInstruction {
    public _(short address) {
        super();
        setAddress(address);
    }

    @Override
    public void execute(CPU cpu) {

    }
}