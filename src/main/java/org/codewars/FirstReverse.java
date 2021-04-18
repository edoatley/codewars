package org.codewars;

import java.util.Comparator;
import java.util.Scanner;
import java.util.stream.Collectors;

public class FirstReverse {

    public static String FirstReverse(String str) {
        return str.chars().mapToObj(c -> (char) c)
                .map(String::valueOf)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.joining("\n"));
    }

    public static void main (String[] args) {
        // keep this function call here
        Scanner s = new Scanner(System.in);
        System.out.print(FirstReverse(s.nextLine()));
    }

}
