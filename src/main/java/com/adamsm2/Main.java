package com.adamsm2;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("3 args required: chain_length array_size threads_number");
            System.exit(1);
        }
        int threadsNumber = 0;
        int chainLength = 0;
        int tableSize = 0;
        try {
            chainLength = Integer.parseInt(args[0]);
            tableSize = Integer.parseInt(args[1]);
            threadsNumber = Integer.parseInt(args[2]);
        } catch (NumberFormatException e) {
            System.out.println("Invalid arguments. Please provide integers");
            System.exit(1);
        }

        String plainText = "password";
        Map<String, String> table = new HashMap<>();
        RainbowTable rainbowTable = new RainbowTable(table, chainLength, tableSize);

        rainbowTable.generateRainbowTable(threadsNumber, plainText);

    }
}