package com.aurora.exceptions;

public class ErrorCreatingDatabaseRecord extends Exception{
    public ErrorCreatingDatabaseRecord(String message) {
        super(message);
    }
}
