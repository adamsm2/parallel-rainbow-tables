package com.adamsm2;

import java.util.Map;

public class RainbowTable {
    private final Map<String, String> table;
    private final int chainLength;
    private final int tableSize;

    public RainbowTable(Map<String, String> table, int chainLength, int tableSize) {
        this.table = table;
        this.chainLength = chainLength;
        this.tableSize = tableSize;
    }

    public int getElementsInTableNumber() {
        return table.size();
    }

    public void generateRainbowTable(int threadsNumber, String plainText) {
        Thread[] threads = new Thread[threadsNumber];
        int chainsPerThread = tableSize / threadsNumber;
        int rest = tableSize % threadsNumber;
        for (int i = 0; i < threadsNumber; i++) {
            int chainsNumber = chainsPerThread;
            if (i == threadsNumber - 1) {
                chainsNumber += rest;
            }
            ChainGenerator chainGenerator = new ChainGenerator(chainLength, chainsNumber, table, plainText);
            Thread thread = new Thread(chainGenerator);
            thread.start();
            threads[i] = thread;
        }
        waitTilAllThreadsFinish(threads);
    }

    private void waitTilAllThreadsFinish(Thread[] threads) {
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted");
            }
        }
    }

}
