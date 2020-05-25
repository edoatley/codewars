package org.codewars.calculator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalculatorTest {
    @Test
    public void simpleLiteral() {
        assertEquals("simple literal", new Double(127), Calculator.evaluate("127"), 1e-4);
    }

    @Test
    public void subtractionAndAddition() {
        assertEquals("addition", new Double(5), Calculator.evaluate("2 + 3"), 1e-4);
        assertEquals("subtraction", new Double(-5), Calculator.evaluate("2 - 3 - 4"), 1e-4);
    }

    @Test
    public void divisionAndMultiplication() {
        assertEquals("mixed division and multiplication", new Double(10), Calculator.evaluate("10 * 5 / 5"), 1e-4);
    }

    @Test
    public void allMixed() {
        assertEquals("mixed", new Double(13), Calculator.evaluate("2 / 2 + 3 * 4"), 1e-4);
    }

    @Test
    public void floats() {
        assertEquals("floats 1", new Double(0), Calculator.evaluate("7.7 - 3.3 - 4.4"), 1e-4);
    }

    @Test
    public void brackets() {
        assertEquals("brackets 1", new Double(1), Calculator.evaluate("7 - ( 3 * 4 ) + 6"), 1e-4);
    }

    @Test
    public void katatest1() {
        assertEquals("katatest 1", new Double(2 + 3 * 4 / 3 - 6 / 3 * 3 + 8), Calculator.evaluate("2 + 3 * 4 / 3 - 6 / 3 * 3 + 8"), 1e-4);
    }
    @Test
    public void katatest2() {
        assertEquals("katatest 2", new Double(2 / 2 + 3 * 4 - 6), Calculator.evaluate("2 / 2 + 3 * 4 - 6"), 1e-4);
    }
    @Test
    public void katatest3() {
        assertEquals("katatest 3", new Double(10 * 5 / 2), Calculator.evaluate("10 * 5 / 2"), 1e-4);
    }
    @Test
    public void katatest4() {
        assertEquals("katatest 4", new Double(-1306260543l + 1047780666l), Calculator.evaluate("-1306260543 + 1047780666"), 1e-4);
    }
}