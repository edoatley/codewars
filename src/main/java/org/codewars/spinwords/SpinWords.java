package org.codewars.spinwords;

import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;

public class SpinWords {

    public String spinWords(String sentence) {
        return Stream.of(sentence.split(" "))
                .map(StringBuilder::new)
                .map(w -> (w.length() <= 4) ? w : w.reverse())
                .map(StringBuilder::toString)
                .collect(joining(" "));
    }
}
