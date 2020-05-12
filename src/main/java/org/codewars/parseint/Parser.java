package org.codewars.parseint;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Parser {

    static Map<String, Integer> wordToNumber = new HashMap<String, Integer>() {{
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
        put("eleven", 11);
        put("twelve", 12);
        put("thirteen", 13);
        put("fourteen", 14);
        put("fifteen", 15);
        put("sixteen", 16);
        put("seventeen", 17);
        put("eighteen", 18);
        put("nineteen", 19);
        put("twenty", 20);
        put("thirty", 30);
        put("fourty", 40);
        put("fifty", 50);
        put("sixty", 60);
        put("seventy", 70);
        put("eighty", 80);
        put("ninety", 90);
        put("hundred", 100);
    }};

    static String HUNDRED = "hundred";
    static String THOUSAND = "thousand";
    static String ADDITIVE_DIVIDER = "-";
    static String IGNORABLE_TOKEN = "and";

    public static int parseInt(final String input) {
        // remove ands
        String converted = input.replace(IGNORABLE_TOKEN, "");
        List<String> tokens = Arrays.asList(input.split(" "));

        for (String t: tokens) {
            if(wordToNumber.containsKey(t)) {
                converted = converted.replace(t, String.valueOf(wordToNumber.get(t)));
            }
            else if(t.contains(ADDITIVE_DIVIDER)) {
                String[] splitValue = t.split(ADDITIVE_DIVIDER);
                converted = converted.replace(t, String.valueOf(wordToNumber.get(splitValue[0]) + wordToNumber.get(splitValue[1])));
            }
        }
        System.err.println(converted);
        int total = 0;
        return Integer.parseInt(converted);
    }
}