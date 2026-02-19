package com.architecture.RAM;

import com.architecture.Globals;
import com.architecture.Instruction.AbstractInstruction;
import com.architecture.Instruction.Address;
import com.architecture.Instruction.InstructionFactory;
import com.architecture.Instruction.Symbolic;
import com.architecture.Nullable;

import java.lang.reflect.Type;

import static com.architecture.Globals.*;

public class Word {
    private long value;


    public Word() {
        setValue(0L);
    }

    ;

    public Word(long value) {
        setValue(value);
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value & MASK40;
    }

    public AbstractInstruction getLeft() {
        int bits = (int) ((value >>> 20) & MASK20);
        Nullable<Address> address = new Nullable<>(new Address(
                (short) (bits & MASK12)
        ));
        Symbolic type = Globals.symbolicFromByte((byte) ((bits >>> 12) & MASK8));
        return InstructionFactory.getInstance().create(type, address);
    }

    public AbstractInstruction getRight() {
        int bits = (int) ((value) & MASK20);
        Nullable<Address> address = new Nullable<>(new Address(
                (short) (bits & MASK12)
        ));
        Symbolic type = Globals.symbolicFromByte((byte) ((bits >>> 12) & MASK8));
        return InstructionFactory.getInstance().create(type, address);
    }

    @Override
    public String toString() {
        return Long.toString(value);
    }
}
