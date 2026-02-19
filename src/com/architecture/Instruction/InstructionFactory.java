package com.architecture.Instruction;
import com.architecture.Instruction.Instructions.*;
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
        switch (type){
            case LOAD_MQ: {
                return new LOAD_MQ();
			}
            case LOAD_MQ_M_X: {
                return new LOAD_MQ_M_X(address.getValue().getValue());
			}
            case STOR_M_X: {
                return new STOR_M_X(address.getValue().getValue());
			}
            case LOAD_M_X: {

                return new LOAD_M_X(address.getValue().getValue());
			}
            case LOAD_NEG_M_X: {
                return new LOAD_NEG_M_X(address.getValue().getValue());
			}
            case LOAD_ABS_M_X: {
                return new LOAD_ABS_M_X(address.getValue().getValue());
			}
            case LOAD_NEG_ABS_M_X: {
                return new LOAD_NEG_ABS_M_X(address.getValue().getValue());
			}
            case JUMP_M_X_L: {
                return new JUMP_M_X_L(address.getValue().getValue());
			}
            case JUMP_M_X_R: {
                return new JUMP_M_X_R(address.getValue().getValue());
			}
            case JUMP_PLUS_M_X_L: {
                return new JUMP_PLUS_M_X_L(address.getValue().getValue());
			}
            case JUMP_PLUS_M_X_R: {
                return new JUMP_PLUS_M_X_R(address.getValue().getValue());
			}
            case ADD_M_X: {
                return new ADD_M_X(address.getValue().getValue());
			}
            case ADD_ABS_M_X: {
                return new ADD_ABS_M_X(address.getValue().getValue());
			}
            case SUB_M_X: {
                return new SUB_M_X(address.getValue().getValue());
			}
            case SUB_ABS_M_X: {
                return new SUB_ABS_M_X(address.getValue().getValue());
			}
            case MUL_M_X: {
                return new MUL_M_X(address.getValue().getValue());
			}
            case DIV_M_X: {
                return new DIV_M_X(address.getValue().getValue());
			}
            case LSH: {
                return new LSH();
			}
            case RSH: {
                return new RSH();
			}
            case STOR_M_X_L: {
                return new STOR_M_X_L(address.getValue().getValue());
			}
            case STOR_M_X_R: {
                return new STOR_M_X_R(address.getValue().getValue());
			}
            default: {
                throw new RuntimeException("Invalid Instruction");
            }
        }
    }
}
