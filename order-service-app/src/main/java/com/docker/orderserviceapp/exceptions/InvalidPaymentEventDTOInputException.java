package com.docker.orderserviceapp.exceptions;

public class InvalidPaymentEventDTOInputException extends RuntimeException{

    public InvalidPaymentEventDTOInputException(String message) {
        super(message);
    }

    public InvalidPaymentEventDTOInputException(String message, Throwable cause) {
        super(message, cause);
    }
}
