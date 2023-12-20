package org.codewars.unknown.digit;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class RunesTest {

    @ParameterizedTest
    @CsvSource(value = {
            "1+1=?|2",
            "123*45?=5?088|6",
            "-5?*-1=5?|0",
            "19--45=5?|-1",
            "??*??=302?|5",
            "?*11=??|2",
            "??*1=??|2",
            "??+??=??|-1",
            "-?56373--9216=-?47157|8"
    }, delimiter = '|')
    void testRunes(String expression, int expected) {
        assertThat(Runes.solveExpression(expression)).isEqualTo(expected);
    }
}