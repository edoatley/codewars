package org.codewars.two2one;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

public class TwoToOneTest {

    @Test
    public void testOne() {
        assertThat(TwoToOne.longest("aretheyhere", "yestheyarehere"))
                .isEqualTo("aehrsty");
    }

    @Test
    public void testTwo() {
        assertThat(TwoToOne.longest("loopingisfunbutdangerous", "lessdangerousthancoding"))
                .isEqualTo("abcdefghilnoprstu");
    }

    @Test
    public void testThree() {
        assertThat(TwoToOne.longest("inmanylanguages", "theresapairoffunctions"))
                .isEqualTo("acefghilmnoprstuy");
    }
}