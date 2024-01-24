package omo.sem.objects.device.light.state;

import omo.sem.objects.device.Device;
import omo.sem.objects.device.DeviceUsingState;
import omo.sem.system.SmartHouse;

import java.util.logging.Logger;

/**
 * Class representing the Light using state.
 */
public class LightUsingState extends DeviceUsingState {
    // Logger
    private static final Logger log = Logger.getLogger(LightUsingState.class.getName());

    /**
     * Instantiates a new Light using state.
     *
     * @param device the device
     */
    public LightUsingState(Device device) {
        super(device);
        this.ELECTRICITY_CONSUMPTION = 1;

        // Logging
        log.info(String.format("Light in room \"%s\" is on now [%s]",
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
            device.changeState(new LightBrokenState(device));
        }

        this.time += time;
        device.setTime(device.getTime() + time);

        // Consumption
        device.setCurrentElectricityConsumption(device.getCurrentElectricityConsumption()
                + (ELECTRICITY_CONSUMPTION / (3600D * 1000000000L)) * time);
    }
}
