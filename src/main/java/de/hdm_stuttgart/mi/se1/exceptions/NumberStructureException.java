package de.hdm_stuttgart.mi.se1.exceptions;

public class NumberStructureException extends ExceptionCluster {
    public NumberStructureException(String ErrorCalls){
        super(ErrorCalls);

    }
  @Override
public String getErrorType(){
        return "NumberStructureException";
}
}
