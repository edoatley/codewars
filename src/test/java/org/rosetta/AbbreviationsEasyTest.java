package org.rosetta;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AbbreviationsEasyTest {

    @Test
    void testEvaluate() {
        String userInput = "riG   rePEAT copies  put mo   rest    types   fup.    6       poweRin";
        String expected = "RIGHT REPEAT *error* PUT MOVE RESTORE *error* *error* *error* POWERINPUT";
        String actual = new AbbreviationsEasy().evaluate(userInput);
        assertThat(actual).isEqualTo(expected);
    }
    @Test
    void testEvaluateStarter() {
        String userInput = "riG   rePEAT";
        String expected = "RIGHT REPEAT";
        String actual = new AbbreviationsEasy().evaluate(userInput);
        assertThat(actual).isEqualTo(expected);
    }
}