package com.adnan.carpark;

import com.adnan.carpark.exception.ParkingException;
import com.adnan.carpark.function.Car;
import com.adnan.carpark.function.CarParkingLot;
import com.adnan.carpark.util.TimeUtils;

import java.util.Scanner;

/**
 * The main class for the car parking system CLI.
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CarParkingLot parkingLot = new CarParkingLot(100, 2.0);

        while (true) {
            System.out.println("Enter command (park, leave, exit):");
            String command = scanner.next();
            if ("exit".equals(command)) {
                break;
            }

            System.out.println("Enter license plate:");
            String licensePlate = scanner.next();

            try {
                switch (command) {
                    case "park":
                        System.out.println("Enter arrival time (HH:mm):");
                        String arrivalTime = scanner.next();
                        System.out.println(parkingLot.parkCar(new Car(licensePlate), TimeUtils.parseTime(arrivalTime)));
                        break;
                    case "leave":
                        System.out.println("Enter departure time (HH:mm):");
                        String departureTime = scanner.next();
                        System.out.println("Charge: £" + parkingLot.leavePark(licensePlate, TimeUtils.parseTime(departureTime)));
                        break;
                    default:
                        System.out.println("Invalid command");
                }
            } catch (ParkingException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
