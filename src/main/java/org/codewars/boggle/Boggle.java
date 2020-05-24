package org.codewars.boggle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Boggle {
    private static char[][] BOARD;

    private final char[][] board;
    private final String word;

    public Boggle(final char[][] board, final String word) {
        this.board = board;
        this.word = word;
        if(!Arrays.deepEquals(this.board, BOARD)) {
            BOARD = this.board;
            System.out.println(Arrays.deepToString(this.board));
        }
    }

    public boolean check() {

        System.out.println(word);
        List<Coordinate> potentialStartingpositions = globalCharacterMatches(word.charAt(0));
        String rem = word.substring(1);
        return checkRemainder(potentialStartingpositions, new ArrayList<>(), rem);
    }

    public boolean checkRemainder(List<Coordinate> prevCharacterLocations, List<Coordinate> usedPositions, String word) {
        if(prevCharacterLocations.size() == 0) {
            return false;
        }
        else if(word.length() == 0) {
            return true;
        }
        else {
            for(Coordinate coords : prevCharacterLocations) {
                List<Coordinate> thisCharacterLocations = localCharacterMatches(word.charAt(0), coords, usedPositions);
                if (!thisCharacterLocations.isEmpty() && !usedPositions.contains(coords)){
                    List<Coordinate> used = new ArrayList<>(usedPositions);
                    used.add(coords);
                    if(checkRemainder(thisCharacterLocations, used, word.substring(1))) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    private List<Coordinate> localCharacterMatches(char searchCharacter, Coordinate startingPosition, List<Coordinate> excluded) {
        int x = startingPosition.x;
        int y = startingPosition.y;
        List<Coordinate> matches = new ArrayList<>();
        for (int i = (x -1) ; i <= (x + 1); i++) {
            for (int j = (y -1) ; j <= (y + 1); j++) {
                if(coordsValid(i,j) && (i != x || j != y)){
                    if(board[i][j] == searchCharacter) {
                        Coordinate match = new Coordinate(i, j);
                        if(!excluded.contains(match)) {
                            matches.add(match);
                        }
                    }
                }
            }
        }
        return matches;
    }

    private boolean coordsValid(int i, int j) {
        return i < board.length
                && j < board[0].length
                && i >= 0
                && j >= 0;
    }

    private List<Coordinate> globalCharacterMatches(char searchCharacter) {
        List<Coordinate> result = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            char[] line = board[i];
            for (int j = 0; j < line.length; j++) {
                if(line[j] == searchCharacter) {
                    result.add(new Coordinate(i, j));
                }
            }
        }
        return result;
    }

    private static final class Coordinate {
        int x;
        int y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Coordinate that = (Coordinate) o;
            return x == that.x &&
                    y == that.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
