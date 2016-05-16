package com.example.u5778016.calculator;
import com.example.u5778016.calculator.Parsing.BaseConversion;
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
    public void testTransform() throws Exception{
        String infix = "5 + 2 * 3";
        assertEquals(new Transforming(infix).doTransform(), "5#2#3#*#+");
    }
    @Test
    public void testParser() throws Exception {
        assertEquals("((1.3[+]0.2)[*]((38.0[*](47.72[/](56.3[-]24.0)))[^](3.5[-]1.2)))",Tree.generate("(1.3+0.2)*(38*(47.72/(56.3-24)))^(3.5-1.2)", 10).show());
    }

    @Test
    public void testEvaluate() throws Exception {
        assertEquals(Tree.generate("(1.3+0.2)*(38*(47.72/(56.3-24)))^(3.5-1.2)", 10).evaluate(), 15828.4624546, 0.001);
    }

    @Test
    public void testBinary() throws Exception {
        assertEquals(BaseConversion.Decimal_to_Binary(Tree.generate("(1 + 10)^(1 + 1 * 10)", 2).evaluate()), "11011");
    }

    @Test
    public void testOctal() throws Exception {
        assertEquals(BaseConversion.Decimal_to_Octal(Tree.generate("56 + 12", 8).evaluate()),"70");
    }

    @Test
    public void testHex() throws Exception {
        assertEquals(BaseConversion.Decimal_to_Hex(Tree.generate("A.A + B.B - 3", 16).evaluate()),"13.5");
    }
}
