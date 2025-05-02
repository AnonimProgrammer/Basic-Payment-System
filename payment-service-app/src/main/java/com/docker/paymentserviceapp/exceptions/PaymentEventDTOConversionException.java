package com.docker.paymentserviceapp.exceptions;

public class PaymentEventDTOConversionException extends RuntimeException {

    public PaymentEventDTOConversionException(String message) {
        super(message);
    }

    public PaymentEventDTOConversionException(String message, Throwable cause) {
        super(message, cause);
    }
}
