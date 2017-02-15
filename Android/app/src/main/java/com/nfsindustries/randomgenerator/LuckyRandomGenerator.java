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

    public LuckyRandomGenerator(final String locale) {
        if (locale.equalsIgnoreCase("uk")) {
            maxIntValue = biggestAllowedValueInUk;
        }
    }

    public LuckyRandomGenerator(final int min, final int max) {
        minIntValue = min;
        maxIntValue = max;
    }

    public LuckyRandomGenerator(final int max) {
        maxIntValue = max;
    }

    /**
     * Rolled a fair dice, guaranteed to be random
     */
    public int generateRandomNumber(final int min, final int max) {
        return random.nextInt(max - min + 1) + min;
    }

    public Set generateLotteryTickets(final int quantity) {
        final SortedSet<Integer> tickets = new TreeSet<Integer>();
        while(tickets.size() < quantity) {
            tickets.add(generateRandomNumber());
        }
        return tickets;
    }

    public Set<Integer> generateLotteryTickets(final int quantity, final int min, final int max) {
        minIntValue = min;
        maxIntValue = max;
        return generateLotteryTickets(quantity);
    }

    public String generateRandomString(final int length, final String aSaltString) {
        saltchars = aSaltString;
        return generateRandomString(length);
    }

    public String generateRandomString(final int length) {
        if (length < 1) {
            throw new IllegalArgumentException("length < 1: " + length);
        }
        final StringBuilder salt = new StringBuilder();
        while (salt.length() < length) {
            final int index = (int) (random.nextFloat() * saltchars.length());
            salt.append(saltchars.charAt(index));
        }
        final String saltStr = salt.toString();
        return saltStr;
    }

    public Set generateLotteryTickets(final int quantity, final int max) {
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

