package omo.sem.objects.device.light.state;

import omo.sem.objects.device.Device;
import omo.sem.objects.device.DeviceFixingState;
import omo.sem.system.SmartHouse;

import java.util.logging.Logger;

/**
 * Class representing the Light fixing state.
 */
public class LightFixingState extends DeviceFixingState {
    // Logger
    private static final Logger log = Logger.getLogger(LightFixingState.class.getName());

    /**
     * Instantiates a new Light fixing state.
     *
     * @param device the device
     */
    public LightFixingState(Device device) {
        super(device);

        // Logging
        log.info(String.format("Light in room \"%s\" is being fixed now [%s]",
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
