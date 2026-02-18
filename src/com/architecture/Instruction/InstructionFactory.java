package com.architecture.Instruction;

import com.architecture.Nullable;

public class InstructionFactory {
    private static InstructionFactory instance;

    private InstructionFactory() {}

    public static InstructionFactory getInstance() {
        if (instance == null) {
            instance = new InstructionFactory();
            return instance;
        }
        return instance;
    }

    public AbstractInstruction create(Symbolic type, Nullable<Address> address) {

    }
}
