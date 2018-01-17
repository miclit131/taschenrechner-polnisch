package de.hdm_stuttgart.mi.se1;

import static org.junit.Assert.*;

import de.hdm_stuttgart.mi.se1.project.App;
import de.hdm_stuttgart.mi.se1.project.SetupBot;
import org.junit.After;
import org.junit.Test;

import java.util.Stack;

/**
 * Unit tests
 */
public class TestApp {

    @After
    public void resetAll(){
        SetupBot.currentIndex=0;
        SetupBot.failed=false;
        SetupBot.reading=true;
        App.calculationNumbersStack=new Stack<>();
        SetupBot.preOperatorBuffer = new StringBuffer("");

    }
    public static double testHelper(String testString){
        String[] testArray=testString.split(" ");
        try{
            SetupBot.sort(testArray);
        } catch (Exception e){

        }

        return App.result;
    }
    /**
     * Testing addition
     */
    @Test
    public void testAddition1(){
        assertEquals(5.0, testHelper("2 3 +"), Math.pow(10,-14));
    }
    @Test
    public void testAddition2(){
        assertEquals(0.0, testHelper("2 -2 +"), Math.pow(10,-14));
    }
    @Test
    public void testAddition3(){
        assertEquals(-4.0, testHelper("-2 -2 +"), Math.pow(10,-14));
    }
    @Test
    public void testAddition4(){
        assertEquals(3.0, testHelper("3 0 +"), Math.pow(10,-14));
    }

    /**
     * Testing subtraction
     */
    @Test
    public void testSubstraction1(){
        assertEquals(0.0, testHelper("2 2 -"), Math.pow(10,-14));
    }
    @Test
    public void testSubstraction2(){
        assertEquals(4.0, testHelper("2 -2 -"), Math.pow(10,-14));
    }
    @Test
    public void testSubstraction3(){
        assertEquals(0.0, testHelper("-2 -2 -"), Math.pow(10,-14));
    }
    @Test
    public void testSubstraction4(){
        assertEquals(5.0, testHelper("5 0 -"), Math.pow(10,-14));
    }
    @Test
    public void testSubstraction5(){
        assertEquals(-3.0, testHelper("5 8 -"), Math.pow(10,-14));
    }

    /**
     * Testing division
     */
    @Test
    public void testDivision1(){
        assertEquals(1.0, testHelper("2 2 /"), Math.pow(10,-14));
    }
    @Test
    public void testDivision2(){
        assertEquals(-1.0, testHelper("2 -2 /"), Math.pow(10,-14));
    }
    @Test
    public void testDivision3(){
        assertEquals(1.0, testHelper("-2 -2 /"), Math.pow(10,-14));
    }
    @Test
    public void testDivision4(){
        assertEquals(0.66666666666666666, testHelper("4 6 /"), Math.pow(10,-14));
    }
    @Test
    public void testDivision5(){
        assertEquals(1.5, testHelper("6 4 /"), Math.pow(10,-14));
    }
    /*@Test
    public void testDivision6(){
        assertEquals(-Infinity, testHelper("0 42 /"), Math.pow(10,-14)); //not testable
    }*/

    /**
     * Testing multiplication
     */
    @Test
    public void testMultiplikation1(){
        assertEquals(4.0, testHelper("2 2 *"), Math.pow(10,-14));
    }
    @Test
    public void testMultiplikation2(){
        assertEquals(-4.0, testHelper("2 -2 *"), Math.pow(10,-14));
    }
    @Test
    public void testMultiplikation3(){
        assertEquals(4.0, testHelper("-2 -2 *"), Math.pow(10,-14));
    }
    @Test
    public void testMultiplikation4(){
        assertEquals(0.0, testHelper("2 0 *"), Math.pow(10,-14));
    }
    @Test
    public void testMultiplikation5(){
        assertEquals(42.0, testHelper("2 21 *"), Math.pow(10,-14));
    }

    /**
     * Testing pow
     */
    @Test
    public void testPow1(){
        assertEquals(4.0, testHelper("2 2 pow"), Math.pow(10,-14));
    }
    @Test
    public void testPow2(){
        assertEquals(0.25, testHelper("2 -2 pow"), Math.pow(10,-14));
    }
    @Test
    public void testPow3(){
        assertEquals(4.0, testHelper("-2 2 pow"), Math.pow(10,-14));
    }
    @Test
    public void testPow4(){
        assertEquals(0.25, testHelper("-2 -2 pow"), Math.pow(10,-14));
    }
    @Test
    public void testPow5(){
        assertEquals(1.0, testHelper("2 0 pow"), Math.pow(10,-14));
    }
    @Test
    public void testPow6(){
        assertEquals(343.0, testHelper("7 3 pow"), Math.pow(10,-14));
    }

    /**
     * Testing unary sine
     */
    @Test
    public void testUnarySubstraction1(){
        assertEquals(-2.0, testHelper("-2"), Math.pow(10,-14));
    }
    @Test
    public void testUnarySubstraction2(){
        assertEquals(-97.0, testHelper("-97"), Math.pow(10,-14));
    }
    @Test
    public void testUnarySubstraction3(){
        assertEquals(-0.0, testHelper("-0"), Math.pow(10,-14));
    }

    /**
     * Testing sine
     */
    @Test
    public void testSine1(){
        assertEquals(0.0, testHelper("0 sin"), Math.pow(10,-14));
    }
    @Test
    public void testSine2(){
        assertEquals(0.9092974268256817, testHelper("2 sin"), Math.pow(10,-16));
    }
    @Test
    public void testSine3(){
        assertEquals(-0.8414709848078965, testHelper("-1 sin"), Math.pow(10,-16));
    }

    /**
     * Testing cosine
     */
    @Test
    public void testCosine1(){
        assertEquals(1.0, testHelper("0 cos"), Math.pow(10,-14));
    }
    @Test
    public void testCosine2(){
        assertEquals(-0.4161468365471424, testHelper("2 cos"), Math.pow(10,-14));
    }
    @Test
    public void testCosine3(){
        assertEquals(0.5403023058681398, testHelper("-1 cos"), Math.pow(10,-14));
    }

    /**
     * Testing tangent
     */
    @Test
    public void testTangent1(){
        assertEquals(0, testHelper("0 tan"), Math.pow(10,-14));
    }
    @Test
    public void testTangent2(){
        assertEquals(-2.185039863261519, testHelper("2 tan"), Math.pow(10,-14));
    }
    @Test
    public void testTangent3(){
        assertEquals(-1.5574077246549023, testHelper("-1 tan"), Math.pow(10,-14));
    }

    /**
     * Testing exponentials
     */
    @Test
    public void testExponentials1(){
        assertEquals(7.38905609893065, testHelper("2 exp"), Math.pow(10,-14));
    }
    @Test
    public void testExponentials2(){
        assertEquals(1.0, testHelper("0 exp"), Math.pow(10,-14));
    }
    @Test
    public void testExponentials3(){
        assertEquals(0.049787068367863944, testHelper("-3 exp"), Math.pow(10,-14));
    }

    /**
     * Testing log e
     */
    @Test
    public void testLogE1(){
        assertEquals(0.6931471805599453, testHelper("2 ln"), Math.pow(10,-14));
    }
    @Test
    public void testLogE2(){
        assertEquals(0.0, testHelper("1 ln"), Math.pow(10,-14));
    }

    /**
     * Testing square root
     */
    @Test
    public void testSquareRoot1(){
        assertEquals(2, testHelper("4 sqrt"), Math.pow(10,-14));
    }
    @Test
    public void testSquareRoot2(){
        assertEquals(3.1622776601683795, testHelper("10 sqrt"), Math.pow(10,-14));
    }
    @Test
    public void testSquareRoot3(){
        assertEquals(0.0, testHelper("0 sqrt"), Math.pow(10,-14));
    }

    /**
     * Testing complex calculations
     */
    @Test
    public void testComplex1(){
        assertEquals(1.0, testHelper("2 2 + 2 2 + /"), Math.pow(10,-14));
    }
    @Test
    public void testComplex2(){
        assertEquals(5.0, testHelper("5 2 pow sqrt"), Math.pow(10,-14));
    }
    @Test
    public void testComplex3(){
        assertEquals(757.9, testHelper("2.3 56 + 13 *"), Math.pow(10,-14));
    }
    @Test
    public void testComplex4(){
        assertEquals(4.828313737302301, testHelper(" 3 5 ln *"), Math.pow(10,-14));
    }
}
