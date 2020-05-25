package org.codewars.calculator;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class Calculator {
    private static final List<String> operands = Arrays.asList("/*","+-");

    public static Double evaluate(String expression) {
        System.err.println(expression);
        String amendedExpression = expression;

        while(amendedExpression.contains("(")) {
            int start = amendedExpression.lastIndexOf('(');
            int end = amendedExpression.indexOf(')', start);
            double bracketsAnswer = evaluate(amendedExpression.substring(start + 2, end - 1)); // need to exclude the spaces
            amendedExpression = amendedExpression.replace(amendedExpression.substring(start, end + 1), String.valueOf(bracketsAnswer));
        }
        System.err.println(" ->" + amendedExpression);
        String[] elements = amendedExpression.split(" ");
        for(String ops : operands) {
            for (int i = 0; i < elements.length; i++) {
                if(elements[i] != null) {
                    if(elements[i].length() == 1 && ops.contains(elements[i].substring(0, 1))) {
                        int prevIdx = prevIdx(i, elements);
                        int nextIdx = nextIdx(i, elements);
                        elements[i] = eval(Double.valueOf(elements[prevIdx]), Double.valueOf(elements[nextIdx]), elements[i].charAt(0));
                        elements[prevIdx] = null;
                        elements[nextIdx] = null;
                    }
                }
            }
        }
        return Stream.of(elements).filter(Objects::nonNull).mapToDouble(Double::parseDouble).findFirst().orElse(0d);
    }

    private static int prevIdx(int start, String[] array) {
        for (int i = start - 1; i >= 0 ; i--) {
            if(array[i] != null) {
                return i;
            }
        }
        throw new RuntimeException(String.format("Unable to find prev index from %s in %s", start, Arrays.deepToString(array)));
    }
    private static int nextIdx(int start, String[] array) {
        for (int i = start + 1; i < array.length; i++) {
            if(array[i] != null) {
                return i;
            }
        }

        throw new RuntimeException(String.format("Unable to find next index from %s in %s", start, Arrays.deepToString(array)));
    }

    private static String eval(Double a, Double b, char op) {
        switch (op) {
            case '/':
                return Double.toString(a / b);
            case '*':
                return Double.toString(a * b);
            case '+':
                return Double.toString(a + b);
            case '-':
                return Double.toString(a - b);
            default:
                throw new RuntimeException("Invalid operand " + op);
        }
    }
}