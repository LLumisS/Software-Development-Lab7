package org.example.classes.error;

public class ExceptionHandler extends RuntimeException {
    public ExceptionHandler(String msg) {
        super(msg);
    }
}
