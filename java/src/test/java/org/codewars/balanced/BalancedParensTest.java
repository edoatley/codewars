package org.codewars.balanced;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BalancedParensTest {

    @Test
    public void testZero() {
        List<String> actual = BalancedParens.balancedParens(0);
        Collections.sort(actual);
        assertEquals(new ArrayList<>(Collections.singletonList("")), actual);
    }

    @Test
    public void testOne() {
        List<String> actual = BalancedParens.balancedParens(1);
        Collections.sort(actual);
        assertEquals(new ArrayList<>(Arrays.asList("()")), actual);
    }

    @Test
    public void testTwo() {
        List<String> actual = BalancedParens.balancedParens(2);
        Collections.sort(actual);
        assertEquals(new ArrayList<>(Arrays.asList("(())", "()()")), actual);
    }

    @Test
    public void testThree() {
        List<String> actual = BalancedParens.balancedParens(3);
        Collections.sort(actual);
        assertEquals(new ArrayList<>(Arrays.asList("((()))", "(()())", "(())()", "()(())", "()()()")), actual);
    }

    @Test
    public void testFour() {
        List<String> actual = BalancedParens.balancedParens(4);
        Collections.sort(actual);
        assertEquals(new ArrayList<>(Arrays.asList("(((())))", "((()()))", "((())())", "((()))()", "(()(()))", "(()()())", "(()())()", "(())(())", "(())()()", "()((()))", "()(()())", "()(())()", "()()(())", "()()()()")), actual);
    }

}