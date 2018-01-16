package de.hdm_stuttgart.mi.se1;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Stack;

import static org.junit.Assert.assertEquals;

public class TestExceptions {


    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @After
    public void resetAll(){
        SetupBot.currentIndex=0;
        SetupBot.failed=false;
        SetupBot.reading=true;
        App.calculationNumbersStack=new Stack<>();
        SetupBot.preOperatorBuffer = new StringBuffer("");

    }

   public String[] generateStringArray(String fakeScannerInputString){
        return fakeScannerInputString.split(" ");
   }

    @Test
    public void testSomething() {
        thrown.expect(NumberStructureException.class);
     String[] a=generateStringArray("2+ 2 +");
   /*  try{
SetupBot.sort(a); }catch(ExceptionCluster e){}*/
    }

}