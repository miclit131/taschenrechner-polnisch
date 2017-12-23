package de.hdm_stuttgart.mi.se1;

import java.util.Stack;

public class SetupBot {

    /**
    *Main setup method for the calculator
    *
    * @param unsortedString
    *
    * splits the string internal into an String[]
    * then filters it:
    * If(String=!operator){
    * puts the String into an internal Stack<String> prePushToDestinationStack
    *     }else{
    * puts the String into a Stringbuffer,which gets converted to a String and
    * the splitted string becomes an array
    * }
    *
    * prePushToDestinationStack will be read out
    *and put upside down into the stack of the main class (static inputValuesForCalculation = new Stack<>();)
    *
    * the array which is made out of the stringBuffer
    * will be set as the class attribute of the main class (static String[] calculationOperators;)
    */

    static void stringFilterOperatorsIntoArrayAndEverythingElseIntoStack(String unsortedString) {
        String[] unsortedStringSplitted = unsortedString.split(" ");
        StringBuffer operatorsForCalculationBuffer = new StringBuffer("");
        Stack<String> prePushToDestinationStack = new Stack<>();
        int unsortedStringSplittedLength = unsortedStringSplitted.length;

        for (int i = 0; i < unsortedStringSplittedLength; i++) {
            if (isOperator(unsortedStringSplitted[i])) {
                operatorsForCalculationBuffer.append(unsortedStringSplitted[i] + " ");
            } else {
                prePushToDestinationStack.push(unsortedStringSplitted[i]);
            }
        }

        while (!prePushToDestinationStack.empty()) {
            App.inputValuesForCalculation.push(prePushToDestinationStack.pop());
        }

        String operatorsForCalculation = operatorsForCalculationBuffer.toString();
        App.calculationOperators = operatorsForCalculation.split(" ");


    }

    /**
    * Helping Method for the filter-process
    *
    * @param undefinedString
    *
    * takes input String into a Switch
    * and compares it with the defined Operators
    *
    * @return boolean
    *
    * boolean==true if String is a defined operator
    * boolean==false if String is not a defined operator
     */

    public static boolean isOperator(String undefinedString) {
        switch (undefinedString) {
            case "+":
            case "-":
            case "*":
            case "/":
            case "pow":
            case "~":

            case "sin":
            case "cos":
            case "tan":
            case "exp":
            case "ln":
            case "sqrt":
                return true;

            default:
                return false;


        }
    }
}
