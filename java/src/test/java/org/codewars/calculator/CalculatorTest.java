package org.codewars.calculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {
    @Test
    public void simpleLiteral() {
        assertEquals(127d, Calculator.evaluate("127"), 1e-4);
    }

    @Test
    public void subtractionAndAddition() {
        assertEquals(5d, Calculator.evaluate("2 + 3"), 1e-4);
        assertEquals((double) -5, Calculator.evaluate("2 - 3 - 4"), 1e-4);
    }

    @Test
    public void divisionAndMultiplication() {
        assertEquals(10d, Calculator.evaluate("10 * 5 / 5"), 1e-4);
    }

    @Test
    public void allMixed() {
        assertEquals(13d, Calculator.evaluate("2 / 2 + 3 * 4"), 1e-4);
    }

    @Test
    public void floats() {
        assertEquals((double) 0, Calculator.evaluate("7.7 - 3.3 - 4.4"), 1e-4);
    }

    @Test
    public void brackets() {
        assertEquals(1d, Calculator.evaluate("7 - ( 3 * 4 ) + 6"), 1e-4);
    }

    @Test
    public void katatest1() {
        assertEquals((double) (2 + 3 * 4 / 3 - 6 / 3 * 3 + 8), Calculator.evaluate("2 + 3 * 4 / 3 - 6 / 3 * 3 + 8"), 1e-4);
    }
    @Test
    public void katatest2() {
        assertEquals((double) (2 / 2 + 3 * 4 - 6), Calculator.evaluate("2 / 2 + 3 * 4 - 6"), 1e-4);
    }
    @Test
    public void katatest3() {
        assertEquals((double) (10 * 5 / 2), Calculator.evaluate("10 * 5 / 2"), 1e-4);
    }
    @Test
    public void katatest4() {
        assertEquals((double) (-1306260543L + 1047780666L), Calculator.evaluate("-1306260543 + 1047780666"), 1e-4);
    }
}