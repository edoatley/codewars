package org.codewars.anagrams;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.LongStream;

import static java.math.BigInteger.ONE;
import static java.math.BigInteger.ZERO;
import static java.util.stream.Collectors.*;

public class Anagrams {

    /**
     * This function looks at a word and finds the alphabetical position in which it lies
     *
     * for example BOOK is position 3 as the following are before it alphabetically (BKOO, BOKO)
     * @param word
     * @return
     */
    public BigInteger listPosition(String word) {
        int n = word.length();
        List<Character> charactersRemaining = word.chars().mapToObj(c -> (char) c).collect(toList());
        BigInteger betterPositionsSkippedSum = ZERO;
        for (int i = 0; i < n; i++) {
            Character c = word.charAt(i);
            charactersRemaining.remove(c);

            List<Character> betterCharacters = charactersRemaining.stream().filter(x -> x < c).distinct().collect(toList());
            for (Character betterCharacter : betterCharacters) {
                List<Character> missedComboChars = swapChars(c, betterCharacter, charactersRemaining);
                BigInteger skippedCombinations = combinations(missedComboChars);
                betterPositionsSkippedSum = betterPositionsSkippedSum.add(skippedCombinations);
            }
        }

        return betterPositionsSkippedSum.add(ONE);
    }

    private List<Character> swapChars(Character c, Character betterCharacter, List<Character> charactersRemaining) {
        List<Character> result = new ArrayList<>(charactersRemaining);
        result.remove(betterCharacter);
        result.add(c);
        return result;
    }

    /**
     * for a word of length n the combinations are n!
     * you need to adjust for any duplicate letters if you wish to count unique
     * combinations by dividing by the product of all the duplicated entries factorial
     * e.g. for WOOD --> 4! / 2! (2! is as there are two O characters
     * for BOOKKEEPER -> 10! / 3!.2!.2!
     *
     * @param characters
     * @return
     */
    private BigInteger combinations(List<Character> characters) {

        final List<Long> multipleCharacters = characters.stream()
                .collect(groupingBy(c -> c, counting()))
                .values().stream()
                .filter(charCount -> charCount > 1)
                .collect(toList());

        BigInteger result = factorial(characters.size());
        for (Long count : multipleCharacters) {
            result = result.divide(factorial(count.intValue()));
        }
        return result;
    }

    private BigInteger factorial(long n) {
        return LongStream.range(1, n + 1)
                .mapToObj(BigInteger::valueOf)
                .reduce(BigInteger::multiply)
                .orElse(ONE);
    }
}
