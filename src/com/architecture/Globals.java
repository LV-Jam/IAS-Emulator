package com.architecture;

import com.architecture.Instruction.AbstractInstruction;
import com.architecture.Instruction.Instructions.*;
import com.architecture.Instruction.Symbolic;

import java.util.Map;

public class Globals {

    public static final long MASK40 = 0xfffff_fffffL;
    public static final long MASK20 = 0xfffffL;
    public static final long MASK12 = 0xfffL;
    public static final long MASK8 = 0xffL;
    private static final Map<Byte, Symbolic> BYTE_TO_SYMBOLIC = Map.ofEntries(
            Map.entry((byte) 0b00001010, Symbolic.LOAD_MQ),
            Map.entry((byte) 0b00001001, Symbolic.LOAD_MQ_M_X),
            Map.entry((byte) 0b00100001, Symbolic.STOR_M_X),
            Map.entry((byte) 0b00000001, Symbolic.LOAD_M_X),
            Map.entry((byte) 0b00000010, Symbolic.LOAD_NEG_M_X),
            Map.entry((byte) 0b00000011, Symbolic.LOAD_ABS_M_X),
            Map.entry((byte) 0b00000100, Symbolic.LOAD_NEG_ABS_M_X),
            Map.entry((byte) 0b00001101, Symbolic.JUMP_M_X_L),
            Map.entry((byte) 0b00001110, Symbolic.JUMP_M_X_R),
            Map.entry((byte) 0b00001111, Symbolic.JUMP_PLUS_M_X_L),
            Map.entry((byte) 0b00010000, Symbolic.JUMP_PLUS_M_X_R),
            Map.entry((byte) 0b00000101, Symbolic.ADD_M_X),
            Map.entry((byte) 0b00000111, Symbolic.ADD_ABS_M_X),
            Map.entry((byte) 0b00000110, Symbolic.SUB_M_X),
            Map.entry((byte) 0b00001000, Symbolic.SUB_ABS_M_X),
            Map.entry((byte) 0b00001011, Symbolic.MUL_M_X),
            Map.entry((byte) 0b00001100, Symbolic.DIV_M_X),
            Map.entry((byte) 0b00010100, Symbolic.LSH),
            Map.entry((byte) 0b00010101, Symbolic.RSH),
            Map.entry((byte) 0b00010010, Symbolic.STOR_M_X_L),
            Map.entry((byte) 0b00010011, Symbolic.STOR_M_X_R)
    );

    private static final Map<Class<? extends AbstractInstruction>, Symbolic> CLASS_TO_SYMBOLIC = Map.ofEntries(
            Map.entry(LOAD_MQ.class, Symbolic.LOAD_MQ),
            Map.entry(LOAD_MQ_M_X.class, Symbolic.LOAD_MQ_M_X),
            Map.entry(STOR_M_X.class, Symbolic.STOR_M_X),
            Map.entry(LOAD_M_X.class, Symbolic.LOAD_M_X),
            Map.entry(LOAD_NEG_M_X.class, Symbolic.LOAD_NEG_M_X),
            Map.entry(LOAD_ABS_M_X.class, Symbolic.LOAD_ABS_M_X),
            Map.entry(LOAD_NEG_ABS_M_X.class, Symbolic.LOAD_NEG_ABS_M_X),
            Map.entry(JUMP_M_X_L.class, Symbolic.JUMP_M_X_L),
            Map.entry(JUMP_M_X_R.class, Symbolic.JUMP_M_X_R),
            Map.entry(JUMP_PLUS_M_X_L.class, Symbolic.JUMP_PLUS_M_X_L),
            Map.entry(JUMP_PLUS_M_X_R.class, Symbolic.JUMP_PLUS_M_X_R),
            Map.entry(ADD_M_X.class, Symbolic.ADD_M_X),
            Map.entry(ADD_ABS_M_X.class, Symbolic.ADD_ABS_M_X),
            Map.entry(SUB_M_X.class, Symbolic.SUB_M_X),
            Map.entry(SUB_ABS_M_X.class, Symbolic.SUB_ABS_M_X),
            Map.entry(MUL_M_X.class, Symbolic.MUL_M_X),
            Map.entry(DIV_M_X.class, Symbolic.DIV_M_X),
            Map.entry(LSH.class, Symbolic.LSH),
            Map.entry(RSH.class, Symbolic.RSH),
            Map.entry(STOR_M_X_L.class, Symbolic.STOR_M_X_L),
            Map.entry(STOR_M_X_R.class, Symbolic.STOR_M_X_R)
    );

    public static Symbolic symbolicFromByte(byte b) {
        return BYTE_TO_SYMBOLIC.get(b);
    }

    public static Symbolic symbolicFromClass(Class<? extends AbstractInstruction> c) {
        return CLASS_TO_SYMBOLIC.get(c);
    }
}
