package de.hdm_stuttgart.mi.se1;

public class LiteralAbuseException extends ExceptionCluster{
    public LiteralAbuseException(String errorCalls){
        super(errorCalls);
    }
    @Override
    protected String getErrorType(){
        return "LiteralAbuseException";
    }
}
