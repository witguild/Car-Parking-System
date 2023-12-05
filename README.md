# Car-Parking-System

## Problem Statement

Car Parking System Requirements

Capacity: 100 cars

Charge £2 per hour

On arrival, the system allocates space if available. If car park is full, show message to the driver relaying that the car park is full.

When the car leaves, the system calculates the time spent and charge £2 per hour, rounding up to the nearest hour.

Don’t need the payment module but just returning the amount is enough.

The system should handle multiple cars coming in at the same time.


## Solution: Car Parking System Project Overview


## Project Goal
The Car Parking System is designed to manage a parking lot with a capacity of 100 cars. It charges £2 per hour, rounds up fees to the nearest hour, and can handle multiple cars arriving simultaneously. The system includes a CLI (Command Line Interface) for interactions like parking and removing cars.

### Use Case and Features
1. Parking a Car: On arrival, if the parking lot has available space, the system assigns a spot and records the time.
2. Car Leaving: When a car leaves, the system calculates the total time spent and charges accordingly.
3. Handling Capacity: The system notifies the driver if the parking lot is full.
4. Concurrent Handling: The system can process multiple cars arriving at the same time.

### Thought Process and Design Decisions

1. Concurrency Handling: Used `ConcurrentHashMap` in `CarParkingLot` to safely handle multiple threads (cars arriving simultaneously) without compromising the integrity of the parking lot's state.

2. Charge Calculation: Implemented a method to calculate charges based on the time a car spends in the lot, rounding up to the nearest hour to align with the business rule.

3. Error Handling: Introduced `ParkingException` to handle specific parking-related errors, such as attempting to remove a car that isn’t in the lot.

4. Time Management: Utilized Java’s `LocalTime` and `ChronoUnit` for time calculations to keep track of parking durations accurately.

5. Testing: Wrote unit tests to validate the functionality of parking and leaving operations, including concurrency handling and edge cases like a full parking lot.

6. User Interface: Developed a simple CLI to interact with the system, allowing basic operations like parking and removing cars, and viewing charges.

7. Modular Code Design: Organized code into separate classes (`Car`, `CarParkingLot`, `ParkingException`, `TimeUtils`) for better maintainability and readability.

### Assumptions

1. Time Format: Assumes that all time inputs are in a 24-hour format and are correctly formatted.
2. No Persistent Storage: The current implementation does not persist data. Once the application is stopped, all data is lost.
3. Single Rate Charging: The system charges a flat rate regardless of the type or size of the vehicle.
4. Sequential CLI Operations: Although the system can handle multiple cars arriving simultaneously, the CLI processes commands sequentially.

### Further Enhancements

1. Persistent Storage: Implement database support to store parking data persistently.
2. Dynamic Pricing: Introduce variable pricing based on factors like peak hours or vehicle type.
3. Advanced CLI/GUI: Develop a more sophisticated user interface or a web-based GUI for easier interaction.
4. API Integration: Create RESTful APIs for integration with external systems or for building a mobile app.
5. Real-Time Monitoring: Add features for real-time monitoring of parking lot occupancy and statistics.



Key point of handling multiple cars coming at the same time is to use 'ConcurrentHashMap'
To test this scenario, In CarParkingLotTest.java, I have used java ExecutorService in to achieve the multithreading and created the thread pool of 10 threads.

### Build the Project
Open a terminal or command prompt, navigate to the root of your project directory (CarParkingSystem), and run:

mvn clean compile

This command will compile your Java code. If there are any compilation errors, they will be displayed in the terminal.

### Run the Application
To run the application, use the following Maven command:

mvn exec:java -Dexec.mainClass="com.adnan.carpark.Main"

The CLI will start, and you can interact with it as per the instructions.

### Running Tests
To run the unit tests, execute:

mvn test

multi-threading test case to handle multiple cars at same time

	@Test
    void testConcurrentParking() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 100; i++) {
            int finalI = i;
            service.submit(() -> parkingLot.parkCar(new Car("CONCUR" + finalI), LocalTime.now()));
        }
        service.shutdown();
        service.awaitTermination(1, TimeUnit.MINUTES);

        assertEquals(100, parkingLot.getParkedCarsCount());
    }

This command will run the tests written in CarParkingLotTest.java and output the results, showing if the tests passed or failed.
