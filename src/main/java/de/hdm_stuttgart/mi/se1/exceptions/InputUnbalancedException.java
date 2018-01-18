package de.hdm_stuttgart.mi.se1.exceptions;

public class InputUnbalancedException extends ExceptionCluster {
    public InputUnbalancedException(){
        super("ERROR: Too many numbers in the result stack " +
                "\n>>|| either add more operators or decrease the number of values initiated.||");
    }
    @Override
    public String getErrorType(){
        return "InputUnbalancedException";
    }
}
