package com.adnan.carpark.function;
import com.adnan.carpark.exception.ParkingException;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Manages the parking lot operations including parking and leaving cars.
 */
public class CarParkingLot {
    private final int capacity;
    private final double hourlyRate;
    private final ConcurrentHashMap<String, LocalTime> parkedCars;

    /**
     * Constructor to create a parking lot instance.
     *
     * @param capacity   the maximum number of cars the parking lot can hold.
     * @param hourlyRate the hourly rate charged for parking.
     */
    public CarParkingLot(int capacity, double hourlyRate) {
        this.capacity = capacity;
        this.hourlyRate = hourlyRate;
        this.parkedCars = new ConcurrentHashMap<>();
    }

    /**
     * Parks a car in the parking lot.
     *
     * @param car         the car to be parked.
     * @param arrivalTime the arrival time of the car.
     * @return a string message indicating success or failure.
     */
    public String parkCar(Car car, LocalTime arrivalTime) {
        if (parkedCars.size() >= capacity) {
            return "Car park is full";
        }
        parkedCars.put(car.getLicensePlate(), arrivalTime);
        return "Car parked successfully";
    }

    /**
     * Removes a car from the parking lot and calculates the charges.
     *
     * @param licensePlate  the license plate of the car leaving.
     * @param departureTime the departure time of the car.
     * @return the parking charge.
     * @throws ParkingException if the car is not found in the parking lot.
     */
    public double leavePark(String licensePlate, LocalTime departureTime) throws ParkingException {
        LocalTime arrivalTime = parkedCars.remove(licensePlate);
        if (arrivalTime == null) {
            throw new ParkingException("Car not found");
        }
        long hoursParked = ChronoUnit.HOURS.between(arrivalTime, departureTime);
        return Math.ceil(hoursParked) * hourlyRate;
    }

    /**
     * Gets the count of parked cars in the parking lot.
     *
     * @return the number of parked cars.
     */
    public int getParkedCarsCount() {
        return parkedCars.size();
    }
}
