package com.architecture.Instruction.Instructions;

import com.architecture.CPU.CPU;
import com.architecture.Instruction.AbstractInstruction;
import com.architecture.Instruction.Address;
import com.architecture.RAM.RAM;

public final class JUMP_PLUS_M_X_R extends AbstractInstruction {
    public JUMP_PLUS_M_X_R(short address) {
        super();
        setAddress(address);
    }
    @Override
    public void execute() {
        if (getAddress() == null) {
            throw new IllegalStateException("Address is null for JUMP_PLUS_M_X_R");
        }
        CPU cpu = CPU.getInstance();
        RAM ram = RAM.getInstance();
        if (cpu.AC >= 0) {
            cpu.PC = getAddress().getValue();
            cpu.IBR = ram.get(new Address(cpu.PC)).getRight();
        }
    }
}