//package com.nfsindustries.luckyrandomgenerator;

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


    /**
     * Default constructor sets the maximum random value to the largest constant available
     */
    public LuckyRandomGenerator() {
        maxIntValue = biggestAllowedValueInBrazil;
    }

    /**
     * constructor sets the maximum random value to the constant matching the locale passed
     * @param locale country string of the user [e.g. uk, us, br, etc.]
     */
    public LuckyRandomGenerator(final String locale) {
        if (locale.equalsIgnoreCase("uk")) {
            maxIntValue = biggestAllowedValueInUk;
        } else {
            maxIntValue = biggestAllowedValueInBrazil;
        }
    }

    /**
     * sets the min and max values to generate random numbers
     * @param min minimum value allowed to generate (including)
     * @param max maximum value allowed to generate (including)
     */
    public LuckyRandomGenerator(final int min, final int max) {
        minIntValue = min;
        maxIntValue = max;
    }

    /**
     * sets the max value to generate random numbers
     * @param max maximum value allowed to generate (including)
     */
    public LuckyRandomGenerator(final int max) {
        maxIntValue = max;
    }

    /**
     * Rolled a fair dice, guaranteed to be random
     * Generate a random number between min and max
     * @param min minimum value of random number to be generated
     * @param max maximum value of random number to be generated
     * @return the random number generated
     */
    public int generateRandomNumber(final int min, final int max) {
        return random.nextInt(max - min + 1) + min;
    }

    /**
     * Generate quantity number of lottery tickets (random numbers) between default min and
     * default max
     * @param quantity number of random numbers to be generated
     * @return set of random integers between min and max
     */
    public Set<Integer> generateLotteryTickets(final int quantity) {
        final SortedSet<Integer> tickets = new TreeSet<Integer>();
        while(tickets.size() < quantity) {
            tickets.add(generateRandomNumber());
        }
        return tickets;
    }

    /**
     * Generate quantity number of lottery tickets (random numbers) between min and max
     * @param quantity number of random numbers to be generated
     * @param min minimum value of random number to be generated
     * @param max maximum value of random number to be generated
     * @return set of random integers between min and max
     */
    public Set<Integer> generateLotteryTickets(final int quantity, final int min, final int max) {
        minIntValue = min;
        maxIntValue = max;
        return generateLotteryTickets(quantity);
    }

    /**
     * Generate quantity number of lottery tickets (random numbers) between min and max
     * @param length the length of the string to be generated
     * @param aSaltString the salt string containing the characters available to generate the string
     * @return set of random integers between min and max
     */
    public String generateRandomString(final int length, final String aSaltString) {
        saltchars = aSaltString;
        return generateRandomString(length);
    }

    /**
     * Generate quantity number of lottery tickets (random numbers) between min and max
     * @param length the length of the string to be generated
     * @param aSaltString the salt string containing the characters available to generate the string
     *                    to be concatenated to the constant string
     * @return set of random integers between min and max
     */
    public String generateRandomStringAppendingSaltString(final int length, final String aSaltString) {
        saltchars += aSaltString;
        return generateRandomString(length);
    }

    /**
     * generate a random string with the characters defined by the salt string
     * @param length the length of the string to be generated
     * @return random generated string
     */
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

    /**
     * Generate quantity number of lottery tickets (random numbers) between default min and max
     * @param quantity number of random numbers to be generated
     * @param max maximum value of random number to be generated
     * @return set of random integers between min and max
     */
    public Set generateLotteryTickets(final int quantity, final int max) {
        maxIntValue = max;
        return generateLotteryTickets(quantity);
    }

    /**
     * Rolled a fair dice, guaranteed to be random
     * Generate a random number between default min and default max
     * @return the random number generated
     */
    public int generateRandomNumber() {
        return generateRandomNumber(minIntValue, maxIntValue);
    }

  public void printLotteryTickets(final Set<Integer> tickets) {
    for (final int currentTicket : tickets) {
      System.out.println("Ticket: " + String.valueOf(currentTicket));
    }
  }

  public static void main(final String... args) {
    final LuckyRandomGenerator generator = new LuckyRandomGenerator();
    final int randomNumber = generator.generateRandomNumber();
    final String randomNumberString = String.valueOf(randomNumber);
    System.out.println("Random Number: " + randomNumberString);
    generator.printLotteryTickets(generator.generateLotteryTickets(6));
    System.out.println("Random String: " + generator.generateRandomString(10));
  }
}
