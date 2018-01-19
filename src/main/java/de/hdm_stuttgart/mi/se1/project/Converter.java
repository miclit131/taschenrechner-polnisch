package de.hdm_stuttgart.mi.se1.project;

import de.hdm_stuttgart.mi.se1.exceptions.*;

public class Converter {
    /**
     * @param undefinedNumber parses the <String>undefinedNumber</String> and builds up
     *                        a double value by decimal places
     * @return <Double>undefinedNumber converted to double</Double>
     * @throws ExceptionCluster throws exception if pi or e Literal are used wrong
     *                          or a NumberStructureException occurs
     */
    // method for tracking Literals such as binary hexa or 2E10 -> JavaDOc
    public static double convertLiterals(String undefinedNumber) throws ExceptionCluster {

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
                            errorCalls.append("\nCharacter:'-' at Index ").append(i).append(" of ").append(undefinedNumber).append(" can not be resolved ").append("\n>>||only use '-' in front of a number||");
                        }
                        break;
                    case '.':
                        if (i != decimalLiteralIndex) {
                            errorCalls.append("\nCharacter:'.' at Index ").append(i).append(" of ").append(undefinedNumber).append(" is an Additional decimal Literal ").append("\n>>||each number can only have one decimal Literal||");
                        }
                        break;
                    case ',':
                        if (i != decimalLiteralIndex) {
                            errorCalls.append("\nCharacter:',' at Index ").append(i).append(" of ").append(undefinedNumber).append(" is an Additional decimal Literal").append("\n>>||each number can only have one decimal Literal||");
                        }
                        break;
                    case 'e':
                        // if (e at position 1 and E Literal at position 2
                        // ||e at position 2,no numbers have been evaluated until e, e is the last position, first position is not 0 )
                        if (i == 0 && exponentialLiteralIndex == 1 || i == 1 && numberValue == 0 && i == exponentialLiteralIndex - 1 && !(checkIfIndexIs(0, '0', undefinedNumber))) {
                            numberValue = numberValue + Math.E;
                        } else {
                            throw new LiteralAbuseException("\nCharacter:'e' at Index " + i + " of " + undefinedNumber + " is a literal for the euler number" +
                                    "\n>>||the euler number is a Value itself and can not be a Part of a number||");
                        }
                        break;

                    case 'p':
                        if (!checkIfIndexIs((i + 1), 'i', undefinedNumber)) {
                            errorCalls.append("\nCharacter:").append(undefinedNumber.charAt(i)).append(" at Index ").append(i).append(" of ").append(undefinedNumber).append(" is an unknown symbol ").append("\n>>||please, only enter the allowed characters (0-9 , e , E , ',' , '.' , -)||").append("\n  ||for numbers or hexadecimal/binary numbers                              ||");
                        } else if (i == 0 && exponentialLiteralIndex == 2 || i == 1 && numberValue == 0 && i + 1 == exponentialLiteralIndex - 1 && !(checkIfIndexIs(0, '0', undefinedNumber))) {
                            numberValue = numberValue + Math.PI;
                            i++;
                        } else {
                            throw new LiteralAbuseException("\nCharacter:'pi' in " + undefinedNumber + " is a literal for the circular number" +
                                    "\n>>||the circular number is a value itself and can not be a Part of a number||");
                        }
                        break;
                    default:
                        errorCalls.append("\nCharacter:").append(undefinedNumber.charAt(i)).append(" at Index ").append(i).append(" of ").append(undefinedNumber).append(" is an unknown symbol ").append("\n>>||please, only enter the allowed characters (0-9 , e , E , ',' , '.' , -)||").append("\n  ||for numbers or hexadecimal/binary numbers                              ||");
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
                        errorCalls.append("\nCharacter:'-' at Index ").append(i).append(" of ").append(undefinedNumber).append(" can not be resolved").append("\n>>||only use '-' in front of a number||");
                        break;
                    default:
                        errorCalls.append("\nCharacter: ").append(undefinedNumber.charAt(i)).append(" at Index ").append(i).append(" of ").append(undefinedNumber).append(" can not be resolved ").append("\n>>||only use 0 or 1 within binary numbers||");
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
                        errorCalls.append("\nCharacter: ").append(undefinedNumber.charAt(i)).append(" at Index ").append(i).append(" of ").append(undefinedNumber).append(" can not be resolved ").append("\n>>||only use 0 - F within binary numbers||");
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
                            errorCalls.append("\nCharacter:'-' at Index ").append(i).append(" of ").append(undefinedNumber).append(" can not be resolved").append("\n>>||only use '-' in front of a number||");
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
                        errorCalls.append("\nCharacter: ").append(undefinedNumber.charAt(i)).append(" of ").append(undefinedNumber).append(" can not be resolved").append("\n>>||please,only use natural numbers as exponent||");
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
            return Double.parseDouble(preparedNumber.toString());
        }
    }


    /**
     * Helper method used to check two characters in sequence no matter if the second character index
     * is outside of the String
     *
     * @param index           The index that needs to be checked
     * @param character       The character that is looked for at the indec
     * @param undefinedNumber String in which the character is looked for
     * @return Boolean which tells if at the character is found at a specific index
     * true when it is the character and false when it was not found or the index is
     * out of range of the given string
     */
    static private boolean checkIfIndexIs(int index, char character, String undefinedNumber) {
        try {
            char unclarifiedCharacter = undefinedNumber.charAt(index);
            return unclarifiedCharacter == character;
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }
    /**
     *
     * @param insecureString Removing all blanks
     * @return secureInput changed into an String[] after being checked by alertQuit
     * @throws ExceptionCluster EmptyEntryException thrown by splitSafe when
     *                          the entry was empty or filled  with blanks.
     *                          Throws the FoundQuitException of splitSafe.
     */
    static public String[] splitSafe(String insecureString) throws ExceptionCluster {
        String[] unsecureStringArray = insecureString.split(" ");
        if (unsecureStringArray.length != 0 && !insecureString.equals("")) {
            StringBuilder secureInput = new StringBuilder("");
            for (String i : unsecureStringArray) {
                if (!i.equals("")) {
                    secureInput.append(i).append(" ");
                }
            }
            alertQuit(secureInput.toString());
            return secureInput.toString().split(" ");
        } else {
            throw new EmptyEntryException();
        }
    }

    /**
     * Looking for the word "quit" in the secureInputString
     * and triggers an exception when it is found.
     * @param secureInputString Looking for the word
     *                          "quit" in the parameter
     *
     * @throws FoundQuitException When this exception is thrown the
     *                            program will start a request if
     *                            he really wants to quit.
     */

    static private void alertQuit(String secureInputString) throws FoundQuitException {
        int firstQIndex = secureInputString.indexOf('q');
        int endOfString = secureInputString.length() - 1;

        while (firstQIndex < endOfString) {
            if (firstQIndex != -1) {
                if (checkIfIndexIs(firstQIndex + 1, 'u', secureInputString)
                        && checkIfIndexIs(firstQIndex + 2, 'i', secureInputString)
                        && checkIfIndexIs(firstQIndex + 3, 't', secureInputString)
                        ) {
                    throw new FoundQuitException();
                } else {
                    firstQIndex = secureInputString.indexOf('q', firstQIndex + 1);
                }
            } else {
                break;
            }
        }
    }
}

