package com.company.errors;

public class OutOfRangeException extends Exception {
    private int index1;
    private int index2;

    public OutOfRangeException(int index1, int index2){
        this.index1 = index1;
        this.index2 = index2;
    }

    public String outOfBounds() {
        return "OutOfRangeException:" +
                "from Index: " + index1 + " > To Index: " + index2;
    }
}
