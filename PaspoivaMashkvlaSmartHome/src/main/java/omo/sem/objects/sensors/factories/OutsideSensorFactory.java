package omo.sem.objects.sensors.factories;

import omo.sem.objects.PhysicalHouse;
import omo.sem.objects.location.Location;
import omo.sem.objects.sensors.MovementSensor;
import omo.sem.objects.sensors.Sensor;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for creating outside sensors.
 */
public class OutsideSensorFactory implements SensorFactory{
    private static OutsideSensorFactory instance;
    private final List<Sensor> sensors = new ArrayList<>();

    private OutsideSensorFactory() {}

    /**
     * Returns the instance.
     * @return instance
     */
    public static OutsideSensorFactory getInstance() {
        if (instance == null) {
            instance = new OutsideSensorFactory();
        }

        return instance;
    }

    /**
     * Creates a sensor.
     * @param type the type
     * @param house location
     * @return sensor
     */
    public Sensor create(String type, Location house) {
        Sensor sensor = switch (type) {
            case "MOVEMENT" -> new MovementSensor((PhysicalHouse) house);
            default -> throw new IllegalArgumentException("Type of sensor " + type + " was not found.");
        };

        sensors.add(sensor);
        return sensor;
    }

    /**
     * Returns the sensors.
     * @return sensors
     */
    public List<Sensor> getSensors() {
        return sensors;
    }
}
