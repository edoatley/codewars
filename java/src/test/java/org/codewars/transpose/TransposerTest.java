package org.codewars.transpose;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TransposerTest {

    @Test
    void basicTranspose() {
        List<List<String>> input = List.of(
                List.of("Bob", "Apple", "Avocado", "Angel", "Arrow"),
                List.of("Dwayne", "Grape", "Go-kart", "", ""),
                List.of("Sebastian", "Sausage", "Kebab", "Burger", "")
        );

        List<List<String>> expected = List.of(
                List.of("Bob", "Dwayne", "Sebastian"),
                List.of("Apple", "Grape", "Sausage"),
                List.of("Avocado", "Go-kart", "Kebab"),
                List.of("Angel", "", "Burger"),
                List.of("Arrow", "", "")
        );

        List<List<String>> actual = new Transposer().transpose(input);

        assertThat(actual).isEqualTo(expected);
    }
}
