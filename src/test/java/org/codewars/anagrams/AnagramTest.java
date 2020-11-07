package org.codewars.anagrams;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;

public class AnagramTest {

    @CsvSource({
            "A,1",
            "CABD,13",
            "ABAB,2",
            "AAAB,1",
            "BAAA,4",
            "BAAB,4",
            "BCAD,9",
            "QUESTION,24572",
            "BOOKKEEPER,10743"
    })
    @ParameterizedTest(name = "Test {index}) expected {0} to give position {1}")
    public void testKnownInputs(String input, int expected) {
        assertThat(new Anagrams().listPosition(input))
                .describedAs("Position for input incorrect", input)
                .isEqualTo(BigInteger.valueOf(expected));
    }
}
