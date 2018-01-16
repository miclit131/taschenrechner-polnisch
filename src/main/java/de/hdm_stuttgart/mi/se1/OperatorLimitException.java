package de.hdm_stuttgart.mi.se1;

public class OperatorLimitException extends ExceptionCluster {
    public OperatorLimitException(String errorCall){
        super(errorCall);
    }
    @Override
    protected String getErrorType(){
        return "OperatorLimitException";
    }
}
