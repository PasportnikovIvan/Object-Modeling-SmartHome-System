package omo.sem.objects.sensors.factories;

import omo.sem.objects.Room;
import omo.sem.objects.location.Location;
import omo.sem.objects.sensors.LightSensor;
import omo.sem.objects.sensors.Sensor;
import omo.sem.objects.sensors.TemperatureSensor;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for creating inside sensors.
 */
public class InsideSensorFactory implements SensorFactory{

    private static InsideSensorFactory instance;
    private final List<Sensor> sensors = new ArrayList<>();

    private InsideSensorFactory() {}

    /**
     * Returns the instance.
     * @return instance
     */
    public static InsideSensorFactory getInstance() {
        if (instance == null) {
            instance = new InsideSensorFactory();
        }

        return instance;
    }

    /**
     * Creates a sensor.
     * @param type the type
     * @param room location
     * @return sensor
     */
    public Sensor create(String type, Location room) {
        Sensor sensor = switch (type) {
            case "TEMP" -> new TemperatureSensor((Room) room);
            case "LIGHT" -> new LightSensor((Room) room);
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
