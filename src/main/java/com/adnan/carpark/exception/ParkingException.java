package com.adnan.carpark.exception;

/**
 * Custom exception class for handling parking-related errors.
 */
public class ParkingException extends Exception {
    /**
     * Constructor for ParkingException.
     *
     * @param message the error message.
     */
    public ParkingException(String message) {
        super(message);
    }
}

