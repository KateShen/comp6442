package com.example.u5778016.calculator;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals("#1#2#+#3#4#5#+#*#*", Formula.getInstnace().transformToSuffix("(1+2)*(3*(4+5))"));
    }
}