package omo.sem.objects.device.light.state;

import omo.sem.objects.device.Device;
import omo.sem.objects.device.DeviceOffState;
import omo.sem.system.SmartHouse;

import java.util.logging.Logger;

/**
 * Class representing the Light off state.
 */
public class LightOffState extends DeviceOffState {
    // Logger
    private static final Logger log = Logger.getLogger(LightOffState.class.getName());

    /**
     * Instantiates a new Light off state.
     *
     * @param device the device
     */
    public LightOffState(Device device) {
        super(device);

        // Logging
        log.info(String.format("Light in room \"%s\" was turned off now[%s]",
                device.getRoom().getName(),
                SmartHouse.getInstance().getSimulation().getFormattedTime()));
    }

    /**
     * Update.
     * @param time the time
     */
    @Override
    public void update(long time) {
        this.time += time;
    }
}
