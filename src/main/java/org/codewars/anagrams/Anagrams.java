package org.codewars.anagrams;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import static java.math.BigInteger.*;
import static java.util.stream.Collectors.*;

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
        BigInteger position = ZERO;
        for (int i = 0; i < n; i++) {
            Character c = word.charAt(i);
            charactersRemaining.remove(c);
            sb.append(c);
            System.err.printf("c = %s, sb = %s, rem = %s%n", c, sb, charactersRemaining);
            position = position.add(skippedCombinations(c, charactersRemaining));
            System.err.printf("position = %s%n", position);
        }
        if(position.compareTo(ZERO) > 0){
            position = position.add(ONE);
        }
        return position.max(ONE);
    }

    private BigInteger skippedCombinations(Character c, List<Character> charactersRemaining){
        BigInteger lowerOptionsRemaining = valueOf(charactersRemaining.stream().filter(x -> x < c).count());
        BigInteger combinations = combinations(charactersRemaining.stream().map(String::valueOf).collect(joining("")));
        System.err.printf("lowerOptions = %s, combinations = %s%n", lowerOptionsRemaining, combinations);
        if(!lowerOptionsRemaining.equals(ZERO)){
            return combinations.multiply(lowerOptionsRemaining);
        }
        return ZERO;
    }

    private BigInteger combinations(String word) {
        int n = word.length();
        Map<Character, Integer> multiOccurrences = new ConcurrentHashMap<>();

        word.chars().mapToObj(c -> (char) c).forEach(c -> {
            multiOccurrences.compute(c, (key, val) -> (val == null) ? 1 : val + 1);
        });
        multiOccurrences.forEach((key, val) -> {
            if(val == 1) {
                multiOccurrences.remove(key, val);
            }
        });
        BigInteger result = factorial(n);
        for (Map.Entry<Character, Integer> duplicate : multiOccurrences.entrySet()) {
            result = result.divide(factorial(duplicate.getValue()));
        }

        return result;
    }

    private BigInteger factorial(long n) {
        return LongStream.range(1, n+1)
                .mapToObj(BigInteger::valueOf)
                .reduce(BigInteger::multiply)
                .orElse(ONE);
    }
    /*~
       1   ABCD        1234
       2   ABDC        1243
       3   ACBD        1324
       4   ACDB        1342
       5   ADBC        1423
       6   ADCB        1432
       7   BACD        2134
       8   BADC        2143
       9   BCAD        2314
      10   BCDA        2341
      11   BDAC        2413
      12   BDCA        2431
      13   CABD        3124
      14   CBAD        3214
      15   CBDA        3241
      16   CDAB        3412
      17   CDBA        3421
      18   DABC        4123
      19   DBAC        4213
      20   DBCA        4231
      21   DCBA        4321
     */
            // count of letters before multiplied by count of chars these
            // e.g. for CBAD
            // AB are before C giving 2 letters
            // A has 6 combinations from the different ways to shuffle BCD
            // B has 6 combinations from the different combinations of ACD
            // calc combos...
            // A can only be 1 combo
            // AB can be AB or BA i.e. 2 combos
            // ABC can be ABC, ACB, BAC, BCA, CBA, CAB i.e. 6 combos
            /*
                How many different ways can the letters P, Q, R, S be arranged?
                The answer is 4! = 24.
                This is because there are four spaces to be filled: _, _, _, _

                If there are duplicate letters these must be accounted for by divinding by the count factorial

                In how many ways can the letters in the word: STATISTICS be arranged?
                There are 3 S’s, 2 I’s and 3 T’s in this word, therefore, the number of ways of arranging the letters are:
                10!      = 50 400
                --------
                3! 2! 3!
             */
}
