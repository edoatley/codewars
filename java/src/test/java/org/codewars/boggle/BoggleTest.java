package org.codewars.boggle;


import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class BoggleTest {

    final private static char[][] board = {
            {'E', 'A', 'R', 'A'},
            {'N', 'L', 'E', 'C'},
            {'I', 'A', 'I', 'S'},
            {'B', 'Y', 'O', 'R'}
    };
    final private static char[][] board2 = {
            {'T', 'T', 'M', 'D', 'A'}, 
            {'G', 'Y', 'I', 'N', 'N'}, 
            {'P', 'A', 'L', 'C', 'E'}, 
            {'I', 'A', 'U', 'L', 'G'}, 
            {'A', 'M', 'I', 'N', 'A'}
    };

    private static String[] toCheck = {"C", "EAR", "EARS", "BAILER", "RSCAREIOYBAILNEA", "CEREAL", "ROBES"};
    private static boolean[] expecteds = {true, true, false, true, true, false, false};
    private static String[] toCheck2 = {"T", "TT", "YTG", "ANIMA", "ANIMAL", "MINGLE", "GCLLAUP", "ANIMALITY", "ANIMALAMINA", "ECLINCL", "PLACE", "PALACE", "MGYIGA", "Z"};
    private static boolean[] expecteds2 = {true, true, true, true, true, true, false, true, false, false, false, false, false, false};

    @Test
    public void sampleTests() {
        for (int i = 0; i < toCheck.length; i++) {
            assertEquals(expecteds[i], new Boggle(deepCopy(board), toCheck[i]).check());
        }
    }


    @Test
    public void sampleTests2() {
        for (int i = 0; i < toCheck2.length; i++) {
            assertEquals(expecteds2[i], new Boggle(deepCopy(board2), toCheck2[i]).check());
        }
    }

    private char[][] deepCopy(char[][] arr) {
        return Arrays.stream(arr)
                .map(a -> Arrays.copyOf(a, a.length))
                .toArray(char[][]::new);
    }
}