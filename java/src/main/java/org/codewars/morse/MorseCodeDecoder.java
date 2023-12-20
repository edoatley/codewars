package org.codewars.morse;

import java.rmi.ServerError;
import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;

public class MorseCodeDecoder {

    private static final String ZERO = "0";
    private static final String ONE = "1";
    public static final String MORSE_CHAR_SEPARATOR = " ";
    public static final String MORSE_WORD_SEPARATOR = "  ";

    public static String decodeMorse(String morseCode) {
        String result = splitToWords(morseCode).stream()
                .map(MorseCode::get)
                .map(c -> (c == null) ? " " : c)
                .collect(joining(""));
        System.err.println(String.format(" input ==> %s\noutput ==> %s", morseCode, result));
        return result;
    }

    public static String decodeBits(String bits) {
        String cleaned =  stripExtraneousZeroes(bits);
        int timeUnit = determineTimeUnit(cleaned);
        String result = bitsToMorseCode(cleaned, timeUnit);
        System.err.println(String.format(" input ==> %s\noutput ==> %s", bits, result));
        return result;
    }

    // at this point we know the value of a time unit so we have a string of 1TU dots/ 3 TU dashes delimited by a 1 TU pause
    // groups of dots/dashes forming a character are delimited by 3TU pause -> write char to result
    // groups of dots/dashes forming a word are delimited by 7TU pause -> write char to result

    // OPTION 1
    // * Convert 7*TU group of zero to ' ' and split on this to give an array of words
    // * for each word split on 3*TU groups of zeroes to give morse char array
    // * convert each 3*TU of 1's to - and then convert remaining 1*TU of 1s to . then remove 0s
    private static String bitsToMorseCode(String cleaned, int timeUnit) {
        StringBuilder result = new StringBuilder();
        String[] words = cleaned.split(ZERO.repeat(7 * timeUnit));
        for(String word : words) {
            String[] chars = word.split(ZERO.repeat(3 * timeUnit));
            for (String c : chars) {
                String[] dotsAndDashes = c.split(ZERO.repeat(timeUnit));
                for (String d : dotsAndDashes) {
                    result.append(decode(d, timeUnit));
                }
                result.append(MORSE_CHAR_SEPARATOR);
            }
            result.append(MORSE_WORD_SEPARATOR);
        }
        if (result.lastIndexOf(" ") == result.length() - 1) {
            result.deleteCharAt(result.length() - 1);
        }
        return result.toString();
    }

    private static String decode(String dotsOrDash, int timeUnit) {
        if(dotsOrDash.length() == (3 * timeUnit)) {
            return "-";
        }
        return ".";

    }

    protected static List<String> splitToWords(String morseCode) {
        return Arrays.asList(morseCode.trim().replace("  ", " ").split(" "));
    }

    // expecting a 1 (between dots/dashes), 3 length (between letters) and 7 length (between words)
    protected static int determineTimeUnit(String cleaned) {
        if(!cleaned.contains(ZERO)) {
            return cleaned.length();
        }
        Map<Integer, Integer> countsOfZeroesInARow = new HashMap<>();
        Set<String> changeOfCharacter = splitStringOnChangeOfCharacter(cleaned);
        String smallest1s = changeOfCharacter.stream().filter(e -> e.contains("1")).min(String::compareTo).orElse("");
        String smallest0s = changeOfCharacter.stream().filter(e -> e.contains("0")).min(String::compareTo).orElse("");;
        return Stream.of(smallest0s, smallest1s).map(String::length).min(Integer::compareTo).orElse(1);
    }

     // split to array on change of character
    protected static Set<String> splitStringOnChangeOfCharacter(String input){
        Set<String> result = new HashSet<>();
        StringBuilder builder = new StringBuilder();

        if(input.length() <= 1) {
            result.add(input);
        }
        else {
            char prevChar = input.charAt(0);
            char thisChar = ' ';
            builder.append(input.charAt(0));
            for (int i = 1; i < input.length(); i++) {
                thisChar = input.charAt(i);
                if(thisChar != prevChar) {
                    result.add(builder.toString());
                    builder.delete(0, builder.length());
                }
                builder.append(thisChar);
                prevChar = thisChar;
            }
            if(builder.length() > 0) {
                result.add(builder.toString());
            }
        }
        return result;
    }

    protected static String stripExtraneousZeroes(String bits) {
        StringBuilder sb = new StringBuilder(bits);
        for (int i = sb.length() - 1; i >= 0; i--) {
            if(sb.charAt(i) == '0') {
                sb.deleteCharAt(i);
            }
            else {
                break;
            }
        }
        int firstOne = bits.indexOf("1");
        return sb.substring(firstOne);
    }
}
