package de.hdm_stuttgart.mi.se1;

import de.hdm_stuttgart.mi.se1.exceptions.*;
import de.hdm_stuttgart.mi.se1.project.App;
import de.hdm_stuttgart.mi.se1.project.SetupBot;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import java.util.Stack;

/**
 * Testing all personal implemented Exception
 * by forcing the corresponding methods to throw
 * exactly the tested exception
 */

public class TestExceptions {

    @Rule
    final public ExpectedException thrown = ExpectedException.none();

    @SuppressWarnings("Duplicates")
    @After
    public void resetAll() {
        SetupBot.currentIndex = 0;
        SetupBot.failed = false;
        SetupBot.reading = true;
        App.calculationNumbersStack = new Stack<>();
        SetupBot.preOperatorBuffer = new StringBuffer("");
    }

    private String[] generateTestArray(String fakeScannerInputString) {
        return fakeScannerInputString.split(" ");
    }

    /**
     * TESTING NumberStructureException
     */
    @Test
    public void testNumberStructureException() {
        thrown.expect(NumberStructureException.class);
            SetupBot.sort(generateTestArray("2+"));
    }
    @Test
    public void testNumberStructureException2() {
        thrown.expect(NumberStructureException.class);
             SetupBot.sort(generateTestArray("2+ 2 +"));
    }
    @Test
    public void testNumberStructureException3() {
        thrown.expect(NumberStructureException.class);
        SetupBot.sort(generateTestArray("2h-"));
    }
    @Test
    public void testNumberStructureException4() {
        thrown.expect(NumberStructureException.class);
        SetupBot.sort(generateTestArray("2h"));
    }
    @Test
    public void testNumberStructureException5() {
        thrown.expect(NumberStructureException.class);
        SetupBot.sort(generateTestArray("2 2g- +"));
    }
    @Test
    public void testNumberStructureException6() {
        thrown.expect(NumberStructureException.class);
        SetupBot.sort(generateTestArray("ob1"));
    }

    /**
     * TESTING NumberStructureException with binary values
     */
    @Test
    public void testNumberStructureExceptionBinary() {
        thrown.expect(NumberStructureException.class);
        SetupBot.sort(generateTestArray("0b2"));
    }
    @Test
    public void testNumberStructureExceptionBinary2() {
        thrown.expect(NumberStructureException.class);
        SetupBot.sort(generateTestArray("-0b3"));
    }
    @Test
    public void testNumberStructureExceptionBinary3() {
        thrown.expect(NumberStructureException.class);
        SetupBot.sort(generateTestArray("2 -0b3 +"));
    }

    /**
     * TESTING NumberStructureException with hexa decimal values
     */
    @Test
    public void testNumberStructureExceptionHexa() {
        thrown.expect(NumberStructureException.class);
        SetupBot.sort(generateTestArray("0x-2"));
    }
    @Test
    public void testNumberStructureExceptionHexa2() {
        thrown.expect(NumberStructureException.class);
        SetupBot.sort(generateTestArray("0xAa-"));
    }
    @Test
    public void testNumberStructureExceptionHexa3() {
        thrown.expect(NumberStructureException.class);
        SetupBot.sort(generateTestArray("-0xa-"));
    }
    @Test
    public void testNumberStructureExceptionHexa4() {
        thrown.expect(NumberStructureException.class);
        SetupBot.sort(generateTestArray("0xj"));
    }

    /**
     * TESTING NumberStructureException within exponential values
     */
    @Test
    public void testNumberStructureExceptionExponential() {
        thrown.expect(NumberStructureException.class);
        SetupBot.sort(generateTestArray("2E0x2"));
    }
    @Test
    public void testNumberStructureExceptionExponential2() {
        thrown.expect(NumberStructureException.class);
        SetupBot.sort(generateTestArray("0b101E3-3"));
    }
    @Test
    public void testNumberStructureExceptionExponential3() {
        thrown.expect(NumberStructureException.class);
        SetupBot.sort(generateTestArray("0B0101E3-3"));
    }
    @Test
    public void testNumberStructureExceptionExponential4() {
        thrown.expect(NumberStructureException.class);
        SetupBot.sort(generateTestArray("0b011E0b1"));
    }
    @Test
    public void testNumberStructureExceptionExponential5() {
        thrown.expect(NumberStructureException.class);
        SetupBot.sort(generateTestArray("0b1Ehello"));
    }
    /**
    *TESTING OperatorLimitException with all binary operators,
    *unary operators can't trigger OperatorLimitException because of other exceptions
    */
    @Test
    public void testOperatorLimitException() {
        thrown.expect(OperatorLimitException.class);
        App.InputStringArray = SetupBot.splitSafe("2 +");
        SetupBot.sort(App.InputStringArray);
    }
    @Test
    public void testOperatorLimitException2() {
        thrown.expect(OperatorLimitException.class);
        App.InputStringArray = SetupBot.splitSafe("2 2 + +");
        SetupBot.sort(App.InputStringArray);
    }
    @Test
    public void testOperatorLimitException3() {
        thrown.expect(OperatorLimitException.class);
        App.InputStringArray = SetupBot.splitSafe("2 *");
        SetupBot.sort(App.InputStringArray);
    }
    @Test
    public void testOperatorLimitException4() {
        thrown.expect(OperatorLimitException.class);
        App.InputStringArray = SetupBot.splitSafe("2 2 + *");
        SetupBot.sort(App.InputStringArray);
    }
    @Test
    public void testOperatorLimitException5() {
        thrown.expect(OperatorLimitException.class);
        App.InputStringArray = SetupBot.splitSafe("2 2 + *");
        SetupBot.sort(App.InputStringArray);
    }
    @Test
    public void testOperatorLimitException6() {
        thrown.expect(OperatorLimitException.class);
        App.InputStringArray = SetupBot.splitSafe("2 2 * /");
        SetupBot.sort(App.InputStringArray);
    }
    @Test
    public void testOperatorLimitException7() {
        thrown.expect(OperatorLimitException.class);
        App.InputStringArray = SetupBot.splitSafe("2 2 - pow");
        SetupBot.sort(App.InputStringArray);
    }
    @Test
    public void testOperatorLimitException8() {
        thrown.expect(OperatorLimitException.class);
        App.InputStringArray = SetupBot.splitSafe("2 -");
        SetupBot.sort(App.InputStringArray);
    }
    @Test
    public void testOperatorLimitException9() {
        thrown.expect(OperatorLimitException.class);
        App.InputStringArray = SetupBot.splitSafe("2 pow");
        SetupBot.sort(App.InputStringArray);
    }
    /**
    *TESTING LiteralAbuseException with the euler number,
    *the program only allows -/e or -/eE2 which stands for (+/-)e*10^2
    */
    @Test
    public void testLiteralAbuseExceptionEuler() {
        thrown.expect(LiteralAbuseException.class);
        App.InputStringArray = SetupBot.splitSafe("0e");
        SetupBot.sort(App.InputStringArray);
    }
    @Test
    public void testLiteralAbuseExceptionEuler2() {
        thrown.expect(LiteralAbuseException.class);
        App.InputStringArray = SetupBot.splitSafe("2e");
        SetupBot.sort(App.InputStringArray);
    }
    @Test
    public void testLiteralAbuseExceptionEuler3() {
        thrown.expect(LiteralAbuseException.class);
        App.InputStringArray = SetupBot.splitSafe("e2");
        SetupBot.sort(App.InputStringArray);
    }
    @Test
    public void testLiteralAbuseExceptionEuler4() {
        thrown.expect(LiteralAbuseException.class);
        App.InputStringArray = SetupBot.splitSafe("23e2E2");
        SetupBot.sort(App.InputStringArray);
    }
    /**
    * TESTING  LiteralAbuseException with the circular number,
    *the program only allows -/pi or -/piE2 which stands for (+/-)pi*10^2
    */
    @Test
    public void testLiteralAbuseExceptionPI() {
        thrown.expect(LiteralAbuseException.class);
        App.InputStringArray = SetupBot.splitSafe("0pi");
        SetupBot.sort(App.InputStringArray);
    }
    @Test
    public void testLiteralAbuseExceptionPI2() {
        thrown.expect(LiteralAbuseException.class);
        App.InputStringArray = SetupBot.splitSafe("pi0");
        SetupBot.sort(App.InputStringArray);
    }
    @Test
    public void testLiteralAbuseExceptionPI3() {
        thrown.expect(LiteralAbuseException.class);
        App.InputStringArray = SetupBot.splitSafe("5pi");
        SetupBot.sort(App.InputStringArray);
    }
    @Test
    public void testLiteralAbuseExceptionPI4() {
        thrown.expect(LiteralAbuseException.class);
        App.InputStringArray = SetupBot.splitSafe("pi55");
        SetupBot.sort(App.InputStringArray);
    }
    @Test
    public void testLiteralAbuseExceptionPI5() {
        thrown.expect(LiteralAbuseException.class);
        App.InputStringArray = SetupBot.splitSafe("22pi55");
        SetupBot.sort(App.InputStringArray);
    }

    /**
     * TESTING JustOperatorsException
     */
    @Test
    public void testJustOperatorsException() {
        thrown.expect(JustOperatorsException.class);
        App.InputStringArray = SetupBot.splitSafe("+");
        SetupBot.sort(App.InputStringArray);
    }
    @Test
    public void testJustOperatorsException2() {
        thrown.expect(JustOperatorsException.class);
        App.InputStringArray = SetupBot.splitSafe("~");
        SetupBot.sort(App.InputStringArray);
    }
    @Test
    public void testJustOperatorsException3() {
        thrown.expect(JustOperatorsException.class);
        App.InputStringArray = SetupBot.splitSafe("+ /");
        SetupBot.sort(App.InputStringArray);
    }
    @Test
    public void testJustOperatorsException4() {
        thrown.expect(JustOperatorsException.class);
        App.InputStringArray = SetupBot.splitSafe("~ sin");
        SetupBot.sort(App.InputStringArray);
    }
    /**
     * TESTING UnbalancedInputException
     */
    @Test
    public void testInputUnbalancedException() {
        thrown.expect(InputUnbalancedException.class);
        App.calculationNumbersStack=new Stack<>();
        App.calculationNumbersStack.push(2.0);
        App.calculationNumbersStack.push(3.0);
        SetupBot.showResult();
    }
    @Test
    public void testInputUnbalancedException2() {
        thrown.expect(InputUnbalancedException.class);
        App.calculationNumbersStack=new Stack<>();
        App.calculationNumbersStack.push(2.0+2.0);
        App.calculationNumbersStack.push(3.0+Math.PI);
        SetupBot.showResult();
    }
    @Test
    public void testInputUnbalancedException3() {
        thrown.expect(InputUnbalancedException.class);
        App.InputStringArray = SetupBot.splitSafe("2 2");
        SetupBot.sort(App.InputStringArray);
        SetupBot.showResult();
    }
    @Test
    public void testInputUnbalancedException4() {
        thrown.expect(InputUnbalancedException.class);
        App.InputStringArray = SetupBot.splitSafe("2 2 + 2 2 +");
        SetupBot.sort(App.InputStringArray);
        SetupBot.showResult();
    }
    /**
     * TESTING FoundQuitException
     */
    @Test
    public void testFoundQuitException() {
        thrown.expect(FoundQuitException.class);
        App.InputStringArray = SetupBot.splitSafe(" quit");
    }
    @Test
    public void testFoundQuitException2() {
        thrown.expect(FoundQuitException.class);
        App.InputStringArray = SetupBot.splitSafe(" 2+quit");
    }
    @Test
    public void testFoundQuitException3() {
        thrown.expect(FoundQuitException.class);
        App.InputStringArray = SetupBot.splitSafe(" qqquittt");
    }
    @Test
    public void testFoundQuitException4() {
        thrown.expect(FoundQuitException.class);
        App.InputStringArray = SetupBot.splitSafe(" quittttt");
    }
    @Test
    public void testFoundQuitException5() {
        thrown.expect(FoundQuitException.class);
        App.InputStringArray = SetupBot.splitSafe(" 20xFqquit+");
    }
    @Test
    public void testFoundQuitException6() {
        thrown.expect(FoundQuitException.class);
        App.InputStringArray = SetupBot.splitSafe(" 2    2  +             quit");
    }
/**
 * TESTING EmpyEntryException
 */
@Test
public void testEmptyEntryException() {
    thrown.expect(EmptyEntryException.class);
    App.InputStringArray = SetupBot.splitSafe("");
}
    @Test
    public void testEmptyEntryException2() {
        thrown.expect(EmptyEntryException.class);
        App.InputStringArray = SetupBot.splitSafe("                            ");
    }
}