package com.architecture.Instruction.Instructions;

import com.architecture.CPU.CPU;
import static com.architecture.Globals.MASK40;
import com.architecture.Instruction.AbstractInstruction;
import com.architecture.RAM.RAM;

public final class DIV_M_X extends AbstractInstruction {
    public DIV_M_X(short address) {
        super();
        setAddress(address);
    }

    @Override
    public void execute() {
        if (getAddress() == null) {
            throw new IllegalStateException("Address is null for DIV_M_X");
        }
        CPU cpu = CPU.getInstance();
        RAM ram = RAM.getInstance();
        long quotient = cpu.AC / ram.get(getAddress()).getValue();
        long remainder = cpu.AC % ram.get(getAddress()).getValue();
        quotient &= MASK40;
        remainder &= MASK40;
        cpu.MQ = quotient;
        cpu.AC = remainder;
    }
}