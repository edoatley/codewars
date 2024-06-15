package org.codewars.two2one;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TwoToOne {

    public static String longest(String s1, String s2) {
        return (s1 + s2).codePoints()
                .distinct()
                .sorted()
                .collect(StringBuilder::new,
                        StringBuilder::appendCodePoint,
                        StringBuilder::append)
                .toString();
    }
}