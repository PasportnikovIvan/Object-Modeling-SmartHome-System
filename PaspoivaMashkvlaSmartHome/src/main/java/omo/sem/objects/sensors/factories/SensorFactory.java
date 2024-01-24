package omo.sem.objects.sensors.factories;

import omo.sem.objects.location.Location;
import omo.sem.objects.sensors.Sensor;

import java.util.List;

/**
 * Interface representing a sensor factory.
 */
public interface SensorFactory{
    /**
     * Creates a sensor.
     * @param type the type
     * @param location the location
     * @return sensor
     */
    Sensor create(String type, Location location);

    /**
     * Returns the sensors.
     * @return sensors
     */
    List<Sensor> getSensors();
}
