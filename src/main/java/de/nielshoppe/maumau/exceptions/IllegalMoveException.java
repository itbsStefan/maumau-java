package de.nielshoppe.maumau.exceptions;

public class IllegalMoveException extends Exception {

    private String message;

    public IllegalMoveException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
