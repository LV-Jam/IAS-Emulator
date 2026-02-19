package com.architecture;

import com.architecture.CPU.CPU;
import com.architecture.Instruction.AbstractInstruction;
import com.architecture.Instruction.Address;
import com.architecture.Instruction.InstructionFactory;
import com.architecture.Instruction.Symbolic;
import com.architecture.RAM.RAM;
import com.architecture.RAM.Word;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("missing file name" );
            System.exit(1);
        }
        System.out.println(args[0]);
        parseFile(args[0]);
        CPU cpu = CPU.getInstance();
        do {
            cpu.fetchAndDecode();
            cpu.execute();
            System.out.printf(
                    "IR %d, MAR %d, IBR %s, MBR %d, AC %d, MQ %d, PC %d\n",
                    cpu.IR.opcode, cpu.MAR.getValue(), cpu.IBR, cpu.MBR.getValue(),
                    cpu.AC, cpu.MQ, cpu.PC
            );
            System.out.println(Arrays.toString(RAM.getInstance().getArray()));
        } while (cpu.IR != null);
    }

    public static void parseFile(String path) {
        RAM ram = RAM.getInstance();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            short address = 0;
            while (address < RAM.MEMORY_SIZE && (line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith(";" )) {
                    continue;
                }

                Word word = parseLine(line);
                if (word != null) {
                    ram.set(new Address(address), word);
                    address++;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file." );
        }
    }

    public static Word parseLine(String line) {
        // Try to parse as numeric data first
        try {
            long value = Long.parseLong(line.trim());
            return new Word(value);
        } catch (NumberFormatException e) {
            // Not a number, parse as instructions
        }

        // Split by semicolon to get left and right instructions
        String[] parts = line.split(";", 2);
        String leftStr = parts[0].trim();
        String rightStr = parts.length > 1 ? parts[1].trim() : "";

        AbstractInstruction leftInstr = parseInstruction(leftStr);
        AbstractInstruction rightInstr = parseInstruction(rightStr);

        if (leftInstr == null && rightInstr == null) {
            return null;
        }

        long wordValue = 0;

        if (leftInstr != null) {
            Symbolic leftType = Globals.symbolicFromClass(leftInstr.getClass());
            short leftAddr = leftInstr.getAddress() != null ? leftInstr.getAddress().getValue() : 0;
            wordValue |= (((long) leftType.opcode & Globals.MASK8) << 12 | (leftAddr & Globals.MASK12)) << 20;
        }

        if (rightInstr != null) {
            Symbolic rightType = Globals.symbolicFromClass(rightInstr.getClass());
            short rightAddr = rightInstr.getAddress() != null ? rightInstr.getAddress().getValue() : 0;
            wordValue |= ((long) rightType.opcode & Globals.MASK8) << 12 | (rightAddr & Globals.MASK12);
        }

        return new Word(wordValue);
    }

    private static AbstractInstruction parseInstruction(String str) {
        if (str.isEmpty()) {
            return null;
        }

        String[] tokens = str.split("\\s+", 2);
        String opcode = tokens[0].toUpperCase();
        String operand = tokens.length > 1 ? tokens[1] : "";

        Symbolic symbolicType = determineSymbolic(opcode, operand);
        if (symbolicType == null) {
            return null;
        }

        short address = -1;
        if (!operand.isEmpty() && !symbolicType.name().endsWith("MQ" ) && !symbolicType.name().startsWith("LSH" ) && !symbolicType.name().startsWith("RSH" )) {
            address = extractAddress(operand);
        }

        Nullable<Address> addressWrapper = new Nullable<>(address >= 0 ? new Address(address) : null);
        return InstructionFactory.getInstance().create(symbolicType, addressWrapper);
    }

    private static Symbolic determineSymbolic(String opcode, String operand) {
        boolean hasOperand = !operand.isEmpty();
        boolean isNegative = operand.contains("-" );
        boolean isAbsolute = operand.contains("ABS" );

        if (opcode.equals("LOAD" )) {
            if (!hasOperand) {
                return Symbolic.LOAD_MQ;
            }
            if (isNegative && isAbsolute) {
                return Symbolic.LOAD_NEG_ABS_M_X;
            }
            if (isAbsolute) {
                return Symbolic.LOAD_ABS_M_X;
            }
            if (isNegative) {
                return Symbolic.LOAD_NEG_M_X;
            }
            return Symbolic.LOAD_M_X;
        } else if (opcode.equals("LOAD MQ" ) && hasOperand) {
            return Symbolic.LOAD_MQ_M_X;
        } else if (opcode.equals("LOAD MQ" )) {
            return Symbolic.LOAD_MQ;
        } else if (opcode.equals("STOR" )) {
            if (operand.contains("," ) || operand.contains("0:19" )) {
                return Symbolic.STOR_M_X_L;
            }
            if (operand.contains("20:39" )) {
                return Symbolic.STOR_M_X_R;
            }
            return Symbolic.STOR_M_X;
        } else if (opcode.equals("ADD" )) {
            if (isAbsolute) {
                return Symbolic.ADD_ABS_M_X;
            }
            return Symbolic.ADD_M_X;
        } else if (opcode.equals("SUB" )) {
            if (isAbsolute) {
                return Symbolic.SUB_ABS_M_X;
            }
            return Symbolic.SUB_M_X;
        } else if (opcode.equals("MUL" )) {
            return Symbolic.MUL_M_X;
        } else if (opcode.equals("DIV" )) {
            return Symbolic.DIV_M_X;
        } else if (opcode.equals("LSH" )) {
            return Symbolic.LSH;
        } else if (opcode.equals("RSH" )) {
            return Symbolic.RSH;
        } else if (opcode.equals("JUMP" )) {
            if (operand.contains("0:19" )) {
                return Symbolic.JUMP_M_X_L;
            }
            return Symbolic.JUMP_M_X_R;
        } else if (opcode.equals("JUMP+" )) {
            if (operand.contains("0:19" )) {
                return Symbolic.JUMP_PLUS_M_X_L;
            }
            return Symbolic.JUMP_PLUS_M_X_R;
        }

        return null;
    }

    private static short extractAddress(String operand) {
        // Extract address from formats like M(20), -M(20), M(9,0:19), M(0,20:39), etc.
        int startIdx = operand.indexOf('(');
        int endIdx = operand.indexOf(',');
        if (endIdx == -1) {
            endIdx = operand.indexOf(')');
        }

        if (startIdx != -1 && endIdx != -1) {
            String addressStr = operand.substring(startIdx + 1, endIdx).trim();
            try {
                return Short.parseShort(addressStr);
            } catch (NumberFormatException e) {
                return -1;
            }
        }

        return -1;
    }

}
