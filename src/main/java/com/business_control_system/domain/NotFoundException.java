package com.business_control_system.domain;

public class NotFoundException extends RuntimeException{
        public NotFoundException(String message){
            super(message);
        }
}
