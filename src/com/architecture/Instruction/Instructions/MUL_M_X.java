package com.architecture.Instruction.Instructions;

import com.architecture.CPU.CPU;
import static com.architecture.Globals.MASK20;
import static com.architecture.Globals.MASK40;
import com.architecture.Instruction.AbstractInstruction;
import com.architecture.RAM.RAM;

public final class MUL_M_X extends AbstractInstruction {
    public MUL_M_X(short address) {
        super();
        setAddress(address);
    }

    @Override
    public void execute() {
        if (getAddress() == null) {
            throw new IllegalStateException("Address is null for MUL_M_X");
        }
        CPU cpu = CPU.getInstance();
        RAM ram = RAM.getInstance();
        long product = cpu.MQ * ram.get(getAddress()).getValue();
        product &= MASK40;
        cpu.MQ = ((product) & MASK20);
        cpu.AC = ((product >>> 20) & MASK20);
    }
}