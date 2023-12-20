package org.codewars.panagram;

import java.util.stream.Stream;

public class PanagramChecker {
    public boolean check(String sentence){
        return 26L == sentence.chars()
                .map(c -> (char) c)
                .filter(Character::isAlphabetic)
                .map(Character::toUpperCase)
                .distinct()
                .count();
    }
}
