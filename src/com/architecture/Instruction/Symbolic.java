package com.architecture.Instruction;

public enum Symbolic {
    LOAD_MQ             ((byte) 0b00001010),
    LOAD_MQ_M_X         ((byte) 0b00001001),
    STOR_M_X            ((byte) 0b00100001),
    LOAD_M_X            ((byte) 0b00000001),
    LOAD_NEG_M_X        ((byte) 0b00000010),
    LOAD_ABS_M_X        ((byte) 0b00000011),
    LOAD_NEG_ABS_M_X    ((byte) 0b00000100),
    JUMP_M_X_L          ((byte) 0b00001101),
    JUMP_M_X_R          ((byte) 0b00001110),
    JUMP_PLUS_M_X_L     ((byte) 0b00001111),
    JUMP_PLUS_M_X_R     ((byte) 0b00010000),
    ADD_M_X             ((byte) 0b00000101),
    ADD_ABS_M_X         ((byte) 0b00000111),
    SUB_M_X             ((byte) 0b00000110),
    SUB_ABS_M_X         ((byte) 0b00001000),
    MUL_M_X             ((byte) 0b00001011),
    DIV_M_X             ((byte) 0b00001100),
    LSH                 ((byte) 0b00010100),
    RSH                 ((byte) 0b00010101),
    STOR_M_X_L          ((byte) 0b00010010),
    STOR_M_X_R          ((byte) 0b00010011);
    public final byte opcode;

    private Symbolic(byte opcode) {
        this.opcode = opcode;
    }
}