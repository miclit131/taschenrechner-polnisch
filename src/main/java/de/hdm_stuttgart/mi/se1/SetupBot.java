package de.hdm_stuttgart.mi.se1;

import java.util.Stack;

public class SetupBot {

    /**
     * Main setup method for the calculator
     *
     * @param unsortedString splits the string internal into an String[]
     *                       then filters it:
     *                       If(String=!operator){
     *                       tries to convert string to double,
     *                       puts the double into an internal Stack<Double> prePushToDestinationStack
     *                       }else{
     *                       puts the String into a Stringbuffer,which gets converted to a String and
     *                       the splitted string becomes an array
     *                       }
     *                       <p>
     *                       prePushToDestinationStack will be read out
     *                       and put upside down into the stack of the main class (static inputValuesForCalculation = new Stack<>();)
     *                       <p>
     *                       the array which is made out of the stringBuffer
     *                       will be set as the class attribute of the main class (static String[] calculationOperators;)
     */

    static void stringFilterOperatorsIntoArrayAndEverythingElseIntoStack(String unsortedString) {
        String[] unsortedStringSplitted = unsortedString.split(" ");
        StringBuffer operatorsForCalculationBuffer = new StringBuffer("");
        Stack<Double> prePushToDestinationStack = new Stack<>();
        int unsortedStringSplittedLength = unsortedStringSplitted.length;

        for (int i = 0; i < unsortedStringSplittedLength; i++) {
            if (isOperator(unsortedStringSplitted[i])) {
                operatorsForCalculationBuffer.append(unsortedStringSplitted[i] + " ");
            } else {
                //TODO programm needs  to learn to recon Literals / watch out : https://freedocs.mi.hdm-stuttgart.de/sd1_sect_projectRpnCalculatorFunctionality.html
                try {
                    prePushToDestinationStack.push(Double.parseDouble(unsortedStringSplitted[i]));
                } catch (NumberFormatException e) {
                    //TODO what to do in case of an exception? found a letter instead of a number
                }
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
     * @param undefinedString takes input String into a Switch
     *                        and compares it with the defined Operators
     * @return boolean
     * <p>
     * boolean==true if String is a defined operator
     * boolean==false if String is not a defined operator
     */

    private static boolean isOperator(String undefinedString) {
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

    /**
     * creating internal Integer stackSizeControl
     * with the starting Size of the Stack App.inputValuesforCalculation
     * <p>
     * taking each operation out of the Array App.calculationOperators
     * compares the string of the Array in a switch case and
     * activates the MathOperation according to the Character that has been read out
     * <p>
     * each MathOperation shrinks down the size of the Stack depending on its
     * mathematical context, furthermore when the size of the stack==1
     * the last remaining object is peeked and given out as return value
     * <p>
     * programm will try to shrink the size with the operators
     * if the stackSize is at one it'll only use unary operators
     *
     * @return App.inputValuesForCalculation.peek()
     */
    //TODO error warning if there is still a binary operator left when the size of the stack<double> is at 1
    public static double solve() {
        //int stackSizeControl=App.inputValuesForCalculation.size();
        for (int i = 0; i < App.calculationOperators.length; i++) {
            if (App.inputValuesForCalculation.size() > 1) {
                activateBinaryOperator(App.calculationOperators[i]);
                activateUnaryOperator(App.calculationOperators[i]);
            } else {
                activateUnaryOperator(App.calculationOperators[i]);
            }
        }
        return App.inputValuesForCalculation.peek();
    }

    /**
     * @param undefinedCalculationOperation compares parameter with all binary switch cases
     *                                      does nothing if undefinedCalculationOperation is not a binary operator
     *                                      activates the binary MathOperation if is found
     */
    private static void activateBinaryOperator(String undefinedCalculationOperation) {
        switch (undefinedCalculationOperation) {
            case "+":
                MathOperation.add();
                break;
            case "-":
                MathOperation.substract();
                break;
            case "*":
                MathOperation.mulitply();
                break;
            case "/":
                MathOperation.divide();
                break;
            case "pow":
                MathOperation.pow();
                break;

            default:
                break;

        }
    }

    /**
     * @param undefinedCalculationOperation compares parameter with all unary switch cases
     *                                      does nothing if undefinedCalculationOperation is not a unary operator
     *                                      activates the unary MathOperation if is found
     */
    private static void activateUnaryOperator(String undefinedCalculationOperation) {
        switch (undefinedCalculationOperation) {
            case "~":
                MathOperation.unarySubstraction();
                break;
            case "sin":
                MathOperation.sine();
                break;
            case "cos":
                MathOperation.cosine();
                break;
            case "tan":
                MathOperation.tangent();
            case "exp":
                MathOperation.exponential();
                break;
            case "ln":
                MathOperation.logE();
                break;
            case "sqrt":
                MathOperation.squareRoot();
                break;
            default:
                break;
        }
    }
}
