package com.adnan.carpark;

import com.adnan.carpark.exception.ParkingException;
import com.adnan.carpark.function.Car;
import com.adnan.carpark.function.CarParkingLot;
import org.junit.jupiter.api.*;
import java.time.LocalTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import static org.junit.jupiter.api.Assertions.*;

class CarParkingLotTest {
    private CarParkingLot parkingLot;

    @BeforeEach
    void setUp() {
        parkingLot = new CarParkingLot(100, 2.0);
    }

    @Test
    void testParkCar() {
        assertEquals("Car parked successfully", parkingLot.parkCar(new Car("CAR123"), LocalTime.now()));
    }

    @Test
    void testLeavePark() throws ParkingException {
        Car car = new Car("CAR123");
        parkingLot.parkCar(car, LocalTime.now());
        assertDoesNotThrow(() -> parkingLot.leavePark(car.getLicensePlate(), LocalTime.now().plusHours(1)));
    }

    @Test
    void testParkingLotFull() {
        for (int i = 0; i < 100; i++) {
            parkingLot.parkCar(new Car("CAR" + i), LocalTime.now());
        }
        assertEquals("Car park is full", parkingLot.parkCar(new Car("CAR101"), LocalTime.now()));
    }

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
}
