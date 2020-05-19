package org.codewars.parseint;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Parser {

    static Map<String, Integer> wordToNumber = new HashMap<String, Integer>() {{
        put("eleven", 11);
        put("twelve", 12);
        put("thirteen", 13);
        put("fourteen", 14);
        put("fifteen", 15);
        put("sixteen", 16);
        put("seventeen", 17);
        put("eighteen", 18);
        put("nineteen", 19);
        put("zero", 0);
        put("one", 1);
        put("two", 2);
        put("three", 3);
        put("four", 4);
        put("five", 5);
        put("six", 6);
        put("seven", 7);
        put("eight", 8);
        put("nine", 9);
        put("ten", 10);
        put("twenty", 20);
        put("thirty", 30);
        put("forty", 40);
        put("fifty", 50);
        put("sixty", 60);
        put("seventy", 70);
        put("eighty", 80);
        put("ninety", 90);
        put("hundred", 100);
        put("thousand", 1000);
        put("million", 1000000);
    }};

    static String ADDITIVE_DIVIDER = "-";
    static String IGNORABLE_TOKEN = "and";

    public static int parseInt(final String input) {
        List<Integer> numbersToAdd = Stream.of(input.split(" "))
                .filter(t-> !t.equals(IGNORABLE_TOKEN))
                .map(Parser::convertTokenToInteger)
                .collect(Collectors.toList());

        int total = 0;
        int accumulator = 0;
        for (int thisNum : numbersToAdd) {
            if (thisNum == 100) {
                accumulator *= thisNum;
            }
            else if(thisNum == 1000 || thisNum == 1000000) {
                accumulator *= thisNum;
                total += accumulator;
                accumulator = 0;
            }
            else {
                accumulator += thisNum;
            }
        }
        total += accumulator;
        return total;
    }

    private static Integer convertTokenToInteger(String t) {
        if(wordToNumber.containsKey(t)) {
            return wordToNumber.get(t);
        }
        else if(t.contains(ADDITIVE_DIVIDER)) {
            String[] splitValue = t.split(ADDITIVE_DIVIDER);
            return wordToNumber.get(splitValue[0]) + wordToNumber.get(splitValue[1]);
        }
        return 0;
    }
}