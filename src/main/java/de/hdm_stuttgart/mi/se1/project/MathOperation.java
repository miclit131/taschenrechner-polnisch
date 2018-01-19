package de.hdm_stuttgart.mi.se1.project;


import de.hdm_stuttgart.mi.se1.exceptions.OperatorLimitException;

/**
 * Methods calculating with values from solve method.
 * binary operators: takes 2 top-most items of App.calculationNumbersStack
 * executes binary calculation
 * puts the answer on top of App.calculationNumbersStack
 * unary operators:  takes top-most item of App.calculationNumbersStack
 * executes unary calculation
 * puts result on top of App.calculationNumbersStack<Double>
 */
class MathOperation {

    /**
     * First number is added to second number.
     */
    public static void add() throws OperatorLimitException {
        if (Calculator.calculationNumbersStack.size() >= 2) {
            double answer = Calculator.calculationNumbersStack.pop() + Calculator.calculationNumbersStack.pop();
            Calculator.calculationNumbersStack.push(answer);
        } else {
            throw new OperatorLimitException("ERROR: system reached a limit and stopped at Index " + Calculator.currentIndex +
                    "\n>>||the binary operator \"" + App.InputStringArray[Calculator.currentIndex - 1] + "\" can't be used on single number||" +
                    "\n  ||binary operators always need 2 input values           ||");
        }
    }

    /**
     * First number is subtracted from second number.
     */
    public static void subtract() throws OperatorLimitException {
        if (Calculator.calculationNumbersStack.size() >= 2) {
            double a = Calculator.calculationNumbersStack.pop();
            double b = Calculator.calculationNumbersStack.pop();
            double answer = b - a;
            Calculator.calculationNumbersStack.push(answer);
        } else {
            throw new OperatorLimitException("ERROR: system reached a limit and stopped at Index " + Calculator.currentIndex +
                    "\n>>||the binary operator \"" + App.InputStringArray[Calculator.currentIndex - 1] + "\" can't be used on single number||" +
                    "\n  ||binary operators always need 2 input values           ||");
        }
    }

    /**
     * Second number is divided by first number.
     */

    public static void divide() throws OperatorLimitException {
        if (Calculator.calculationNumbersStack.size() >= 2) {
            double a = Calculator.calculationNumbersStack.pop();
            double b = Calculator.calculationNumbersStack.pop();
            double answer = b / a;
            Calculator.calculationNumbersStack.push(answer);
        } else {
            throw new OperatorLimitException("ERROR: system reached a limit and stopped at Index " + Calculator.currentIndex +
                    "\n>>||the binary operator \"" + App.InputStringArray[Calculator.currentIndex - 1] + "\" can't be used on single number||" +
                    "\n  ||binary operators always need 2 input values           ||");
        }
    }

    /**
     * First number is multiplied by second number.
     */
    public static void multiply() throws OperatorLimitException {
        if (Calculator.calculationNumbersStack.size() >= 2) {
            double answer = Calculator.calculationNumbersStack.pop() * Calculator.calculationNumbersStack.pop();
            Calculator.calculationNumbersStack.push(answer);
        } else {
            throw new OperatorLimitException("ERROR: system reached a limit and stopped at Index " + Calculator.currentIndex +
                    "\n>>||the binary operator \"" + App.InputStringArray[Calculator.currentIndex - 1] + "\" can't be used on single number||" +
                    "\n  ||binary operators always need 2 input values           ||");
        }
    }

    /**
     * Second number is raised to the first number's power.
     */
    public static void pow() throws OperatorLimitException {
        if (Calculator.calculationNumbersStack.size() >= 2) {
            double a = Calculator.calculationNumbersStack.pop();
            double b = Calculator.calculationNumbersStack.pop();
            double answer = Math.pow(b, a);
            Calculator.calculationNumbersStack.push(answer);
        } else {
            throw new OperatorLimitException("ERROR: system reached a limit and stopped at Index " + Calculator.currentIndex +
                    "\n>>||the binary operator \"" + App.InputStringArray[Calculator.currentIndex - 1] + "\" can't be used on single number||" +
                    "\n  ||binary operators always need 2 input values           ||");
        }
    }

    /**
     * Negates number
     */
    public static void unarySubtraction() {
        Calculator.calculationNumbersStack.push(-Calculator.calculationNumbersStack.pop());
    }

    /**
     * Calculates the sine of a number in radians.
     */
    public static void sine() {
        double answer = Math.sin(Calculator.calculationNumbersStack.pop());
        Calculator.calculationNumbersStack.push(answer);
    }

    /**
     * Calculates the cosine of a number in radians.
     */
    public static void cosine() {
        double answer = Math.cos(Calculator.calculationNumbersStack.pop());
        Calculator.calculationNumbersStack.push(answer);
    }

    /**
     * Calculates the tangent of a number in radians.
     */
    public static void tangent() {
        double answer = Math.tan(Calculator.calculationNumbersStack.pop());
        Calculator.calculationNumbersStack.push(answer);
    }

    /**
     * Calculates the exponential of a number.
     */
    public static void exponential() {
        double answer = Math.exp(Calculator.calculationNumbersStack.pop());
        Calculator.calculationNumbersStack.push(answer);
    }

    /**
     * Calculates the logarithm of a number.
     */
    public static void logE() {
        double answer = Math.log(Calculator.calculationNumbersStack.pop());
        Calculator.calculationNumbersStack.push(answer);
    }

    /**
     * Calculates the square root of a number.
     * Square roots of negative numbers lead to "Not a Number" result.
     */
    public static void squareRoot() {
        double answer = Math.sqrt(Calculator.calculationNumbersStack.pop());
        Calculator.calculationNumbersStack.push(answer);
    }
}
