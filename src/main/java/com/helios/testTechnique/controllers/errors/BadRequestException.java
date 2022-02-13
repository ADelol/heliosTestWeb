package com.helios.testTechnique.controllers.errors;

public class BadRequestException extends RuntimeException {

    public BadRequestException(String message){
        super(message);
    }
}
