package de.hdm_stuttgart.mi.se1.exceptions;

public abstract class ExceptionCluster extends Error{
    public ExceptionCluster(String errorCall){
        super(errorCall);
    }
    abstract public String getErrorType();
}
