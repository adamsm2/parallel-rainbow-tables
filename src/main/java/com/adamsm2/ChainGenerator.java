package com.adamsm2;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Random;

public class ChainGenerator implements Runnable {
    private final int chainLength;
    private final int chainsNumber;
    private final Map<String, String> rainbowTable;
    private final String textToEncrypt;

    public ChainGenerator(int chainLength, int chainsNumber, Map<String, String> rainbowTable, String textToEncrypt) {
        this.chainLength = chainLength;
        this.chainsNumber = chainsNumber;
        this.rainbowTable = rainbowTable;
        this.textToEncrypt = textToEncrypt;
    }

    @Override
    public void run() {
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("DES");
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            System.out.println("Cipher initialization failed");
            System.exit(2);
        }
        for (int i = 0; i < chainsNumber; i++) {
            String randomDesKey = generateRandomPlainText();
            String nextChainElement = encrypt(textToEncrypt, randomDesKey, cipher);
            for (int j = 0; j < chainLength; j++) {
                nextChainElement = reductionFunction(nextChainElement);
                nextChainElement = encrypt(nextChainElement, randomDesKey, cipher);
            }
            addToRainbowTable(nextChainElement, randomDesKey);
        }
    }

    private String generateRandomPlainText() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder plainText = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 8; i++) {
            int randIndex = random.nextInt(chars.length());
            plainText.append(chars.charAt(randIndex));
        }
        return plainText.toString();
    }

    private String encrypt(String plainText, String key, Cipher cipher) {
        Key desKey = new SecretKeySpec(key.getBytes(), "DES");
        byte[] encrypted = new byte[0];
        try {
            cipher.init(Cipher.ENCRYPT_MODE, desKey);
            encrypted = cipher.doFinal(plainText.getBytes());
        } catch (IllegalBlockSizeException | BadPaddingException | InvalidKeyException e) {
            System.out.println("Encryption failed");
            System.exit(3);
        }
        StringBuilder sb = new StringBuilder();
        for (byte b : encrypted) {
            sb.append(String.format("%02X", b));
        }
        return sb.toString();

    }

    private synchronized void addToRainbowTable(String lastHashInChain, String randomDesKey) {
        rainbowTable.put(lastHashInChain, randomDesKey);
    }

    private String reductionFunction(String cipherText) {
        return cipherText.substring(0, 8);
    }


}
