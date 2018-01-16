package de.hdm_stuttgart.mi.se1;

public class NumberStructureException extends ExceptionCluster{
    public NumberStructureException(String ErrorCalls){
        super(ErrorCalls);

    }
  @Override
protected String getErrorType(){
        return "NumberStructureException";
}
}
