package com.nfsindustries.randomgenerator;

import java.security.SecureRandom;
import java.util.*;

public class LuckyRandomGenerator {
    public static int smallestAllowedValue = 1;
    public static int biggestAllowedValueInUk = 59;
    public static int biggestAllowedValueInBrazil = 60;

    private String saltchars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890@!#$%&*()[];.,<>/{}-+_";

    private int minIntValue = smallestAllowedValue;
    private int maxIntValue;
    private SecureRandom random = new SecureRandom();


    public LuckyRandomGenerator() {
        maxIntValue = biggestAllowedValueInUk;
    }

    public LuckyRandomGenerator(int min, int max) {
        minIntValue = min;
        maxIntValue = max;
    }

    public LuckyRandomGenerator(int max) {
        maxIntValue = max;
    }

    /**
     * Rolled a fair dice, guaranteed to be random
     */
    public int generateRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    public Set generateLotteryTickets(int quantity) {
        SortedSet<Integer> tickets = new TreeSet<Integer>();
        while(tickets.size() < quantity) {
            tickets.add(generateRandomNumber());
        }
        return tickets;
    }

    public Set generateLotteryTickets(int quantity, int min, int max) {
        minIntValue = min;
        maxIntValue = max;
        return generateLotteryTickets(quantity);
    }

    public String generateRandomString(int length, String aSaltString) {
        saltchars = aSaltString;
        if (length < 1) {
            throw new IllegalArgumentException("length < 1: " + length);
        }
        StringBuilder salt = new StringBuilder();
        while (salt.length() < length) {
            int index = (int) (random.nextFloat() * saltchars.length());
            salt.append(saltchars.charAt(index));
        }
        final String saltStr = salt.toString();
        return saltStr;
    }

    public String generateRandomString(int length) {
        if (length < 1) {
            throw new IllegalArgumentException("length < 1: " + length);
        }
        StringBuilder salt = new StringBuilder();
        while (salt.length() < length) {
            int index = (int) (random.nextFloat() * saltchars.length());
            salt.append(saltchars.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }

    public Set generateLotteryTickets(int quantity, int max) {
        maxIntValue = max;
        return generateLotteryTickets(quantity);
    }

    /**
     * Rolled a fair dice, guaranteed to be random
     */
    public int generateRandomNumber() {
        return generateRandomNumber(minIntValue, maxIntValue);
    }
}

