package de.hdm_stuttgart.mi.se1.exceptions;

public class LiteralAbuseException extends ExceptionCluster {
    public LiteralAbuseException(String errorCalls){
        super(errorCalls);
    }
    @Override
    public String getErrorType(){
        return "LiteralAbuseException";
    }
}
