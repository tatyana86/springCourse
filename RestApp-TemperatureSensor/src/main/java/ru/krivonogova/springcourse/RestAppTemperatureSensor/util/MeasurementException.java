package ru.krivonogova.springcourse.RestAppTemperatureSensor.util;

public class MeasurementException extends RuntimeException {
    public MeasurementException(String msg) {
        super(msg);
    }
}
