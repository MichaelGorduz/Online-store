package org.example;

class InvalidInputException extends RuntimeException {
    public InvalidInputException(String message) {
        super(message);
    }
}