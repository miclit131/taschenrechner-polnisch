package de.hdm_stuttgart.mi.se1.exceptions;

public class EmptyEntryException  extends ExceptionCluster{

        public EmptyEntryException(){
            super("HELP:"+" Entry is empty and stopped " +
                    "\n >>||please do enter something before you are starting the program||");
        }
        public String getErrorType(){
            return "EmptyEntryException";
        }

}
