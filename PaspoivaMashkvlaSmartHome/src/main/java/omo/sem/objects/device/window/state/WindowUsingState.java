package omo.sem.objects.device.window.state;

import omo.sem.objects.device.Device;
import omo.sem.objects.device.DeviceUsingState;
import omo.sem.system.SmartHouse;

import java.util.logging.Logger;

/**
 * Class representing the Window using state.
 */
public class WindowUsingState extends DeviceUsingState {
    // Logger
    private static final Logger log = Logger.getLogger(WindowUsingState.class.getName());

    /**
     * Instantiates a new Window using state.
     *
     * @param device the device
     */
    public WindowUsingState(Device device) {
        super(device);
        this.ELECTRICITY_CONSUMPTION = 0.5;

        // Logging
        log.info(String.format("Window in room \"%s\" was opened now [%s]",
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
            device.changeState(new WindowBrokenState(device));
        }

        this.time += time;
        device.setTime(device.getTime() + time);

        // Consumption
        device.setCurrentElectricityConsumption(device.getCurrentElectricityConsumption()
                + (ELECTRICITY_CONSUMPTION / (3600D * 1000000000L)) * time);
    }
}
