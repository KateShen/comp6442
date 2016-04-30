package com.example.u5778016.calculator;
import com.example.u5778016.calculator.Parsing.Tree;;

import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void testParser() throws Exception {
        assertEquals("((1.3[+]0.2)[*]((38.0[*](47.72[/](56.3[-]24.0)))[^](3.5[-]1.2)))",Tree.generate("(1.3+0.2)*(38*(47.72/(56.3-24)))^(3.5-1.2)").show());
    }

    @Test
    public void testEvaluate() throws Exception {
        assertEquals(Tree.generate("(1.3+0.2)*(38*(47.72/(56.3-24)))^(3.5-1.2)").evaluate(), 15828.4624546 , 0.001);
        System.out.println("(1.3+0.2)*(38*(47.72/(56.3-24)))^(3.5-1.2) equals " + Tree.generate("(1.3+0.2)*(38*(47.72/(56.3-24)))^(3.5-1.2)").evaluate());
    }
}
