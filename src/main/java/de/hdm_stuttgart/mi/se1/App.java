package de.hdm_stuttgart.mi.se1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;


/**
 * A simple http://logging.apache.org/log4j/2.x demo,
 * see file resources/log4j2.xml for configuration options
 * and A1.log containing debugging output.
 */
//TODO the programm can only work in one direction, (2+2)+((2*3)*5) is not possible. Also only works without literals at the moment
//TODO programm makes no differences in + + 2 3 4 , 2 + 3 + 4, 2 3 + 4 + or 2 3 4 + + . Needs to be fixed so the TODO above  is possible to implement
public class App {
    private static Logger log = LogManager.getLogger(App.class);

    /**
     * Your application's main entry point.
     *
     * @param args Yet unused
     */
    public static String[] calculationOperators;
    public static Stack<Double> inputValuesForCalculation = new Stack<>();

    public static void main(String[] args) {
        /**
         * Scanner for the main source of the InputStream / Userinterface
         * .nextLine() takes the whole scannerInput
         * .next() only takes next string / no sentences
         */
        final Scanner InputStringScanner = new Scanner(System.in);
        System.out.println("Please enter your entry Values to calculate with");
        String InputString = InputStringScanner.nextLine();

        //setting the calculation up

        SetupBot.stringFilterOperatorsIntoArrayAndEverythingElseIntoStack(InputString);

        // solving calculation and printing it out
        System.out.println(SetupBot.solve());

        //TODO unprofessional unity test, still need to implement
        //TODO unity tests for stringFilterOperatorsIntoArrayAndEverythingElseIntoStack and isOperator methods in SetupCalculator class
        /*
        System.out.println("InputString: " + InputString);
        System.out.println("Operatoren: " + Arrays.toString(App.calculationOperators));
        System.out.println("Operator an stelle 1: " + App.calculationOperators[0]);
        System.out.println(App.inputValuesForCalculation.toString());
        System.out.println("oberster wert im stack " + App.inputValuesForCalculation.pop());
*/
        log.debug("With respect to logging you may want to configure file ");
        log.debug("'src/main/resources/log4j2.xml' to suit your needs.");
        log.debug("This config file 'log4j2.xml' will result in 'A1.log'");
        log.debug("file containing logging output as well.");


    }
}