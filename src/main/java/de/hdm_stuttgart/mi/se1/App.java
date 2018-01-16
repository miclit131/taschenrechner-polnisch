package de.hdm_stuttgart.mi.se1;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

import java.util.Scanner;
import java.util.Stack;


//TODO the programm can only work in one direction, (2+2)+((2*3)*5) is not possible. Also only works without literals at the moment
//TODO programm makes no differences in + + 2 3 4 , 2 + 3 + 4, 2 3 + 4 + or 2 3 4 + + . Needs to be fixed so the TODO above  is possible to implement
public class App {
    //private static Logger log = LogManager.getLogger(App.class);

    public static boolean runProgramm = true;
    public static String[] operatorArray;
    public static Stack<Double> calculationNumbersStack = new Stack<>();
    public static double result;
    public static String[] InputStringArray;
    public static void main(String[] args) {
//          Scanner for the main source of the InputStream / Userinterface
//          .nextLine() takes the whole scannerInput
//          .next() only takes next string / no sentences
        final Scanner InputStringScanner = new Scanner(System.in);
        System.out.println("Welcome to the best RPN calculator in the whoooole world!!! \uD83C\uDF0D \uD83C\uDF38");
        System.out.println("Have fun playing with numbers you little nerd \uD83C\uDFAE");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        while(runProgramm) {
            System.out.println("Do you wanna continue calculating? Or are you tired of maths? \nENTER your entry values to calculate with or ENTER \" stahp! \" to stahp the program");
            System.out.print("ENTRY : ");
            String InputString = InputStringScanner.nextLine();
            if(InputString.equals("stahp!")){
                System.out.println("Thank you very much for using our wonderful program ✨✨✨");
                System.out.println("Please enter your credit card number here: _ _ _ _ _ _ _ _ _ _ _ _ _ _ _" +
                        "\n \uD83D\uDCB0 \uD83D\uDCB8 \uD83D\uDCB0 \uD83D\uDCB8 \uD83D\uDCB0 \uD83D\uDCB8 \uD83D\uDCB0 \uD83D\uDCB8");
                runProgramm=false;
                break;
            }

            App.InputStringArray = InputString.split(" ");
            //setting the calculation up
            try {
                SetupBot.sort(InputStringArray);
            }catch(JustOperatorsException | EmptyNumberStackException e){
                System.out.println("___________________________________________________________");
                System.out.println(e.getMessage());
                System.out.println("___________________________________________________________");
            }
try {
    SetupBot.showResult();
}catch(InputUnbalancedException | EmptyNumberStackException e){
    System.out.println("___________________________________________________________");
    System.out.println(e.getMessage());
    System.out.println("___________________________________________________________");
}

            SetupBot.currentIndex=0;
            SetupBot.failed=false;
            SetupBot.reading=true;
            App.calculationNumbersStack=new Stack<>();
        }
        //TODO entering d ends up to be a format exception
        //TODO unprofessional unity test, still need to implement
        //TODO unity tests for sort and isOperator methods in SetupCalculator class
        /*
        System.out.println("InputString: " + InputString);
        System.out.println("Operatoren: " + Arrays.toString(App.operatorArray));
        System.out.println("Operator an stelle 1: " + App.operatorArray[0]);
        System.out.println(App.calculationNumbersStack.toString());
        System.out.println("oberster wert im stack " + App.calculationNumbersStack.pop());
*//*
        log.debug("With respect to logging you may want to configure file ");
        log.debug("'src/main/resources/log4j2.xml' to suit your needs.");
        log.debug("This config file 'log4j2.xml' will result in 'A1.log'");
        log.debug("file containing logging output as well.");
*/
        //TODO play with me mates :) put in binary hexa or decimal numbers with . ,  E e 0b 0x into undefinedNumber to test it out please :o
        // TODO die neues methode des Programms wird hier kurz getestet
      /*  if (!SetupBot.failed) {
            try {
                System.out.println(Double.parseDouble(SetupBot.convertLiterals("0x1-")));
                // System.out.println(Double.parseDouble("0x1"));
            } catch (NumberFormatException e) {
                String[] a = SetupBot.convertLiterals("0x+1-1-").split("#");

                System.out.println("\n________________________________________");
                System.out.println(InputString+" has failed ");
                for (int j = 1; j < a.length; j++) {
                    System.out.print(a[j]);
                }
                System.out.println("\n________________________________________");
            }
        }
            */
    }
}