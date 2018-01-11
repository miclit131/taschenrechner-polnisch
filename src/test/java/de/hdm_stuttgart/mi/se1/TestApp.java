package de.hdm_stuttgart.mi.se1;

import static org.junit.Assert.*;
import org.junit.Test;
import de.hdm_stuttgart.mi.se1.*;

/**
 * Unit tests
 */
public class TestApp {

    /**
     * Testing simple addition.
     */
    @Test
    public void testSimpleAddition(){
     //   SetupBot.stringFilterOperatorsIntoArrayAndEverythingElseIntoStack("5");
       // assertEquals(4, SetupBot.solve(),Math.pow(10,-14));

        String testString="2 2 +";
        String[] testArray=testString.split(" ");
        SetupBot.sort(testArray);
        assertEquals(4.0 , App.result,Math.pow(10,-14));
    }
    /**
     * Testing simple subtraction.
     */
    @Test
    public void testSimpleSubtraction(){
        //assertArrayEquals(5, MathOperation.add("2 3 -"));
    }
}
