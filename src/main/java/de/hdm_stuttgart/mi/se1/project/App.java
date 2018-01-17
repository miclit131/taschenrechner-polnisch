package de.hdm_stuttgart.mi.se1.project;
import de.hdm_stuttgart.mi.se1.exceptions.ExceptionCluster;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

import java.util.Scanner;
import java.util.Stack;


//TODO the programm can only work in one direction, (2+2)+((2*3)*5) is not possible. Also only works without literals at the moment
//TODO programm makes no differences in + + 2 3 4 , 2 + 3 + 4, 2 3 + 4 + or 2 3 4 + + . Needs to be fixed so the TODO above  is possible to implement
public class App {
    //private static Logger log = LogManager.getLogger(App.class);

    private static boolean runProgramm = true;
    public static String[] operatorArray;
    public static Stack<Double> calculationNumbersStack = new Stack<>();
    public static double result;
    public static String[] InputStringArray;
    public static void main(String[] args) {
//          Scanner for the main source of the InputStream / Userinterface
//          .nextLine() takes the whole scannerInput
//          .next() only takes next string / no sentences
        final Scanner InputStringScanner = new Scanner(System.in);
        //starting text first "frame" of the program
        System.out.println("Welcome to the best RPN calculator in the whoooole world!!! \uD83C\uDF0D \uD83C\uDF38");
        System.out.println("Have fun playing with numbers little nerd \uD83C\uDFAE");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        while(runProgramm) {
            System.out.println("___________________________________________________________");
            System.out.println("Do you wanna continue calculating? Or are you tired of maths? \nENTER your entry values to calculate with or ENTER \" stahp! \" to stahp the program");
            System.out.print("ENTRY : ");
            String InputString = InputStringScanner.nextLine();

            //exit condition and dialog with the  user
            if(InputString.equals("stahp!")){
                System.out.println("Thank you very much for using our wonderful program ✨✨✨");
                System.out.println("Please enter your credit card number here: _ _ _ _ _ _ _ _ _ _ _ _ _ _ _" +
                        "\n \uD83D\uDCB0 \uD83D\uDCB8 \uD83D\uDCB0 \uD83D\uDCB8 \uD83D\uDCB0 \uD83D\uDCB8 \uD83D\uDCB0 \uD83D\uDCB8");
                runProgramm=false;
                break;
            }

                App.InputStringArray=InputString.split(" ");


            //main process of the program with Exceptions
            try {
                SetupBot.sort(InputStringArray);
                SetupBot.showResult();
            }catch(ExceptionCluster e){
                System.out.println("___________________________________________________________");
                System.out.println((char)27 + "[34m"+e.getErrorType()+(char)27 + "[0m");
                System.out.println((char)27 + "[31m"+e.getMessage()+(char)27 + "[0m");
            }
            //returning to starting values
            SetupBot.currentIndex=0;
            SetupBot.failed=false;
            SetupBot.reading=true;
            SetupBot.preOperatorBuffer=new StringBuffer("");
            App.calculationNumbersStack=new Stack<>();
        }
        InputStringScanner.close();

        //TODO unity tests for sort and isOperator methods in SetupCalculator class


    }
}