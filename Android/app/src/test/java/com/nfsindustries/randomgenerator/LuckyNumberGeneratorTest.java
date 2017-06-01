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
    public void generation_isNotNull() throws Exception {
        LuckyRandomGenerator generator = new LuckyRandomGenerator();
        assertNotNull(generator.generateRandomNumber());
    }

    @Test
    public void generation_isInRangeDefault() throws Exception {
        LuckyRandomGenerator generator = new LuckyRandomGenerator();
        assertNotEquals(generator.generateRandomNumber(), 61);
        assertNotEquals(generator.generateRandomNumber(), 0);
    }

    @Test
    public void generation_isInRangeUK() throws Exception {
        LuckyRandomGenerator generator = new LuckyRandomGenerator("uk");
        assertNotEquals(generator.generateRandomNumber(), 60);
        assertNotEquals(generator.generateRandomNumber(), 0);
    }

    @Test
    public void generation_String() throws Exception {
        LuckyRandomGenerator generator = new LuckyRandomGenerator("uk");
        String generatedString = generator.generateRandomString(10);
        assertNotEquals(generatedString, "");
        assertNotNull(generatedString);
        assertEquals(generatedString.length(), 10);
    }
}