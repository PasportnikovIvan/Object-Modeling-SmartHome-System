package omo.sem.objects.device.airConditioner.state;

import omo.sem.objects.Room;
import omo.sem.objects.device.Device;
import omo.sem.objects.device.DeviceUsingState;
import omo.sem.objects.device.airConditioner.AirConditioner;
import omo.sem.objects.sensors.InsideSensor;
import omo.sem.objects.sensors.TemperatureSensor;
import omo.sem.system.SmartHouse;

import java.util.List;
import java.util.logging.Logger;

/**
 * Class representing the AirConditioner using state.
 */
public class AirConditionerUsingState extends DeviceUsingState {

    // Logger
    private static final Logger log = Logger.getLogger(AirConditionerUsingState.class.getName());

    /**
     * Instantiates a new AirConditioner using state.
     *
     * @param device the device
     */
    public AirConditionerUsingState(Device device) {
        super(device);
        this.ELECTRICITY_CONSUMPTION = 3.14;

        // Logging
        log.info(String.format("AirConditioner in room \"%s\" was turned on now [%s]",
                device.getRoom().getName(),
                SmartHouse.getInstance().getSimulation().getFormattedTime()));
    }

    /**
     * Update.
     * @param time the time
     */
    @Override
    public void update(long time) {
        // Wear out ime
        if (device.getTime() > device.getOperatingTimeInHours() * 3600L * 1000000000L) {
            device.changeState(new AirConditionerBrokenState(device));
        }

        this.time += time;
        device.setTime(device.getTime() + time);

        // Consumption
        device.setCurrentElectricityConsumption(device.getCurrentElectricityConsumption()
                + (ELECTRICITY_CONSUMPTION / (3600D * 1000000000L)) * time);


        double coolingPerHour = ((AirConditioner) device).getCOOLING_PER_HOUR();
        double heatingPerHour = ((AirConditioner) device).getHEATING_PER_HOUR();
        double temperature = device.getRoom().getTemperature();


        // Get the TemperatureSensor from the room
        TemperatureSensor tempSensor = null;
        for (InsideSensor sensor : device.getRoom().getSensors()) {
            if (sensor instanceof TemperatureSensor) {
                tempSensor = (TemperatureSensor) sensor;
                break;
            }
        }
        if (tempSensor != null) {
            // Check the status of the TemperatureSensor
            TemperatureSensor.TemperatureSensorStatus status = tempSensor.getStatus();
            if (status == TemperatureSensor.TemperatureSensorStatus.COLD) {
                // The room is too cold, activate the heater
                device.getRoom().setTemperature(device.getRoom().getTemperature()
                        + (heatingPerHour / (3600D * 1000000000)) * time);
            } else {
                // The room is too hot, activate the cooler
                device.getRoom().setTemperature(device.getRoom().getTemperature()
                        - (coolingPerHour / (3600D * 1000000000)) * time);
            }
        }
    }
}
