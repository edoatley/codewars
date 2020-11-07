package org.codewars.anagrams;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.LongStream;

import static java.math.BigInteger.ONE;
import static java.math.BigInteger.ZERO;
import static java.util.stream.Collectors.toList;

//Consider a "word" as any sequence of capital letters A-Z (not limited to just "dictionary words").
// For any word with at least two different letters, there are other words composed of the same letters
// but in a different order (for instance, STATIONARILY/ANTIROYALIST, which happen to both be dictionary
// words; for our purposes "AAIILNORSTTY" is also a "word" composed of the same letters as these two).
//
//We can then assign a number to every word, based on where it falls in an alphabetically sorted list of
// all words made up of the same group of letters. One way to do this would be to generate the entire list
// of words and find the desired one, but this would be slow if the word is long.
//
//Given a word, return its number. Your function should be able to accept any word 25 letters or less in
// length (possibly with some letters repeated), and take no more than 500 milliseconds to run. To compare,
// when the solution code runs the 27 test cases in JS, it takes 101ms.
//
//For very large words, you'll run into number precision issues in JS (if the word's position is greater
// than 2^53). For the JS tests with large positions, there's some leeway (.000000001%). If you feel like
// you're getting it right for the smaller ranks, and only failing by rounding on the larger, submit a
// couple more times and see if it takes.
//
//Python, Java and Haskell have arbitrary integer precision, so you must be precise in those languages
// (unless someone corrects me).
//
//C# is using a long, which may not have the best precision, but the tests are locked so we can't change it.
//
//Sample words, with their rank:
//ABAB = 2
//AAAB = 1
//BAAA = 4
//QUESTION = 24572
//BOOKKEEPER = 10743
public class Anagrams {

    public BigInteger listPosition(String word) {
        int n = word.length();
        List<Character> charactersRemaining = word.chars().mapToObj(c -> (char)c).collect(toList());
        StringBuilder sb = new StringBuilder();
        BigInteger betterPositionsSkippedSum = ZERO;
        for (int i = 0; i < n; i++) {
            Character c = word.charAt(i);
            charactersRemaining.remove(c);
            sb.append(c);
//            System.err.printf("c = %s, sb = %s, charsRemain = %s%n", c, sb, charactersRemaining);

            List<Character> betterCharacters = charactersRemaining.stream().filter(x -> x < c).distinct().collect(toList());
            for (Character betterCharacter : betterCharacters){
                List<Character> missedComboChars = swapChars(c, betterCharacter, charactersRemaining);
                BigInteger skippedCombinations = combinations(missedComboChars);
                betterPositionsSkippedSum = betterPositionsSkippedSum.add(skippedCombinations);
            }
//            System.err.printf("betterPositionsSkippedSum = %s%n", betterPositionsSkippedSum);
        }

        return betterPositionsSkippedSum.add(ONE);
    }

    private List<Character> swapChars(Character c, Character betterCharacter, List<Character> charactersRemaining) {
        List<Character> result = new ArrayList<Character>();
        boolean alreadyExcluded = false;
        result.add(c);
        for(Character rem : charactersRemaining)  {
            if (!rem.equals(betterCharacter) || alreadyExcluded) {
                result.add(rem);
            }
            else {
                alreadyExcluded = true;
            }
        }
//        System.err.printf("swapChars --> c = %s, betterCharacter = %s, result %s%n", c, betterCharacter, result);
        return result;
    }

    private BigInteger combinations(List<Character> characters) {
        int n = characters.size();
        Map<Character, Integer> multiOccurrences = new ConcurrentHashMap<>();

        characters.forEach(c -> {
            multiOccurrences.compute(c, (key, val) -> (val == null) ? 1 : val + 1);
        });
        multiOccurrences.forEach((key, val) -> {
            if(val == 1) {
                multiOccurrences.remove(key, val);
            }
        });
        BigInteger result = factorial(n);
//        System.err.printf("totalCombos = %s ", result.intValue());
        for (Map.Entry<Character, Integer> duplicate : multiOccurrences.entrySet()) {
            result = result.divide(factorial(duplicate.getValue()));
//            System.err.printf("combos after adjusting for %s x%s = %s ", duplicate.getKey(), duplicate.getValue(), result.intValue());
        }
//        System.err.printf("combinations = %s%n", result.intValue());
        return result;
    }

    private BigInteger factorial(long n) {
        return LongStream.range(1, n+1)
                .mapToObj(BigInteger::valueOf)
                .reduce(BigInteger::multiply)
                .orElse(ONE);
    }
}
