package org.codewars.threaded;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.*;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class PersonTest {
    @Test
    public void the_correct_numbers_are_counted() {
        Counter counter = new Counter();
        ThreadedCounter.countInThreads(counter);

        Set<Integer> expected = IntStream.rangeClosed(1, 100)
                .boxed()
                .collect(Collectors.toSet());
        Set<Integer> actual = new HashSet<>(counter.getNumbers());

        assertEquals(expected, actual, "The expected numbers were not generated");
    }

    @Test
    public void the_correct_numbers_are_counted_in_sequence() {
        Counter counter = new Counter();
        ThreadedCounter.countInThreads(counter);

        List<Integer> expected = IntStream.rangeClosed(1, 100)
                .boxed()
                .collect(Collectors.toList());
        List<Integer> actual = counter.getNumbers();

        assertEquals(expected, actual, "The expected numbers were not generated in the right sequence");
    }

    @Test
    public void three_threads_are_used() {
        Counter counter = new Counter();
        ThreadedCounter.countInThreads(counter);

        assertEquals(3, counter.getThreadIds().size(), "The numbers are written in three different threads");
    }

    @Test
    public void numbers_are_in_the_correct_threads() {
        Counter counter = new Counter();
        ThreadedCounter.countInThreads(counter);

        List<Integer> expected1 = IntStream.rangeClosed(1, 100)
                .filter(i -> i % 3 == 1)
                .boxed()
                .collect(Collectors.toList());
        List<Integer> expected2 = IntStream.rangeClosed(1, 100)
                .filter(i -> i % 3 == 2)
                .boxed()
                .collect(Collectors.toList());
        List<Integer> expected3 = IntStream.rangeClosed(1, 100)
                .filter(i -> i % 3 == 0)
                .boxed()
                .collect(Collectors.toList());

        assertEquals(expected1, counter.getNumbersInSameThreadAs(1), "Correct thread for 1, 4, 7...");
        assertEquals(expected2, counter.getNumbersInSameThreadAs(2), "Correct thread for 2, 5, 8...");
        assertEquals(expected3, counter.getNumbersInSameThreadAs(3), "Correct thread for 3, 6, 9...");
    }
}
