package de.hdm_stuttgart.mi.se1;

import static org.junit.Assert.*;
import org.junit.Test;
import de.hdm_stuttgart.mi.se1.*;

/**
 * Unit tests
 */
public class TestApp {

    /**
     * Testing the testHelper method
     */
    @Test
    public void testTheTest(){
        assertEquals(4.0, SetupBot.testHelper("2 2 +"), Math.pow(10,-14));
    }

    /**
     * Testing simple addition
     */
    @Test
    public void testAddition(){
        assertEquals(4.0, SetupBot.testHelper("2 2 +"), Math.pow(10,-14));
        assertEquals(0.0, SetupBot.testHelper("2 -2 +"), Math.pow(10,-14));
        assertEquals(-4.0, SetupBot.testHelper("-2 -2 +"), Math.pow(10,-14));
    }
    /**
     * Testing simple addition
     */
    @Test
    public void testAddition2(){
        assertEquals(0.0, SetupBot.testHelper("2 -2 +"), Math.pow(10,-14));

    }
    /**
     * Testing simple addition.
     */
    @Test
    public void testSimpleAddition(){

        /*String testStringA="2 2 +";
        String[] testArrayA=testStringA.split(" ");
        SetupBot.sort(testArrayA);
        assertEquals(4.0 , App.result,Math.pow(10,-14));*/

        String testStringB="2 -2 +";
        String[] testArrayB=testStringB.split(" ");
        SetupBot.sort(testArrayB);
        assertEquals(0.0 , App.result,Math.pow(10,-14));

        /*String testStringC="-2 -2 +";
        String[] testArrayC=testStringC.split(" ");
        SetupBot.sort(testArrayC);
        assertEquals(-4.0 , App.result,Math.pow(10,-14));*/
    }

    /**
     * Testing simple substraction.
     */
    @Test
    public void testSimpleSubstraction(){

        String testStringA="2 2 -";
        String[] testArrayA=testStringA.split(" ");
        SetupBot.sort(testArrayA);
        assertEquals(4.0 , App.result,Math.pow(10,-14));

        String testStringB="2 -2 +";
        String[] testArrayB=testStringB.split(" ");
        SetupBot.sort(testArrayB);
        assertEquals(0.0 , App.result,Math.pow(10,-14));

        String testStringC="-2 -2 +";
        String[] testArrayC=testStringC.split(" ");
        SetupBot.sort(testArrayC);
        assertEquals(-4.0 , App.result,Math.pow(10,-14));
    }    /**
     * Testing simple addition.
     */
    @Test
    public void testSimpleDivision(){

        String testStringA="2 2 /";
        String[] testArrayA=testStringA.split(" ");
        SetupBot.sort(testArrayA);
        assertEquals(4.0 , App.result,Math.pow(10,-14));

        String testStringB="2 -2 +";
        String[] testArrayB=testStringB.split(" ");
        SetupBot.sort(testArrayB);
        assertEquals(0.0 , App.result,Math.pow(10,-14));

        String testStringC="-2 -2 +";
        String[] testArrayC=testStringC.split(" ");
        SetupBot.sort(testArrayC);
        assertEquals(-4.0 , App.result,Math.pow(10,-14));
    }    /**
     * Testing simple addition.
     */
    @Test
    public void testSimpleMultiplication(){

        String testStringA="2 2 *";
        String[] testArrayA=testStringA.split(" ");
        SetupBot.sort(testArrayA);
        assertEquals(4.0 , App.result,Math.pow(10,-14));

        String testStringB="2 -2 +";
        String[] testArrayB=testStringB.split(" ");
        SetupBot.sort(testArrayB);
        assertEquals(0.0 , App.result,Math.pow(10,-14));

        String testStringC="-2 -2 +";
        String[] testArrayC=testStringC.split(" ");
        SetupBot.sort(testArrayC);
        assertEquals(-4.0 , App.result,Math.pow(10,-14));
    }
}
