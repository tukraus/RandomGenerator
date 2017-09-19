package com.nfsindustries.luckyrandomgenerator;

import java.security.SecureRandom
import java.util.*

class LuckyRandomGenerator {

    private var saltchars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890@!#$%&*()[];.,<>/{}-+_"

    private var minIntValue = smallestAllowedValue
    private var maxIntValue: Int = 0
    private val random = SecureRandom()


    /**
     * Default constructor sets the maximum random value to the largest constant available
     */
    constructor() {
        maxIntValue = biggestAllowedValueInBrazil
    }

    /**
     * Constructor sets the maximum random value to the constant matching the locale passed
     * @param locale country string of the user [e.g. uk, us, br, etc.]
     */
    constructor(locale: String) {
        if (locale.equals("uk", ignoreCase = true)) {
            maxIntValue = biggestAllowedValueInUk
        } else {
            maxIntValue = biggestAllowedValueInBrazil
        }
    }

    /**
     * Sets the min and max values to generate random numbers
     * @param min minimum value allowed to generate (including)
     * @param max maximum value allowed to generate (including)
     */
    constructor(min: Int, max: Int) {
        minIntValue = min
        maxIntValue = max
    }

    /**
     * Sets the max value to generate random numbers
     * @param max maximum value allowed to generate (including)
     */
    constructor(max: Int) {
        maxIntValue = max
    }

    /**
     * Rolled a fair dice, guaranteed to be random
     * Generate a random number between min and max
     * @param min minimum value of random number to be generated
     * @param max maximum value of random number to be generated
     * @return the random number generated
     */
    @JvmOverloads
    fun generateRandomNumber(min: Int = minIntValue, max: Int = maxIntValue): Int {
        return random.nextInt(max - min + 1) + min
    }

    /**
     * Generate quantity number of lottery tickets (random numbers) between default min and
     * default max
     * @param quantity number of random numbers to be generated
     * @return set of random integers between min and max
     */
    fun generateLotteryTickets(quantity: Int): Set<Int> {
        val tickets = TreeSet<Int>()
        while (tickets.size < quantity) {
            tickets.add(generateRandomNumber())
        }
        return tickets
    }

    /**
     * Generate quantity number of lottery tickets (random numbers) between min and max
     * @param quantity number of random numbers to be generated
     * @param min minimum value of random number to be generated
     * @param max maximum value of random number to be generated
     * @return set of random integers between min and max
     */
    fun generateLotteryTickets(quantity: Int, min: Int, max: Int): Set<Int> {
        minIntValue = min
        maxIntValue = max
        return generateLotteryTickets(quantity)
    }

    /**
     * Generate quantity number of lottery tickets (random numbers) between min and max
     * @param length the length of the string to be generated
     * @param aSaltString the salt string containing the characters available to generate the string
     * @return set of random integers between min and max
     */
    fun generateRandomString(length: Int, aSaltString: String): String {
        saltchars = aSaltString
        return generateRandomString(length)
    }

    /**
     * Generate quantity number of lottery tickets (random numbers) between min and max
     * @param length the length of the string to be generated
     * @param aSaltString the salt string containing the characters available to generate the string
     * to be concatenated to the constant string
     * @return set of random integers between min and max
     */
    fun generateRandomStringAppendingSaltString(length: Int, aSaltString: String): String {
        saltchars += aSaltString
        return generateRandomString(length)
    }

    /**
     * Generate a random string with the characters defined by the salt string
     * @param length the length of the string to be generated
     * @return random generated string
     */
    fun generateRandomString(length: Int): String {
        if (length < 1) {
            throw IllegalArgumentException("length < 1: " + length)
        }
        val salt = StringBuilder()
        while (salt.length < length) {
            val index = (random.nextFloat() * saltchars.length).toInt()
            salt.append(saltchars[index])
        }
        return salt.toString()
    }

    /**
     * Generate quantity number of lottery tickets (random numbers) between default min and max
     * @param quantity number of random numbers to be generated
     * @param max maximum value of random number to be generated
     * @return set of random integers between min and max
     */
    fun generateLotteryTickets(quantity: Int, max: Int): Set<*> {
        maxIntValue = max
        return generateLotteryTickets(quantity)
    }

    fun printLotteryTickets(tickets: Set<Int>) {
        for (currentTicket in tickets) {
            println("Ticket: " + currentTicket.toString())
        }
    }

    companion object {
        var smallestAllowedValue = 1
        var biggestAllowedValueInUk = 59
        var biggestAllowedValueInBrazil = 60

        @JvmStatic
        fun main(args: Array<String>) {
            val generator = LuckyRandomGenerator()
            val randomNumber = generator.generateRandomNumber()
            val randomNumberString = randomNumber.toString()
            println("Random Number: " + randomNumberString)
            generator.printLotteryTickets(generator.generateLotteryTickets(6))
            println("Random String: " + generator.generateRandomString(10))
        }
    }
}
