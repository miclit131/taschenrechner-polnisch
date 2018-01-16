package de.hdm_stuttgart.mi.se1;

public class EmptyNumberStackException extends ExceptionCluster {
    public EmptyNumberStackException(){
        super("ERROR: system reached a limit and stopped \n >>||please do not use just operators, u need to have numbers.||");
    }
    protected String getErrorType(){
        return "EmptyNumberStackException";
    }
}
