package de.hdm_stuttgart.mi.se1;

public abstract class ExceptionCluster extends Exception{
    public ExceptionCluster(String errorCall){
        super(errorCall);
    }
    abstract protected String getErrorType();
}
