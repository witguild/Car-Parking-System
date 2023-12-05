# Car-Parking-System

## Problem Statement

Car Parking System Requirements

Capacity: 100 cars

Charge £2 per hour

On arrival, the system allocates space if available. If car park is full, show message to the driver relaying that the car park is full.

When the car leaves, the system calculates the time spent and charge £2 per hour, rounding up to the nearest hour.

Don’t need the payment module but just returning the amount is enough.

The system should handle multiple cars coming in at the same time.

-------------------------------------------------------------------------------------------------------------------------------------------------------------------

## Solution

The solution is build on Java Maven based project for the above problem statement.

Key point of handling multiple cars coming at the same time is to use 'ConcurrentHashMap'
To test this scenario, In CarParkingLotTest.java, I have used java ExecutorService in to achieve the multithreading and created the thread pool of 10 threads.

### Build the Project
Open a terminal or command prompt, navigate to the root of your project directory (CarParkingSystem), and run:

mvn clean compile

This command will compile your Java code. If there are any compilation errors, they will be displayed in the terminal.

### Run the Application
To run the application, use the following Maven command:

mvn exec:java -Dexec.mainClass="com.adnan.carparkingsystem.Main"
The CLI will start, and you can interact with it as per the instructions.

### Running Tests
To run the unit tests, execute:

mvn test

This command will run the tests written in CarParkingLotTest.java and output the results, showing if the tests passed or failed.
