package de.hdm_stuttgart.mi.se1.exceptions;

public class JustOperatorsException extends ExceptionCluster {

    public JustOperatorsException(){
        super("ERROR: invalid calculation input\n >>||please do not use just operators, u need to have numbers.||");
    }
@Override
    public String getErrorType(){
        return "JustOperatorsException";
}
}
