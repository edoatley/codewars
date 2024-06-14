package org.codewars.two2one;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TwoToOne {

    public static String longest(String s1, String s2) {
        String longs1 = longestStringFor(s1);
        System.err.println("Long S1: " + longs1);
        String longs2 = longestStringFor(s2);
        System.err.println("Long S2: " + longs2);
        return (longs1.length() > longs2.length()) ? longs1 : longs2;
    }

    private static String longestStringFor(String s) {
        return s.chars()
                .map(c -> (char) c)
                .distinct()
                .sorted()
                .collect(StringBuilder::new,
                        StringBuilder::appendCodePoint,
                        StringBuilder::append)
                .toString();
    }
}