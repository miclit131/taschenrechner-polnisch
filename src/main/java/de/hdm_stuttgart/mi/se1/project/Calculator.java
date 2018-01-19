package de.hdm_stuttgart.mi.se1.project;

import de.hdm_stuttgart.mi.se1.exceptions.*;

import java.util.Stack;

public class Calculator {

    public static Stack<Double> calculationNumbersStack = new Stack<>();
    private static String[] operatorArray;
    public static double result;
    /**
     * CurrentIndex is used, by the methods sort and solve,
     * to communicate how many indexes of the input are evaluated
     * and control if the sort / solve process is finished.
     */
    public static int currentIndex = 0;
    public static boolean reading = true;
    public static StringBuffer preOperatorBuffer = new StringBuffer("");

    /** The sort method analyzes the input Array until the first set of operators
     * and sorts them into 2 data structures (operators to a String[] and number
     * into a stack<double>).
     *
     * Numbers get converted from String to a double number value by convertLiterals method.
     *
     * When the first part of the input is sorted the sort method activates solve,
     * which calculates the current sorted part.
     *
     * @param unsortedStringSplit Input which gets analyzed and calculated
     * @throws ExceptionCluster Super class of the personal exceptions,
     *                          whenever a self-written exception gets thrown,
     *                          it'll be passed through to the main.
     */
    static public void sort(String[] unsortedStringSplit) throws ExceptionCluster {
        while (reading) {

            for (int i = currentIndex; i < unsortedStringSplit.length; i++) {
                if (isOperator(unsortedStringSplit[i])) {
                    Calculator.preOperatorBuffer.append(unsortedStringSplit[i]).append(" ");
                    for (int j = i + 1; j < unsortedStringSplit.length; j++) {
                        if (!isOperator(unsortedStringSplit[j])) {
                            reading = false;
                            break;
                        }
                    }
                    currentIndex++;
                    if (i == unsortedStringSplit.length - 1) {
                        reading = false;
                    }

                } else {

                    Calculator.calculationNumbersStack.push(Converter.convertLiterals(unsortedStringSplit[i]));

                    currentIndex++;
                    if (i == unsortedStringSplit.length - 1) {
                        reading = false;
                    }
                }
                if (!reading) {
                    break;
                }
            }
        }
            Calculator.operatorArray = Calculator.preOperatorBuffer.toString().split(" ");
            solve(unsortedStringSplit);
    }

    /**
     * solve activates operators one after another or throws an exception.
     * It is guaranteed that activateBinaryOperators and activateUnaryOperator only get operators as input.
     * The last result will be still in the memory location for numbers.
     * After the current part of the user-entry is calculated,solve puts sort into reading mode
     * and tells sort thereby to analyze the next part of the user-entry or sets the final result
     * that can be read out and finishes the sort-solve process.
     *
     *
     * @param unsortedStringSplit The parameter is passed through sort to solve to allow solve
     *                            to fulfil the constructor of sort. At the same time the parameter
     *                            allows to get specific information about the Array in the process.
     *                            solve is only used by the sort method.
     * @throws ExceptionCluster Solve passes through the OperatorLimitException in activateBinaryOperator
     *                          by the binary operators in MathOperation.class.
     *                          Solve itself throws JustOperatorException when there were no numbers
     *                          in the currently analyzed part.
     */
    private static void solve(String[] unsortedStringSplit) throws ExceptionCluster {
        for (int i = 0; i < Calculator.operatorArray.length; i++) {
            if (!Calculator.calculationNumbersStack.empty()) {
                activateBinaryOperator(Calculator.operatorArray[i]);
                activateUnaryOperator(Calculator.operatorArray[i]);
            } else {
                throw new JustOperatorsException();
            }
        }
        Calculator.preOperatorBuffer = new StringBuffer("");
        Calculator.operatorArray = null;
        reading = true;

        if (Calculator.currentIndex == unsortedStringSplit.length && Calculator.calculationNumbersStack.size() != 0) {
            Calculator.result = Calculator.calculationNumbersStack.peek();
            reading = false;
        } else if (Calculator.currentIndex != unsortedStringSplit.length) {
            Calculator.sort(unsortedStringSplit);
        }
    }

    /**
     * Before showing the result the method checks if there was only 1 number in the memory location
     * and throws an exception if it was more than one double value that could ve been saved in the result
     * at the end of the calculation.
     *
     * @throws InputUnbalancedException There are too many numbers
     *                                  in the calculation stack before the result can be printed.
     */
    public static void showResult() throws InputUnbalancedException {
            if (Calculator.calculationNumbersStack.size() == 1) {
                System.out.println("RESULT: " + (char) 27 + "[34m" + Calculator.result + (char) 27 + "[0m" + "\n");
            } else if (Calculator.calculationNumbersStack.size() > 1) {
                throw new InputUnbalancedException();
            }
        }

    /**
     * Helping Method for the filter-process
     *
     * @param undefinedString Takes input String into a Switch
     *                        and compares it with the defined Operators.
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
     * Activates binary operators in MathOperation
     *
     * @param undefinedCalculationOperation Compares parameter with all binary switch cases
     *                                      does nothing if undefinedCalculationOperation is not a binary operator
     *                                      activates the binary MathOperation if is found.
     */
    private static void activateBinaryOperator(String undefinedCalculationOperation) throws ExceptionCluster {
        switch (undefinedCalculationOperation) {
            case "+":
                MathOperation.add();
                break;
            case "-":
                MathOperation.subtract();
                break;
            case "*":
                MathOperation.multiply();
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
     * Activates unary operators defined in MathOperation
     *
     * @param undefinedCalculationOperation compares parameter with all unary switch cases
     *                                      does nothing if undefinedCalculationOperation is not a unary operator
     *                                      activates the unary MathOperation if is found
     */
    private static void activateUnaryOperator(String undefinedCalculationOperation) {
        switch (undefinedCalculationOperation) {
            case "~":
                MathOperation.unarySubtraction();
                break;
            case "sin":
                MathOperation.sine();
                break;
            case "cos":
                MathOperation.cosine();
                break;
            case "tan":
                MathOperation.tangent();
                break;
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

