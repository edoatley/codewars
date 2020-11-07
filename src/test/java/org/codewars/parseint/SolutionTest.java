package org.codewars.parseint;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class SolutionTest {
    
    @Test
    public void acceptanceTests1() {
        assertEquals(1 , Parser.parseInt("one"));
    }
    @Test
    public void acceptanceTests20() {
        assertEquals(20 , Parser.parseInt("twenty"));
    }
    @Test
    public void acceptanceTests246() {
        assertEquals(246 , Parser.parseInt("two hundred forty-six"));
    }
    @Test
    public void acceptanceTests783919() {
        assertEquals(783919, Parser.parseInt("seven hundred eighty-three thousand nine hundred and nineteen"));
    }

    @Test
    public void oneMillion() {
        assertEquals(1000000, Parser.parseInt("one million"));
    }

    @Test
    public void basicZeroToTen() {
        assertEquals(0, Parser.parseInt("zero"));
        assertEquals(1, Parser.parseInt("one"));
        assertEquals(2, Parser.parseInt("two"));
        assertEquals(3, Parser.parseInt("three"));
        assertEquals(4, Parser.parseInt("four"));
        assertEquals(5, Parser.parseInt("five"));
        assertEquals(6, Parser.parseInt("six"));
        assertEquals(7, Parser.parseInt("seven"));
        assertEquals(8, Parser.parseInt("eight"));
        assertEquals(9, Parser.parseInt("nine"));
        assertEquals(10, Parser.parseInt("ten"));
    }

    @Test
    public void teens() {
        assertEquals(11, Parser.parseInt("eleven"));
        assertEquals(12, Parser.parseInt("twelve"));
        assertEquals(13, Parser.parseInt("thirteen"));
        assertEquals(14, Parser.parseInt("fourteen"));
        assertEquals(15, Parser.parseInt("fifteen"));
        assertEquals(16, Parser.parseInt("sixteen"));
        assertEquals(17, Parser.parseInt("seventeen"));
        assertEquals(18, Parser.parseInt("eighteen"));
        assertEquals(19, Parser.parseInt("nineteen"));
    }

    @Test
    public void tens() {
        assertEquals(20, Parser.parseInt("twenty"));
        assertEquals(30, Parser.parseInt("thirty"));
        assertEquals(40, Parser.parseInt("forty"));
        assertEquals(50, Parser.parseInt("fifty"));
        assertEquals(60, Parser.parseInt("sixty"));
        assertEquals(70, Parser.parseInt("seventy"));
        assertEquals(80, Parser.parseInt("eighty"));
        assertEquals(90, Parser.parseInt("ninety"));
    }

    @Test
    public void hyphenatedBelowOneHundred() {
        assertEquals(21, Parser.parseInt("twenty-one"));
        assertEquals(32, Parser.parseInt("thirty-two"));
        assertEquals(43, Parser.parseInt("forty-three"));
        assertEquals(54, Parser.parseInt("fifty-four"));
        assertEquals(65, Parser.parseInt("sixty-five"));
        assertEquals(76, Parser.parseInt("seventy-six"));
        assertEquals(87, Parser.parseInt("eighty-seven"));
        assertEquals(98, Parser.parseInt("ninety-eight"));
    }
}