package org.rosetta;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AbbreviationsSimpleTest {

    @Test
    void evaluate() {
        String userInput = "riG   rePEAT copies  put mo   rest    types   fup.    6       poweRin";
        String expected = "RIGHT REPEAT *error* PUT MOVE RESTORE *error* *error* *error* POWERINPUT";
        String actual = new AbbreviationsSimple().evaluate(userInput);
        assertThat(actual).isEqualTo(expected);
    }
}