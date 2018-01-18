package de.hdm_stuttgart.mi.se1.project;


import de.hdm_stuttgart.mi.se1.exceptions.OperatorLimitException;

/**
 * methods calculating with values from solve method
 * binary operators: takes 2 top-most items of App.calculationNumbersStack
 *                   executes binary calculation
 *                   puts the answer on top of App.calculationNumbersStack
 * unary operators: takes top-most item of App.calculationNumbersStack
 *                  executes unary calculation
 *                  puts result on top of App.calculationNumbersStack<Double>
 */
public class MathOperation {

    /**
     * first number is added to second number
     */
    public static void add() throws OperatorLimitException {
        if (App.calculationNumbersStack.size() >= 2) {
            double answer = App.calculationNumbersStack.pop() + App.calculationNumbersStack.pop();
            App.calculationNumbersStack.push(answer);
        } else {
            throw new OperatorLimitException("ERROR: system reached a limit and stopped at Index " + SetupBot.currentIndex +
                    "\n>>||the binary operator \"" + App.InputStringArray[SetupBot.currentIndex - 1] + "\" can't be used on single number||" +
                    "\n  ||binary operators always need 2 input values           ||");
        }
    }

    /**
     * first number is subtracted from second number
     */
    public static void subtract() throws OperatorLimitException {
        if (App.calculationNumbersStack.size() >= 2) {
            double a = App.calculationNumbersStack.pop();
            double b = App.calculationNumbersStack.pop();
            double answer = b - a;
            App.calculationNumbersStack.push(answer);
        } else {
            throw new OperatorLimitException("ERROR: system reached a limit and stopped at Index " + SetupBot.currentIndex +
                    "\n>>||the binary operator \"" + App.InputStringArray[SetupBot.currentIndex - 1] + "\" can't be used on single number||" +
                    "\n  ||binary operators always need 2 input values           ||");
        }
    }

    /**
     * second number is divided by first number
     */
    //TODO what happens if the second number is zero?
    public static void divide() throws OperatorLimitException {
        if (App.calculationNumbersStack.size() >= 2) {
            double a = App.calculationNumbersStack.pop();
            double b = App.calculationNumbersStack.pop();
            double answer = b / a;
            App.calculationNumbersStack.push(answer);
        } else {
            throw new OperatorLimitException("ERROR: system reached a limit and stopped at Index " + SetupBot.currentIndex +
                    "\n>>||the binary operator \"" + App.InputStringArray[SetupBot.currentIndex - 1] + "\" can't be used on single number||" +
                    "\n  ||binary operators always need 2 input values           ||");
        }
    }

    /**
     * first number is multiplied by second number
     */
    public static void multiply() throws OperatorLimitException {
        if (App.calculationNumbersStack.size() >= 2) {
            double answer = App.calculationNumbersStack.pop() * App.calculationNumbersStack.pop();
            App.calculationNumbersStack.push(answer);
        } else {
            throw new OperatorLimitException("ERROR: system reached a limit and stopped at Index " + SetupBot.currentIndex +
                    "\n>>||the binary operator \"" + App.InputStringArray[SetupBot.currentIndex - 1] + "\" can't be used on single number||" +
                    "\n  ||binary operators always need 2 input values           ||");
        }
    }

    /**
     * second number is raised to the first number's power
     */
    public static void pow() throws OperatorLimitException {
        if (App.calculationNumbersStack.size() >= 2) {
            double a = App.calculationNumbersStack.pop();
            double b = App.calculationNumbersStack.pop();
            double answer = Math.pow(b, a);
            App.calculationNumbersStack.push(answer);
        } else {
            throw new OperatorLimitException("ERROR: system reached a limit and stopped at Index " + SetupBot.currentIndex +
                    "\n>>||the binary operator \"" + App.InputStringArray[SetupBot.currentIndex - 1] + "\" can't be used on single number||" +
                    "\n  ||binary operators always need 2 input values           ||");
        }
    }

    /**
     * negates number
     */
    public static void unarySubtraction() {
        App.calculationNumbersStack.push(-App.calculationNumbersStack.pop());
    }

    /**
     * calculates the sine of a number in radians
     */
    public static void sine() {
        double answer = Math.sin(App.calculationNumbersStack.pop());
        App.calculationNumbersStack.push(answer);
    }

    /**
     * calculates the cosine of a number in radians
     */
    public static void cosine() {
        double answer = Math.cos(App.calculationNumbersStack.pop());
        App.calculationNumbersStack.push(answer);
    }

    /**
     * calculates the tangent of a number in radians
     */
    public static void tangent() {
        double answer = Math.tan(App.calculationNumbersStack.pop());
        App.calculationNumbersStack.push(answer);
    }

    /**
     * calculates the exponential of a number
     */
    public static void exponential() {
        double answer = Math.exp(App.calculationNumbersStack.pop());
        App.calculationNumbersStack.push(answer);
    }

    /**
     * calculates the logarithm of a number
     */
    public static void logE() {
        double answer = Math.log(App.calculationNumbersStack.pop());
        App.calculationNumbersStack.push(answer);
    }

    /**
     * calculates the square root of a number
     * square roots of negative numbers lead to "Not a Number" result
     */
    public static void squareRoot() {
        double answer = Math.sqrt(App.calculationNumbersStack.pop());
        App.calculationNumbersStack.push(answer);
    }
}
