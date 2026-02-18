package com.architecture;

import com.architecture.Instruction.InstructionFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("missing file name");
            System.exit(1);
        }

        InstructionFactory instructionFactory = InstructionFactory.getInstance();

        for (String s : readFile(args[0])) {
            System.out.println(s);
        }
    }

    public static ArrayList<String> readFile(String path) {
        ArrayList<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading file.");
        }

        return lines;
    }

}
