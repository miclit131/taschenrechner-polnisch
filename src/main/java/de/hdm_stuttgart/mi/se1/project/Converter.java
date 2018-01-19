package de.hdm_stuttgart.mi.se1.project;

import de.hdm_stuttgart.mi.se1.exceptions.*;

public class Converter {
    static private boolean positive = true;
    static private boolean positiveExponent = true;
    // static double exponentialNumber = 0;

    /**
     * @param undefinedNumber parses the <String>undefinedNumber</String> and builds up
     *                        a double value by decimal places
     * @return <Double>undefinedNumber converted to double</Double>
     * @throws ExceptionCluster throws exception if pi or e Literal are used wrong
     *                          or a NumberStructureException occurs
     */
    // method for tracking Literals such as binary hexa or 2E10 -> JavaDOc
    public static double convertLiterals(String undefinedNumber) throws ExceptionCluster {
        double numberValue;
        double exponentialNumber = 0;
        double secureNumber = 0;

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

        boolean zeroIsZero = checkIfIndexIs(0, '0', undefinedNumber);
        boolean oneIsZero = checkIfIndexIs(1, '0', undefinedNumber);
        boolean oneIsB = checkIfIndexIs(1, 'B', undefinedNumber);
        boolean twoIsB = checkIfIndexIs(2, 'B', undefinedNumber);
        boolean oneIsb = checkIfIndexIs(1, 'b', undefinedNumber);
        boolean twoIsb = checkIfIndexIs(2, 'b', undefinedNumber);
        boolean oneIsx = checkIfIndexIs(1, 'x', undefinedNumber);
        boolean twoIsx = checkIfIndexIs(2, 'x', undefinedNumber);
        boolean oneIsX = checkIfIndexIs(1, 'X', undefinedNumber);
        boolean twoIsX = checkIfIndexIs(2, 'X', undefinedNumber);

//in case it is a decimal number
        if (!isBinaryNumber(zeroIsZero, oneIsZero, oneIsB, twoIsB, oneIsb, twoIsb)
                && !isHexaDecimalNumber(zeroIsZero, oneIsZero, oneIsx, twoIsx, oneIsX, twoIsX)) {

            numberValue = calculateDecimalNumber(decimalLiteralIndex, exponentialLiteralIndex, undefinedNumber);
//case  if binary number
        } else if (isBinaryNumber(zeroIsZero, oneIsZero, oneIsB, twoIsB, oneIsb, twoIsb)) {

            numberValue = calculateBinaryNumber(exponentialLiteralIndex, undefinedNumber);
//case if hexadezimal  number
        } else {
            numberValue = calculateHexadecimalNumber(undefinedNumber);
        }
// caculating exponent if exists
        if (exponentialLiteralIndex != -1 && exponentialLiteralIndex != undefinedNumber.length() - 1) {
            exponentialNumber = calculateExponentialNumber(exponentialLiteralIndex, undefinedNumber);
        }
//
        if (Converter.positiveExponent && Converter.positive) {
            secureNumber = numberValue * Math.pow(10, exponentialNumber);
        }
        if (!Converter.positiveExponent && Converter.positive) {
            secureNumber = numberValue * Math.pow(10, (-1) * exponentialNumber);
        }
        if (Converter.positiveExponent && !Converter.positive) {
            secureNumber = (-1) * numberValue * Math.pow(10, exponentialNumber);
        }
        if (!Converter.positiveExponent && !Converter.positive) {
            secureNumber = (-1) * numberValue * Math.pow(10, (-1) * exponentialNumber);
        }
        Converter.positive = true;
        Converter.positiveExponent = true;

        return secureNumber;
    }


    /**
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
     *
     * @param secureInputString Looking for the word
     *                          "quit" in the parameter
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
     * checks if an object with certain starting digits is a hexadecimal number
     *
     * @param zeroIsZero boolean that tells if 0 is the Value at Index 0
     * @param oneIsZero  boolean that tells if 0 is the Value at Index 1
     * @param oneIsx     boolean that tells if 0 is the Value at Index 1
     * @param twoIsx     boolean that tells if 0 is the Value at Index 2
     * @param oneIsX     boolean that tells if 0 is the Value at Index 1
     * @param twoIsX     boolean that tells if 0 is the Value at Index 2
     * @return <boolean>true</boolean> If a String with those boolean values
     * fulfils a certain condition.
     * Either it has to be the first 2 letters are 0X / 0x
     * or the second and third letters are 0x / 0X
     */
    private static boolean isHexaDecimalNumber(boolean zeroIsZero, boolean oneIsZero, boolean oneIsx,
                                               boolean twoIsx, boolean oneIsX, boolean twoIsX) {
        return zeroIsZero && oneIsx
                || zeroIsZero && oneIsX
                || oneIsZero && twoIsx
                || oneIsZero && twoIsX;
    }

    /**
     * checks if an object with certain starting digits is a binary number
     *
     * @param zeroIsZero boolean that tells if 0 is the Value at Index 0
     * @param oneIsZero  boolean that tells if 0 is the Value at Index 1
     * @param oneIsB     boolean that tells if 0 is the Value at Index 1
     * @param twoIsB     boolean that tells if 0 is the Value at Index 2
     * @param oneIsb     boolean that tells if 0 is the Value at Index 1
     * @param twoIsb     boolean that tells if 0 is the Value at Index 2
     * @return <boolean>true</boolean> If a String with those boolean values
     * fulfils a certain condition.
     * Either it has to be the first 2 letters are 0B / 0b
     * or the second and third letters are 0B / 0b
     */
    private static boolean isBinaryNumber(boolean zeroIsZero, boolean oneIsZero, boolean oneIsB,
                                          boolean twoIsB, boolean oneIsb, boolean twoIsb) {
        return zeroIsZero && oneIsb
                || zeroIsZero && oneIsB
                || oneIsZero && twoIsb
                || oneIsZero && twoIsB;
    }

    /**
     * The method builds up a double value depending on the indices, it builds up
     * the number in this way : a*10^1+b*10^0,c*10^-1 end of number or exponentialLiteralIndex = ab,c
     *
     * @param decimalLiteralIndex     The Index that is used to build up the decimal number
     *                                in the decimal base logic.  a*10^1+b*10^0,c*10^-1 = ab,c
     * @param exponentialLiteralIndex The Index is needed to see when the decimal number
     *                                ends. If there is no exponential index the string
     *                                is evaluated as a decimal number until the last index.
     * @param undefinedNumber         <String>undefinedNumber</String> is a string that gets
     *                                converted into a decimal number.
     * @return <double>numberValue</double>,the value of the decimal number as a double value.
     * @throws LiteralAbuseException    throws LiteralAbuseException when 'pi' or 'e'
     *                                  are written within a number.
     * @throws NumberStructureException Throws NumberStructureException when something has been
     *                                  appended into errorCalls, because of a digit that couldn't
     *                                  be recognized or was set at the wrong point.
     */
    static private double calculateDecimalNumber(int decimalLiteralIndex, int exponentialLiteralIndex, String undefinedNumber) throws ExceptionCluster {
        StringBuilder errorCalls = new StringBuilder("");
        double numberValue = 0;


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
                        Converter.positive = false;
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
        if (errorCalls.length() != 0) {
            throw new NumberStructureException(errorCalls.toString());
        }

        return numberValue;
    }

    /**
     * The method builds up a double value depending on the indices, it builds up
     * the number in this way : a*2^x...b*2^2+c*2^1+d*2^0
     *
     * @param exponentialLiteralIndex The Index that is used to build up the decimal number
     *                                in the decimal base logic.  a*2^1+b*2^0,c*2^-1 = ab,c
     * @param undefinedNumber         <String>undefinedNumber</String> is a string that gets
     *                                converted into a decimal number
     * @return <double>numberValue</double>,the value of the binary number as a double value.
     * @throws NumberStructureException Throws NumberStructureException when something has been
     *                                  appended into errorCalls, because of a digit that couldn't
     *                                  be recognized or was set at the wrong point.
     */

    private static double calculateBinaryNumber(int exponentialLiteralIndex, String undefinedNumber) throws NumberStructureException {
        StringBuilder errorCalls = new StringBuilder("");
        double numberValue = 0;

        if (undefinedNumber.charAt(0) == '-') {
            Converter.positive = false;
        }
        int referencePoint = exponentialLiteralIndex - 1;
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
        if (errorCalls.length() != 0) {
            throw new NumberStructureException(errorCalls.toString());
        }
        return numberValue;
    }

    /**
     * The method builds up a double value depending on the indices, it builds up
     * the number in this way : a*16^x...b*16^2+c*16^1+d*16^0
     *
     * @param undefinedNumber <String>undefinedNumber</String> is a string that gets
     *                        converted into a hexadecimal number
     * @return <double>numberValue</double>,the value of the binary number as a double value.
     * @throws NumberStructureException Throws NumberStructureException when something has been
     *                                  appended into errorCalls, because of a digit that couldn't
     *                                  be recognized or was set at the wrong point.
     */
    private static double calculateHexadecimalNumber(String undefinedNumber) throws NumberStructureException {
        StringBuilder errorCalls = new StringBuilder("");
        double numberValue = 0;
        if (undefinedNumber.charAt(0) == '-') {
            Converter.positive = false;
        }

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
        if (errorCalls.length() != 0) {
            throw new NumberStructureException(errorCalls.toString());
        }
        return numberValue;
    }

    /**
     * The method builds up a double value depending on the indices, it builds up
     * the number in this way : a*10^1+b*10^0,c*10^-1 end of number or exponentialLiteralIndex = ab,c
     *
     * @param exponentialLiteralIndex is the index from where this method starts to evaluate
     *                                the <String>undefinedNumber</String> and analyzes until
     *                                the end of <String>undefinedNumber</String>.
     * @param undefinedNumber         <String>undefinedNumber</String> is a string that gets
     *                                converted into an exponential number.
     * @return <double>exponentialNumber</double>,the value of the decimal number as a double value.
     * @throws LiteralAbuseException    throws LiteralAbuseException when 'pi' or 'e'
     *                                  are written within a number.
     * @throws NumberStructureException Throws NumberStructureException when something has been
     *                                  appended into errorCalls, because of a digit that couldn't
     *                                  be recognized or was set at the wrong point.
     */

    private static double calculateExponentialNumber(int exponentialLiteralIndex, String undefinedNumber) {
        StringBuilder errorCalls = new StringBuilder("");
        double exponentialNumber = 0;
        for (int i = exponentialLiteralIndex + 1; i < undefinedNumber.length(); i++) {
            switch (undefinedNumber.charAt(i)) {
                case '-':
                    if (i == exponentialLiteralIndex + 1) {
                        Converter.positiveExponent = false;
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
        if (errorCalls.length() != 0) {
            throw new NumberStructureException(errorCalls.toString());
        }
        return exponentialNumber;
    }

}

