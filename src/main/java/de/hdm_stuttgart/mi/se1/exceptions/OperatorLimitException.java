package de.hdm_stuttgart.mi.se1.exceptions;

public class OperatorLimitException extends ExceptionCluster {
    public OperatorLimitException(String errorCall){
        super(errorCall);
    }
    @Override
    public String getErrorType(){
        return "OperatorLimitException";
    }
}
