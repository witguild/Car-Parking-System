package com.adnan.carpark.util;

import java.time.LocalTime;

/**
 * Utility class for time-related operations.
 */
public class TimeUtils {
    /**
     * Parses a time string into a LocalTime object.
     *
     * @param timeStr the time string in HH:mm format.
     * @return the LocalTime object.
     */
    public static LocalTime parseTime(String timeStr) {
        return LocalTime.parse(timeStr);
    }
}
