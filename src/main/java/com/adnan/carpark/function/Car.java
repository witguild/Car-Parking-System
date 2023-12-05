package com.adnan.carpark.function;

/**
 * Represents a car with a license plate.
 */
public class Car {
    private final String licensePlate;

    /**
     * Constructor to create a new car instance.
     *
     * @param licensePlate the license plate of the car.
     */
    public Car(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    /**
     * Gets the license plate of the car.
     *
     * @return the license plate.
     */
    public String getLicensePlate() {
        return licensePlate;
    }
}

