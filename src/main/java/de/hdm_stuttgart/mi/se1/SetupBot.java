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
                MathOperation.subtract();
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
    // method for tracking Literals such as binary hexa or 2E10
    public static String convertLiterals(String undefinedNumber){

        StringBuffer preparedNumber= new StringBuffer("");
        StringBuffer errorCalls=new StringBuffer("");

        double numberValue=0;
        boolean positive=true;
        double exponentialNumber=0;
        boolean positiveExponent = true;

        int exponentialLiteralIndex;
        if(undefinedNumber.lastIndexOf('E')==-1){
            exponentialLiteralIndex=undefinedNumber.length();
        }else{
            exponentialLiteralIndex=undefinedNumber.lastIndexOf('E');
        }

        int decimalLiteralIndex;
            if (undefinedNumber.indexOf(',') >= 0) {
                decimalLiteralIndex = undefinedNumber.indexOf(',');
            } else if (undefinedNumber.indexOf('.') >= 0) {
                decimalLiteralIndex = undefinedNumber.indexOf('.');
            }else if (exponentialLiteralIndex==-1){
                decimalLiteralIndex=undefinedNumber.length()-1;
            }else{
                decimalLiteralIndex=exponentialLiteralIndex;
            }
//in case it is a Dezimal number
        if(!(undefinedNumber.charAt(0)=='0'&&undefinedNumber.charAt(1)=='b')
                &&!(undefinedNumber.charAt(0)=='0'&&undefinedNumber.charAt(1)=='x')
                &&!(undefinedNumber.charAt(1)=='0'&&undefinedNumber.charAt(2)=='b')
                &&!(undefinedNumber.charAt(1)=='0'&&undefinedNumber.charAt(2)=='x')) {

            for (int i = 0; i < exponentialLiteralIndex; i++) {
                int referencePoint = decimalLiteralIndex-1 ;
                if (i > decimalLiteralIndex) {
                    referencePoint = decimalLiteralIndex;
                }
                switch (undefinedNumber.charAt(i)) {
                    case '1':numberValue = numberValue + 1 * Math.pow(10, (referencePoint - i));
                    break;
                    case '2':numberValue = numberValue + 2 * Math.pow(10, (referencePoint - i));
                        break;
                    case '3':numberValue = numberValue + 3 * Math.pow(10, (referencePoint - i));
                        break;
                    case '4':numberValue = numberValue + 4 * Math.pow(10, (referencePoint - i));
                        break;
                    case '5':numberValue = numberValue + 5 * Math.pow(10, (referencePoint - i));
                        break;
                    case '6':numberValue = numberValue + 6 * Math.pow(10, (referencePoint - i));
                        break;
                    case '7':numberValue = numberValue + 7 * Math.pow(10, (referencePoint - i));
                        break;
                    case '8':numberValue = numberValue + 8 * Math.pow(10, (referencePoint - i));
                        break;
                    case '9':numberValue = numberValue + 9 * Math.pow(10, (referencePoint - i));
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
                        if (i != 0 && i != 1) {
                            //TODO what is when "e" stands within of a word which is  wrong as a whole
                            errorCalls.append("\nCharacter:'e' at Index " + i + " of " + undefinedNumber + " is a literal for the euler number" +
                                    "\n>>||the euler number is a Value itself and not a Part of a number||");
                        }
                        break;
                    default:
                        errorCalls.append("\nCharacter:" + undefinedNumber.charAt(i) + " at Index " + i + " of " + undefinedNumber + " is an unknown symbol " +
                                "\n>>||please, only enter the allowed characters (0-9 , e , E , ',' , '.' , -) for numbers or hexadecimal/binary numbers||");
                        break;
                }
            }
//case  if binary number
        }else if(undefinedNumber.charAt(0)=='0'&&undefinedNumber.charAt(1)=='b'
                ||undefinedNumber.charAt(1)=='0'&&undefinedNumber.charAt(2)=='b'){
            if (undefinedNumber.charAt(0) == '-') {
                positive = false;
            }
            int referencePoint = decimalLiteralIndex-1 ;
            for (int i = undefinedNumber.indexOf('0')+2; i < exponentialLiteralIndex; i++) {
                switch (undefinedNumber.charAt(i)) {
                    case '0':
                        break;
                    case '1':numberValue=numberValue+Math.pow(2,referencePoint-i);
                        break;
                    case '-':
                        errorCalls.append("\nCharacter:'-' at Index " + i + " of " + undefinedNumber + " can not be resolved" +
                                    "\n>>||only use '-' in front of a number||");
                        break;
                    default:
                        errorCalls.append("\nCharacter: " + undefinedNumber.charAt(i) +" at Index " + i + " of " + undefinedNumber + " can not be resolved " +
                                "\n>>||only use 0 or 1 within binary numbers||");
                        break;
                }
            }
//case if hexadezimal  number
        }else if(undefinedNumber.charAt(0)=='0'&&undefinedNumber.charAt(1)=='x'
                ||undefinedNumber.charAt(1)=='0'&&undefinedNumber.charAt(2)=='x'){
                int lastIndexOfHexa=exponentialLiteralIndex-1;
            if (undefinedNumber.charAt(0) == '-') {
                positive = false;
            }
            if(exponentialLiteralIndex==undefinedNumber.length()-1){
                lastIndexOfHexa=undefinedNumber.length()-1;
            }

            for (int i = undefinedNumber.indexOf('0')+2; i <=lastIndexOfHexa; i++) {
                switch (undefinedNumber.charAt(i)) {
                    case '0':
                        break;
                    case '1':numberValue=numberValue+1*Math.pow(16,lastIndexOfHexa-i);
                        break;
                    case '2':numberValue=numberValue+2*Math.pow(16,lastIndexOfHexa-i);
                        break;
                    case '3':numberValue=numberValue+3*Math.pow(16,lastIndexOfHexa-i);
                        break;
                    case '4':numberValue=numberValue+4*Math.pow(16,lastIndexOfHexa-i);
                        break;
                    case '5':numberValue=numberValue+5*Math.pow(16,lastIndexOfHexa-i);
                        break;
                    case '6':numberValue=numberValue+6*Math.pow(16,lastIndexOfHexa-i);
                        break;
                    case '7':numberValue=numberValue+7*Math.pow(16,lastIndexOfHexa-i);
                        break;
                    case '8':numberValue=numberValue+8*Math.pow(16,lastIndexOfHexa-i);
                        break;
                    case '9':numberValue=numberValue+9*Math.pow(16,lastIndexOfHexa-i);
                    break;
                    case 'A':numberValue=numberValue+10*Math.pow(16,lastIndexOfHexa-i);
                    break;
                    case 'B':numberValue=numberValue+11*Math.pow(16,lastIndexOfHexa-i);
                    break;
                    case 'C':numberValue=numberValue+12*Math.pow(16,lastIndexOfHexa-i);
                    break;
                    case 'D':numberValue=numberValue+13*Math.pow(16,lastIndexOfHexa-i);
                    break;
                    case 'E':numberValue=numberValue+14*Math.pow(16,lastIndexOfHexa-i);
                    break;
                    case 'F':numberValue=numberValue+15*Math.pow(16,lastIndexOfHexa-i);
                    break;
                    default:
                        errorCalls.append("\nCharacter: " + undefinedNumber.charAt(i) +" at Index " + i + " of " + undefinedNumber + " can not be resolved " +
                                "\n>>||only use 0 - F within binary numbers||");
                        break;
                }
            }
        }
// caculating exponent if exists
        if(exponentialLiteralIndex!=-1 && exponentialLiteralIndex!=undefinedNumber.length()-1 ) {
            for (int i=exponentialLiteralIndex+1;i<undefinedNumber.length();i++){
                switch(undefinedNumber.charAt(i)){
                    case '-':
                        if(i==exponentialLiteralIndex+1) {
                            positiveExponent=false;
                        }else{
                            errorCalls.append("\nCharacter:'-' at Index "+ i +" of " + undefinedNumber + " can not be resolved" +
                                    "\n>>||only use '-' in front of a number||");
                        }
                    break;
                    case '1': exponentialNumber=exponentialNumber+1*Math.pow(10,(undefinedNumber.length()-1-i));
                        break;
                    case '2': exponentialNumber=exponentialNumber+2*Math.pow(10,(undefinedNumber.length()-1-i));
                        break;
                    case '3': exponentialNumber=exponentialNumber+3*Math.pow(10,(undefinedNumber.length()-1-i));
                        break;
                    case '4': exponentialNumber=exponentialNumber+4*Math.pow(10,(undefinedNumber.length()-1-i));
                        break;
                    case '5': exponentialNumber=exponentialNumber+5*Math.pow(10,(undefinedNumber.length()-1-i));
                        break;
                    case '6': exponentialNumber=exponentialNumber+6*Math.pow(10,(undefinedNumber.length()-1-i));
                        break;
                    case '7': exponentialNumber=exponentialNumber+7*Math.pow(10,(undefinedNumber.length()-1-i));
                        break;
                    case '8': exponentialNumber=exponentialNumber+8*Math.pow(10,(undefinedNumber.length()-1-i));
                        break;
                    case '9': exponentialNumber=exponentialNumber+9*Math.pow(10,(undefinedNumber.length()-1-i));
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
        if(positiveExponent && positive) {
            preparedNumber.append(numberValue * Math.pow(10, exponentialNumber));
        }
        if(!positiveExponent && positive) {
            preparedNumber.append( numberValue * Math.pow(10,(-1)* exponentialNumber));
        }
        if(positiveExponent && !positive) {
            preparedNumber.append((-1)*numberValue * Math.pow(10,exponentialNumber));
        }
        if(!positiveExponent && !positive) {
            preparedNumber.append((-1)* numberValue * Math.pow(10,(-1)* exponentialNumber));
        }

        preparedNumber.append(errorCalls);

        return preparedNumber.toString();
    }
}
