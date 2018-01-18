package de.hdm_stuttgart.mi.se1.exceptions;

public abstract class ExceptionCluster extends Error{
    ExceptionCluster(String errorCall){
        super(errorCall);
    }
    abstract public String getErrorType();
}
