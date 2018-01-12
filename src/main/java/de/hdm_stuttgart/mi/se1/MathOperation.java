package de.hdm_stuttgart.mi.se1;

public class MathOperation {
    //TODO implement math operations that know how many objects they need
    //TODO operations should take numbers out of the stack,  calculate and put the answere back into the stack
    //TODO think about the case if an operation cant take enough objects out of  the stack
    //TODO  and all stack values are still strings, use  Double.parseDouble(String) to convert them, but it only works with numbers

    /**
     * takes the 2 top-most items  of App.calculationNumbersStack
     * the first number gets added to the second
     * and puts the answer back on top of the  same stack<Double>
     */
    public static void add() {
        double answer = App.calculationNumbersStack.pop() + App.calculationNumbersStack.pop();
        App.calculationNumbersStack.push(answer);
    }

    /**
     * takes the 2 top-most items  of App.calculationNumbersStack
     * the first number gets subtracted from the second number
     * and puts the answer back on top of the  same stack<Double>
     */
    public static void subtract() {
        double a = App.calculationNumbersStack.pop();
        double b = App.calculationNumbersStack.pop();
        double answer = b-a;
        App.calculationNumbersStack.push(answer);
    }

    /**
     * takes the 2 top-most items  of App.calculationNumbersStack
     * the first number gets divided from the second number
     * and puts the answer back on top of the  same stack<Double>
     */
    //TODO what happens if the second number is zero?
    public static void divide() {
        double a = App.calculationNumbersStack.pop();
        double b = App.calculationNumbersStack.pop();
        double answer = b/a;
        App.calculationNumbersStack.push(answer);
    }

    /**
     * takes the 2 top-most items  of App.calculationNumbersStack
     * the first number gets multiplied with the second
     * and puts the answer back on top of the  same stack<Double>
     */
    public static void mulitply() {
        double answer = App.calculationNumbersStack.pop() * App.calculationNumbersStack.pop();
        App.calculationNumbersStack.push(answer);
    }

    /**
     * takes the 2 top-most items  of App.calculationNumbersStack
     * the result of raising the first number to the power of the second number
     * and puts the answer back on top of the  same stack<Double>
     */
    public static void pow() {
        double a = App.calculationNumbersStack.pop();
        double b = App.calculationNumbersStack.pop();
        double answer = Math.pow(b, a);
        App.calculationNumbersStack.push(answer);
    }

    //TODO Implementation of unarySubtraction
    public static void unarySubtraction() {
        App.calculationNumbersStack.push(-App.calculationNumbersStack.pop());
    }

    //TODO Parameter is in radians not degree
    /**
     * takes the top-most item of App.calculationNumbersStack
     * calculates the sine of a number, in radians not degree
     * and puts the answer back on top of the same stack<Double>
     */
    public static void sine() {
        double answer = Math.sin(App.calculationNumbersStack.pop());
        App.calculationNumbersStack.push(answer);
    }

    //TODO Parameter is in radians not degree
    /**
     * takes the top-most item of App.calculationNumbersStack
     * calculates the cosine of a number, in radians not degree
     * and puts the answer back on top of the same stack<Double>
     */
    public static void cosine() {
        double answer = Math.cos(App.calculationNumbersStack.pop());
        App.calculationNumbersStack.push(answer);
    }

    //TODO Parameter is in radians not degree
    /**
     * takes the top-most item of App.calculationNumbersStack
     * calculates the tangent of a number, in radians not degree
     * and puts the answer back on top of the same stack<Double>
     */
    public static void tangent() {
        double answer = Math.tan(App.calculationNumbersStack.pop());
        App.calculationNumbersStack.push(answer);
    }

    /**
     * takes the top-most item of App.calculationNumbersStack
     * calculates the exponential of a number
     * and puts the answer back on top of the same stack<Double>
     */
    public static void exponential() {
        double answer = Math.exp(App.calculationNumbersStack.pop());
        App.calculationNumbersStack.push(answer);
    }

    /**
     * takes the top-most item of App.calculationNumbersStack
     * calculates the log of a number
     * and puts the answer back on top of the same stack<Double>
     */
    public static void logE() {
        double answer = Math.log(App.calculationNumbersStack.pop());
        App.calculationNumbersStack.push(answer);
    }

    /**
     * takes the top-most item of App.calculationNumbersStack
     * calculates the square root of a number
     * and puts the answer back on top of the same stack<Double>
     */
    public static void squareRoot() {
        double answer = Math.sqrt(App.calculationNumbersStack.pop());
        App.calculationNumbersStack.push(answer);
    }
}
