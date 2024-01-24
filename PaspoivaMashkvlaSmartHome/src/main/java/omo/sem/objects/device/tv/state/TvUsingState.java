package omo.sem.objects.device.tv.state;

import omo.sem.system.SmartHouse;
import omo.sem.objects.device.Device;
import omo.sem.objects.device.DeviceUsingState;

import java.util.logging.Logger;

/**
 * Class representing the TV using state.
 */
public class TvUsingState extends DeviceUsingState {
    // Logger
    private static final Logger log = Logger.getLogger(TvUsingState.class.getName());

    /**
     * Instantiates a new TV using state.
     *
     * @param device the device
     */
    public TvUsingState(Device device) {
        super(device);
        this.ELECTRICITY_CONSUMPTION = 2.5;

        // Logging
        log.info(String.format("TV in room \"%s\" is being used now [%s]",
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
            device.changeState(new TvBrokenState(device));
        }

        this.time += time;
        device.setTime(device.getTime() + time);

        // Consumption
        device.setCurrentElectricityConsumption(device.getCurrentElectricityConsumption()
                + (ELECTRICITY_CONSUMPTION / (3600D * 1000000000L)) * time);
    }
}
