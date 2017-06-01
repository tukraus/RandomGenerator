package com.nfsindustries.randomgenerator;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class LuckyNumberGeneratorTest {


    @Test
    public void numberGenerationIsNotNull() throws Exception {
        LuckyRandomGenerator generator = new LuckyRandomGenerator();
        assertNotNull(generator.generateRandomNumber());
    }

    @Test
    public void numberGenerationIsInRangeDefault() throws Exception {
        LuckyRandomGenerator generator = new LuckyRandomGenerator();
        assertNotEquals(generator.generateRandomNumber(), 61);
        assertNotEquals(generator.generateRandomNumber(), 0);
    }

    @Test
    public void numberGenerationIsInRangeNotUk() throws Exception {
        LuckyRandomGenerator generator = new LuckyRandomGenerator("br");
        assertNotEquals(generator.generateRandomNumber(), 61);
        assertNotEquals(generator.generateRandomNumber(), 0);
    }

    @Test
    public void numberGenerationIsInRangeUK() throws Exception {
        LuckyRandomGenerator generator = new LuckyRandomGenerator("uk");
        assertNotEquals(generator.generateRandomNumber(), 60);
        assertNotEquals(generator.generateRandomNumber(), 0);
    }

    @Test
    public void numberGenerationIsInRangeCustomInput() throws Exception {
        LuckyRandomGenerator generator = new LuckyRandomGenerator("uk");
        assertNotEquals(generator.generateRandomNumber(0, 99), 0);
        assertNotEquals(generator.generateRandomNumber(0, 99), 99);
    }

    @Test
    public void numberGenerationIsInRangeCustomInit() throws Exception {
        LuckyRandomGenerator generator = new LuckyRandomGenerator(0, 1001);
        assertNotEquals(generator.generateRandomNumber(), 0);
        assertNotEquals(generator.generateRandomNumber(), 1002);
    }

    @Test
    public void numberGenerationIsInRangeCustomMaxInit() throws Exception {
        LuckyRandomGenerator generator = new LuckyRandomGenerator(999);
        assertNotEquals(generator.generateRandomNumber(), 0);
        assertNotEquals(generator.generateRandomNumber(), 1000);
    }

    @Test
    public void stringGeneration() throws Exception {
        LuckyRandomGenerator generator = new LuckyRandomGenerator("uk");
        String generatedString = generator.generateRandomString(10);
        assertNotEquals(generatedString, "");
        assertNotNull(generatedString);
        assertEquals(generatedString.length(), 10);
    }

    @Test
    public void stringGenerationWithCustomSalt() throws Exception {
        LuckyRandomGenerator generator = new LuckyRandomGenerator();
        String generatedString = generator.generateRandomString(10, "abc123");
        assertNotEquals(generatedString, "");
        assertNotNull(generatedString);
        assertEquals(generatedString.length(), 10);
        assertFalse(generatedString.contains("d"));
        assertFalse(generatedString.contains("4"));
    }

    @Test
    public void stringGenerationWithAppendingSalt() throws Exception {
        LuckyRandomGenerator generator = new LuckyRandomGenerator();
        String generatedString = generator.generateRandomStringAppendingSaltString(100, "llllç");
        assertNotEquals(generatedString, "");
        assertNotNull(generatedString);
        assertEquals(generatedString.length(), 100);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotGenerateNegativeLengthString() {
        LuckyRandomGenerator generator = new LuckyRandomGenerator();
        generator.generateRandomString(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotGenerateZeroLengthString() {
        LuckyRandomGenerator generator = new LuckyRandomGenerator();
        generator.generateRandomString(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotGenerateNegativeLengthStringUk() {
        LuckyRandomGenerator generator = new LuckyRandomGenerator("uk");
        generator.generateRandomString(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotGenerateZeroLengthStringUk() {
        LuckyRandomGenerator generator = new LuckyRandomGenerator("uk");
        generator.generateRandomString(0);
    }
}