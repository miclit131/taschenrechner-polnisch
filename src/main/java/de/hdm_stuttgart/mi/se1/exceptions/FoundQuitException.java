package de.hdm_stuttgart.mi.se1.exceptions;

public class FoundQuitException  extends ExceptionCluster{

    public FoundQuitException(){
        super("HELP: In the entry \"quit\" occured " +
                "\n >>||writing \"quit\" at any point into the input box      ||" +
                "\n   ||which is not a the start will trigger this exception||");
    }
    public String getErrorType(){
        return "FoundQuitException";
    }

}
