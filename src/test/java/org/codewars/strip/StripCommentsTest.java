package org.codewars.strip;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StripCommentsTest {

    @Test
    public void stripComments1() throws Exception {
        assertEquals(
                "apples, pears\ngrapes\nbananas",
                StripComments.stripComments("apples, pears # and bananas\ngrapes\nbananas !apples", new String[]{"#", "!"})
        );
    }
    @Test
    public void stripComments2() throws Exception {
        assertEquals(
                "a\nc\nd",
                StripComments.stripComments( "a #b\nc\nd $e f g", new String[] { "#", "$" } )
        );

    }
}