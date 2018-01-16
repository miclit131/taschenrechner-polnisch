package de.hdm_stuttgart.mi.se1;

public class JustOperatorsException extends ExceptionCluster {

    public JustOperatorsException(){
        super("ERROR: invalid calculation input\n >>||please do not use just operators, u need to have numbers.||");
    }
@Override
    protected String getErrorType(){
        return "JustOperatorsException";
}
}
