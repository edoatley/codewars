package org.hacker;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class HourglassTest {


    @Test
    void maxHourGlass() {
        int[][] arr = {new int[]{1, 1, 1, 0, 0, 0},
                new int[]{0, 1, 0, 0, 0, 0},
                new int[]{1, 1, 1, 0, 0, 0},
                new int[]{0, 0, 2, 4, 4, 0},
                new int[]{0, 0, 0, 2, 0, 0},
                new int[]{0, 0, 1, 2, 4, 0}};
        int actual = Hourglass.maxHourGlass(arr);
        assertThat(actual).isEqualTo(19);
    }
    @Test
    void maxHourGlassNeg() {
        int[][] arr = {new int[]{-1, -1, 0, -9, -2, -2},
                       new int[]{-2, -1, -6, -8, -2, -5},
                       new int[]{-1, -1, -1, -2, -3, -4},
                       new int[]{-1, -9, -2, -4, -4, -5},
                       new int[]{-7, -3, -3, -2, -9, -9},
                       new int[]{-1, -3, -1, -2, -4, -5}};
        int actual = Hourglass.maxHourGlass(arr);
        assertThat(actual).isEqualTo(-6);
    }
}