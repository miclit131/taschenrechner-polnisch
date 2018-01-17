package de.hdm_stuttgart.mi.se1;

import de.hdm_stuttgart.mi.se1.exceptions.*;
import de.hdm_stuttgart.mi.se1.exceptions.OperatorLimitException;
import de.hdm_stuttgart.mi.se1.project.App;
import de.hdm_stuttgart.mi.se1.project.SetupBot;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import java.util.Stack;
import de.hdm_stuttgart.mi.se1.exceptions.ExceptionCluster;
import static org.junit.Assert.assertEquals;
import de.hdm_stuttgart.mi.se1.exceptions.ExceptionCluster;
public class TestExceptions {


    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @After
    public void resetAll() {
        SetupBot.currentIndex = 0;
        SetupBot.failed = false;
        SetupBot.reading = true;
        App.calculationNumbersStack = new Stack<>();
        SetupBot.preOperatorBuffer = new StringBuffer("");

    }

    private String[] generateStringArray(String fakeScannerInputString) {
        return fakeScannerInputString.split(" ");
    }

    @Test
    public void testSomething() {

        thrown.expect(NumberStructureException.class);
        String[] a = generateStringArray("2+ 2 +");
        SetupBot.sort(a);
    }
}