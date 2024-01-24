package omo.sem.objects.device.kettle.state;

import omo.sem.objects.device.Device;
import omo.sem.objects.device.DeviceUsingState;
import omo.sem.system.SmartHouse;

import java.util.logging.Logger;

/**
 * Class representing the Kettle using state.
 */
public class KettleUsingState extends DeviceUsingState {
    // Logger
    private static final Logger log = Logger.getLogger(KettleUsingState.class.getName());

    /**
     * Instantiates a new Kettle using state.
     *
     * @param device the device
     */
    public KettleUsingState(Device device) {
        super(device);
        this.ELECTRICITY_CONSUMPTION = 3;

        // Logging
        log.info(String.format("Kettle in room \"%s\" is being used now [%s]",
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
            device.changeState(new KettleBrokenState(device));
        }

        this.time += time;
        device.setTime(device.getTime() + time);

        // Consumption
        device.setCurrentElectricityConsumption(device.getCurrentElectricityConsumption()
                + (ELECTRICITY_CONSUMPTION / (3600D * 1000000000L)) * time);
    }
}
