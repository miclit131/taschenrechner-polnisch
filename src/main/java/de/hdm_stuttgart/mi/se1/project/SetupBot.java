package de.hdm_stuttgart.mi.se1.project;

import de.hdm_stuttgart.mi.se1.exceptions.ExceptionCluster;
import de.hdm_stuttgart.mi.se1.exceptions.*;

import java.util.Arrays;

public class SetupBot {
    public static StringBuffer preOperatorBuffer = new StringBuffer("");
    public static int currentIndex = 0;
    public static boolean reading = true;
    public static boolean failed = false;


    static public void sort(String[] unsortedStringSplit) throws ExceptionCluster {
        while (reading) {

            for (int i = currentIndex; i < unsortedStringSplit.length; i++) {
                if (isOperator(unsortedStringSplit[i])) {
                    SetupBot.preOperatorBuffer.append(unsortedStringSplit[i] + " ");
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

                    App.calculationNumbersStack.push(Double.parseDouble(SetupBot.convertLiterals(unsortedStringSplit[i])));

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
        if (!failed) {
            App.operatorArray = SetupBot.preOperatorBuffer.toString().split(" ");
            SetupBot.solve(unsortedStringSplit);
        }
    }


    private static void solve(String[] unsortedStringSplit) throws ExceptionCluster {
        for (int i = 0; i < App.operatorArray.length; i++) {
            if (!App.calculationNumbersStack.empty()) {
                activateBinaryOperator(App.operatorArray[i]);
                activateUnaryOperator(App.operatorArray[i]);
            } else {
                throw new JustOperatorsException();
            }
        }
        SetupBot.preOperatorBuffer = new StringBuffer("");
        App.operatorArray = null;
        reading = true;

        if (SetupBot.currentIndex == unsortedStringSplit.length && App.calculationNumbersStack.size() != 0) {
            App.result = App.calculationNumbersStack.peek();
            reading = false;
        } else if (SetupBot.currentIndex != unsortedStringSplit.length) {
            SetupBot.sort(unsortedStringSplit);
        } else if (SetupBot.currentIndex == unsortedStringSplit.length && App.calculationNumbersStack.size() == 0) {
            throw new EmptyEntryException();
        }
    }

    /**
     * @throws InputUnbalancedException there are too many numbers
     *                                  in the calculation stack before the result can be printed
     */
    public static void showResult() throws InputUnbalancedException {
        if (!SetupBot.failed) {
            if (App.calculationNumbersStack.size() == 1) {
                System.out.println("RESULT: " + (char) 27 + "[34m" + App.result + (char) 27 + "[0m" + "\n");
            } else if (App.calculationNumbersStack.size() > 1) {
                throw new InputUnbalancedException();
            }
        }
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
     * @param undefinedCalculationOperation compares parameter with all binary switch cases
     *                                      does nothing if undefinedCalculationOperation is not a binary operator
     *                                      activates the binary MathOperation if is found
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

    //TODO JavaDoc and include method into the programm , only use this method if string includes special signs otherwise performance ist damaged
    //TODO use if before activating this method
    // method for tracking Literals such as binary hexa or 2E10 -> JavaDOc
    private static String convertLiterals(String undefinedNumber) throws ExceptionCluster {

        StringBuilder preparedNumber = new StringBuilder("");
        StringBuilder errorCalls = new StringBuilder("");

        double numberValue = 0;
        boolean positive = true;
        double exponentialNumber = 0;
        boolean positiveExponent = true;

        int exponentialLiteralIndex;
        if (undefinedNumber.lastIndexOf('E') == -1) {
            exponentialLiteralIndex = undefinedNumber.length();
        } else {
            exponentialLiteralIndex = undefinedNumber.lastIndexOf('E');
        }

        int decimalLiteralIndex;
        if (undefinedNumber.indexOf(',') >= 0) {
            decimalLiteralIndex = undefinedNumber.indexOf(',');
        } else if (undefinedNumber.indexOf('.') >= 0) {
            decimalLiteralIndex = undefinedNumber.indexOf('.');
        } else if (exponentialLiteralIndex == -1) {
            decimalLiteralIndex = undefinedNumber.length() - 1;
        } else {
            decimalLiteralIndex = exponentialLiteralIndex;
        }

//in case it is a decimal number
        if (!(checkIfIndexIs(0, '0', undefinedNumber) && checkIfIndexIs(1, 'b', undefinedNumber))
                && !(checkIfIndexIs(0, '0', undefinedNumber) && checkIfIndexIs(1, 'x', undefinedNumber))
                && !(checkIfIndexIs(1, '0', undefinedNumber) && checkIfIndexIs(2, 'b', undefinedNumber))
                && !(checkIfIndexIs(1, '0', undefinedNumber) && checkIfIndexIs(2, 'x', undefinedNumber))
                && !(checkIfIndexIs(0, '0', undefinedNumber) && checkIfIndexIs(1, 'B', undefinedNumber))
                && !(checkIfIndexIs(0, '0', undefinedNumber) && checkIfIndexIs(1, 'X', undefinedNumber))
                && !(checkIfIndexIs(1, '0', undefinedNumber) && checkIfIndexIs(2, 'B', undefinedNumber))
                && !(checkIfIndexIs(1, '0', undefinedNumber) && checkIfIndexIs(2, 'X', undefinedNumber))) {

            for (int i = 0; i < exponentialLiteralIndex; i++) {
                int referencePoint = decimalLiteralIndex - 1;
                if (i > decimalLiteralIndex) {
                    referencePoint = decimalLiteralIndex;
                }
                switch (undefinedNumber.charAt(i)) {
                    case '1':
                        numberValue = numberValue + 1 * Math.pow(10, (referencePoint - i));
                        break;
                    case '2':
                        numberValue = numberValue + 2 * Math.pow(10, (referencePoint - i));
                        break;
                    case '3':
                        numberValue = numberValue + 3 * Math.pow(10, (referencePoint - i));
                        break;
                    case '4':
                        numberValue = numberValue + 4 * Math.pow(10, (referencePoint - i));
                        break;
                    case '5':
                        numberValue = numberValue + 5 * Math.pow(10, (referencePoint - i));
                        break;
                    case '6':
                        numberValue = numberValue + 6 * Math.pow(10, (referencePoint - i));
                        break;
                    case '7':
                        numberValue = numberValue + 7 * Math.pow(10, (referencePoint - i));
                        break;
                    case '8':
                        numberValue = numberValue + 8 * Math.pow(10, (referencePoint - i));
                        break;
                    case '9':
                        numberValue = numberValue + 9 * Math.pow(10, (referencePoint - i));
                        break;
                    case '0':
                        break;
                    case '-':
                        if (i == 0) {
                            positive = false;
                        } else {
                            errorCalls.append("\nCharacter:'-' at Index " + i + " of " + undefinedNumber + " can not be resolved " +
                                    "\n>>||only use '-' in front of a number||");
                        }
                        break;
                    case '.':
                        if (i != decimalLiteralIndex) {
                            errorCalls.append("\nCharacter:'.' at Index " + i + " of " + undefinedNumber + " is an Additional decimal Literal " +
                                    "\n>>||each number can only have one decimal Literal||");
                        }
                        break;
                    case ',':
                        if (i != decimalLiteralIndex) {
                            errorCalls.append("\nCharacter:',' at Index " + i + " of " + undefinedNumber + " is an Additional decimal Literal" +
                                    "\n>>||each number can only have one decimal Literal||");
                        }
                        break;
                    case 'e':
                        // if (e at position 1 and E Literal at position 2
                        // ||e at position 2,no numbers have been evaluated until e, e is the last position, first position is not 0 )
                        if (i == 0 && exponentialLiteralIndex == 1 || i == 1 && numberValue == 0 && i == exponentialLiteralIndex - 1 && !(checkIfIndexIs(0, '0', undefinedNumber))) {
                            numberValue = numberValue + Math.E;
                        } else {
                            //TODO what is when "e" stands within of a word which is  wrong as a whole
                            throw new LiteralAbuseException("\nCharacter:'e' at Index " + i + " of " + undefinedNumber + " is a literal for the euler number" +
                                    "\n>>||the euler number is a Value itself and can not be a Part of a number||");
                        }
                        break;
                    //TODO case pi use LiteralAbuseException if not first to or 2nd and 3rd character are p i
                    //TODO if pi use Math.PI , almost the same if conditions as the 'e' Literal
                    //TODO -pi and piE2 should be possible refer e Literal
                    case 'p':
                        if (!checkIfIndexIs((i + 1), 'i', undefinedNumber)) {
                            errorCalls.append("\nCharacter:" + undefinedNumber.charAt(i) + " at Index " + i + " of " + undefinedNumber + " is an unknown symbol " +
                                    "\n>>||please, only enter the allowed characters (0-9 , e , E , ',' , '.' , -)||" +
                                    "\n  ||for numbers or hexadecimal/binary numbers                              ||");
                        } else if (i == 0 && exponentialLiteralIndex == 2 || i == 1 && numberValue == 0 && i + 1 == exponentialLiteralIndex - 1 && !(checkIfIndexIs(0, '0', undefinedNumber))) {
                            numberValue = numberValue + Math.PI;
                            i++;
                        } else {
                            throw new LiteralAbuseException("\nCharacter:'pi' in " + undefinedNumber + " is a literal for the circular number" +
                                    "\n>>||the circular number is a value itself and can not be a Part of a number||");
                        }
                        break;
                    default:
                        errorCalls.append("\nCharacter:" + undefinedNumber.charAt(i) + " at Index " + i + " of " + undefinedNumber + " is an unknown symbol " +
                                "\n>>||please, only enter the allowed characters (0-9 , e , E , ',' , '.' , -)||" +
                                "\n  ||for numbers or hexadecimal/binary numbers                              ||");
                        break;
                }
            }
//case  if binary number
        } else if (checkIfIndexIs(0, '0', undefinedNumber) && checkIfIndexIs(1, 'b', undefinedNumber)
                || checkIfIndexIs(1, '0', undefinedNumber) && checkIfIndexIs(2, 'b', undefinedNumber)
                || checkIfIndexIs(0, '0', undefinedNumber) && checkIfIndexIs(1, 'B', undefinedNumber)
                || checkIfIndexIs(1, '0', undefinedNumber) && checkIfIndexIs(2, 'B', undefinedNumber)) {
            if (undefinedNumber.charAt(0) == '-') {
                positive = false;
            }
            int referencePoint = decimalLiteralIndex - 1;
            for (int i = undefinedNumber.indexOf('0') + 2; i < exponentialLiteralIndex; i++) {
                switch (undefinedNumber.charAt(i)) {
                    case '0':
                        break;
                    case '1':
                        numberValue = numberValue + Math.pow(2, referencePoint - i);
                        break;
                    case '-':
                        errorCalls.append("\nCharacter:'-' at Index " + i + " of " + undefinedNumber + " can not be resolved" +
                                "\n>>||only use '-' in front of a number||");
                        break;
                    default:
                        errorCalls.append("\nCharacter: " + undefinedNumber.charAt(i) + " at Index " + i + " of " + undefinedNumber + " can not be resolved " +
                                "\n>>||only use 0 or 1 within binary numbers||");
                        break;
                }
            }

//case if hexadezimal  number
        } else if (checkIfIndexIs(0, '0', undefinedNumber) && checkIfIndexIs(1, 'x', undefinedNumber)
                || checkIfIndexIs(1, '0', undefinedNumber) && checkIfIndexIs(2, 'x', undefinedNumber)
                || checkIfIndexIs(0, '0', undefinedNumber) && checkIfIndexIs(1, 'X', undefinedNumber)
                || checkIfIndexIs(1, '0', undefinedNumber) && checkIfIndexIs(2, 'X', undefinedNumber)) {
            // int lastIndexOfHexa = exponentialLiteralIndex - 1;
            if (undefinedNumber.charAt(0) == '-') {
                positive = false;
            }
          /*  if (exponentialLiteralIndex == undefinedNumber.length() - 1) {
                lastIndexOfHexa = undefinedNumber.length() - 1;
            }*/
            //  lastIndexOfHexa=undefinedNumber.length()-1;
            int referencePoint = undefinedNumber.length() - 1;

            for (int i = undefinedNumber.indexOf('0') + 2; i <= referencePoint; i++) {
                switch (undefinedNumber.charAt(i)) {
                    case '0':
                        break;
                    case '1':
                        numberValue = numberValue + 1 * Math.pow(16, referencePoint - i);
                        break;
                    case '2':
                        numberValue = numberValue + 2 * Math.pow(16, referencePoint - i);
                        break;
                    case '3':
                        numberValue = numberValue + 3 * Math.pow(16, referencePoint - i);
                        break;
                    case '4':
                        numberValue = numberValue + 4 * Math.pow(16, referencePoint - i);
                        break;
                    case '5':
                        numberValue = numberValue + 5 * Math.pow(16, referencePoint - i);
                        break;
                    case '6':
                        numberValue = numberValue + 6 * Math.pow(16, referencePoint - i);
                        break;
                    case '7':
                        numberValue = numberValue + 7 * Math.pow(16, referencePoint - i);
                        break;
                    case '8':
                        numberValue = numberValue + 8 * Math.pow(16, referencePoint - i);
                        break;
                    case '9':
                        numberValue = numberValue + 9 * Math.pow(16, referencePoint - i);
                        break;
                    case 'A':
                    case 'a':
                        numberValue = numberValue + 10 * Math.pow(16, referencePoint - i);
                        break;
                    case 'B':
                    case 'b':
                        numberValue = numberValue + 11 * Math.pow(16, referencePoint - i);
                        break;
                    case 'C':
                    case 'c':
                        numberValue = numberValue + 12 * Math.pow(16, referencePoint - i);
                        break;
                    case 'D':
                    case 'd':
                        numberValue = numberValue + 13 * Math.pow(16, referencePoint - i);
                        break;
                    case 'E':
                    case 'e':
                        numberValue = numberValue + 14 * Math.pow(16, referencePoint - i);
                        break;
                    case 'F':
                    case 'f':
                        numberValue = numberValue + 15 * Math.pow(16, referencePoint - i);
                        break;
                    default:
                        errorCalls.append("\nCharacter: " + undefinedNumber.charAt(i) + " at Index " + i + " of " + undefinedNumber + " can not be resolved " +
                                "\n>>||only use 0 - F within binary numbers||");
                        break;
                }
            }
        }
// caculating exponent if exists
        if (exponentialLiteralIndex != -1 && exponentialLiteralIndex != undefinedNumber.length() - 1) {
            for (int i = exponentialLiteralIndex + 1; i < undefinedNumber.length(); i++) {
                switch (undefinedNumber.charAt(i)) {
                    case '-':
                        if (i == exponentialLiteralIndex + 1) {
                            positiveExponent = false;
                        } else {
                            errorCalls.append("\nCharacter:'-' at Index " + i + " of " + undefinedNumber + " can not be resolved" +
                                    "\n>>||only use '-' in front of a number||");
                        }
                        break;
                    case '1':
                        exponentialNumber = exponentialNumber + 1 * Math.pow(10, (undefinedNumber.length() - 1 - i));
                        break;
                    case '2':
                        exponentialNumber = exponentialNumber + 2 * Math.pow(10, (undefinedNumber.length() - 1 - i));
                        break;
                    case '3':
                        exponentialNumber = exponentialNumber + 3 * Math.pow(10, (undefinedNumber.length() - 1 - i));
                        break;
                    case '4':
                        exponentialNumber = exponentialNumber + 4 * Math.pow(10, (undefinedNumber.length() - 1 - i));
                        break;
                    case '5':
                        exponentialNumber = exponentialNumber + 5 * Math.pow(10, (undefinedNumber.length() - 1 - i));
                        break;
                    case '6':
                        exponentialNumber = exponentialNumber + 6 * Math.pow(10, (undefinedNumber.length() - 1 - i));
                        break;
                    case '7':
                        exponentialNumber = exponentialNumber + 7 * Math.pow(10, (undefinedNumber.length() - 1 - i));
                        break;
                    case '8':
                        exponentialNumber = exponentialNumber + 8 * Math.pow(10, (undefinedNumber.length() - 1 - i));
                        break;
                    case '9':
                        exponentialNumber = exponentialNumber + 9 * Math.pow(10, (undefinedNumber.length() - 1 - i));
                        break;
                    case '0':
                        break;
                    default:
                        errorCalls.append("\nCharacter: " + undefinedNumber.charAt(i) + " of " + undefinedNumber + " can not be resolved" +
                                "\n>>||please,only use natural numbers as exponent||");
                        break;
                }
            }
        }
        if (positiveExponent && positive) {
            preparedNumber.append(numberValue * Math.pow(10, exponentialNumber));
        }
        if (!positiveExponent && positive) {
            preparedNumber.append(numberValue * Math.pow(10, (-1) * exponentialNumber));
        }
        if (positiveExponent && !positive) {
            preparedNumber.append((-1) * numberValue * Math.pow(10, exponentialNumber));
        }
        if (!positiveExponent && !positive) {
            preparedNumber.append((-1) * numberValue * Math.pow(10, (-1) * exponentialNumber));
        }

        //preparedNumber.append(errorCalls);
        if (errorCalls.length() != 0) {
            throw new NumberStructureException(errorCalls.toString());
        } else {
            return preparedNumber.toString();
        }
    }

    static private boolean checkIfIndexIs(int index, char character, String undefinedNumber) {
        try {
            char unclarifiedCharacter = undefinedNumber.charAt(index);
            return unclarifiedCharacter == character;
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    static public String[] splitSafe(String insecureString) throws ExceptionCluster {
        String[] unsecureStringArray = insecureString.split(" ");
        if (unsecureStringArray.length != 0 && !insecureString.equals("")) {
            StringBuilder secureInput = new StringBuilder("");
            for (String i : unsecureStringArray) {
                if (!i.equals("")) {
                    secureInput.append(i + " ");
                }
            }
            alertQuit(secureInput.toString());
            return secureInput.toString().split(" ");
        } else {
            throw new EmptyEntryException();

        }
    }

    static private void alertQuit(String secureInputString) {
        int firstQIndex = secureInputString.indexOf('q');
        int endOfString = secureInputString.length() - 1;

        while (firstQIndex < endOfString) {
            if (secureInputString.indexOf('q') != -1) {
                if (checkIfIndexIs(firstQIndex + 1, 'u', secureInputString)
                        && checkIfIndexIs(firstQIndex + 2, 'i', secureInputString)
                        && checkIfIndexIs(firstQIndex + 3, 't', secureInputString)
                        ) {
                    throw new FoundQuitException();
                } else {
                    firstQIndex = secureInputString.indexOf('q', firstQIndex+1);
                }
            } else {
                break;
            }
        }
    }

}

