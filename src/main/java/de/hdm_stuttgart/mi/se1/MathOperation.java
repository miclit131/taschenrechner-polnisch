package de.hdm_stuttgart.mi.se1;

public class MathOperation {
    //TODO implement math operations that know how many objects they need
    //TODO operations should take numbers out of the stack,  calculate and put the answere back into the stack
    //TODO think about the case if an operation cant take enough objects out of  the stack
    //TODO  and all stack values are still strings, use  Double.parseDouble(String) to convert them, but it only works with numbers

    /**
     * takes the 2 top-most items  of App.inputValuesForCalculation
     * and puts the answere back on top of the  same stack<Double>
     */
    public static void add() {
        double answere = App.inputValuesForCalculation.pop() + App.inputValuesForCalculation.pop();
        App.inputValuesForCalculation.push(answere);

    }

    //TODO IMPLEMENT US  /**with javaDoc please*/
    public static void substract() {

    }

    public static void divide() {

    }

    public static void mulitply() {

    }

    public static void pow() {

    }

    public static void unarySubstraction() {

    }

    public static void sine() {

    }

    public static void cosine() {

    }

    public static void tangent() {

    }

    public static void exponential() {

    }

    public static void logE() {

    }

    public static void squareRoot() {

    }
}
