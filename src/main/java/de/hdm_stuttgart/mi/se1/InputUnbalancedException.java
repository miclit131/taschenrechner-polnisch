package de.hdm_stuttgart.mi.se1;

public class InputUnbalancedException extends Exception {
    public InputUnbalancedException(){
        super("ERROR: too many numbers in the result stack " +
                "\n>>|| either add more operators or decrease the number of values initiated.||");
    }
}
