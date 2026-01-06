package com.business_control_system.domain.exception;

public class AlreadyExistsException extends RuntimeException {
    public AlreadyExistsException(String message){
       super(message);
    }
}
