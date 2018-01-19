package de.hdm_stuttgart.mi.se1.project;
import de.hdm_stuttgart.mi.se1.exceptions.EmptyEntryException;
import de.hdm_stuttgart.mi.se1.exceptions.ExceptionCluster;
import de.hdm_stuttgart.mi.se1.exceptions.FoundQuitException;

import java.util.Scanner;
import java.util.Stack;


/**
 * main class
 * Program gets input values through a scanner
 * which gets changed into a fitting data type
 * and then given to a parse method which activates the  calculator.
 * After the calculator has finish, the program checks
 * if there were any complication during the process
 */
public class App {

    private static boolean runProgram = true;
    public static String[] InputStringArray;

    public static void main(String[] args) {

        //Scanner for the main source of the InputStream / Userinterface
        final Scanner InputStringScanner = new Scanner(System.in);

        //starting text first "frame" of the program
        System.out.println("Welcome to the best RPN calculator in the whoooole world!!! \uD83C\uDF0D \uD83C\uDF38"
                +"\nHave fun playing with numbers little nerd. \uD83C\uDFAE"
                +"\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        while(runProgram) {
            System.out.println("___________________________________________________________"
                    +"\nDo you want to make calculations? Or are you tired of maths? "
                    +"\nEnter your entry values to calculate with or ENTER \" quit \" to exit the program.");
            System.out.print("ENTRY : ");
         final String InputString = InputStringScanner.nextLine();
            //exit condition and dialog with the  user
            if(InputString.equals("quit")) {
                runProgram = false;
                break;
            }



            //main process of the program with Exceptions
            try {
                App.InputStringArray = Converter.splitSafe(InputString);
                Calculator.sort(InputStringArray);
                Calculator.showResult();
            }catch(FoundQuitException e){
                System.out.println("___________________________________________________________\n\n"
                        +(char)27 + "[34m"+e.getErrorType()+(char)27 + "[0m\n"
                        +(char)27 + "[32m"+e.getMessage()+(char)27 + "[0m");
                boolean ask=true;
                while(ask) {
                    System.out.print("Please type "+(char)27 + "[33m"+ "yes "+(char)27 + "[0m"+"or "
                            +(char)27 + "[31m"+"no"+(char)27 + "[0m"+" , do u want to quit the program?" +
                            "\nANSWER:");
                    String decision = InputStringScanner.nextLine();
                    switch (decision) {
                        case "yes": runProgram =false;
                                    ask=false;
                                    break;
                        case "no": ask=false;
                        break;
                        default:
                            System.out.print("Please type "+(char)27 + "[33m"+ "yes "+(char)27 + "[0m"+"or "
                                    +(char)27 + "[31m"+"no"+(char)27 + "[0m"+" , do u want to quit the program?" +
                                    "\nANSWER:");
                            break;
                    }
                }
            }catch(EmptyEntryException e){
                System.out.println("___________________________________________________________\n\n"
                                    +(char)27 + "[34m"+e.getErrorType()+(char)27 + "[0m\n"
                                    +(char)27 + "[32m"+e.getMessage()+(char)27 + "[0m");
            }catch(ExceptionCluster e){
                System.out.println("___________________________________________________________\n\n"
                                    +(char)27 + "[34m"+e.getErrorType()+(char)27 + "[0m\n"
                                    +(char)27 + "[31m"+e.getMessage()+(char)27 + "[0m");

            }

            //setting starting values to default
            Calculator.currentIndex=0;
            Calculator.reading=true;
            Calculator.preOperatorBuffer=new StringBuffer("");
            Calculator.calculationNumbersStack=new Stack<>();
        }

        //closing the scanner and printing the exit message
        InputStringScanner.close();
        System.out.println("___________________________________________________________" +
                "\nThank you very much for using our wonderful program ✨✨✨" +
                "\nPlease enter your credit card number here: _ _ _ _ _ _ _ _ _" +
                (char) 27 + "[32m" + "\n \uD83D\uDCB0 \uD83D\uDCB8 \uD83D\uDCB0 \uD83D\uDCB8 \uD83D\uDCB0 \uD83D\uDCB8 \uD83D\uDCB0 \uD83D\uDCB8" + (char) 27 + "[0m");

    }
}