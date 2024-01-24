package omo.sem.objects.device.tv.state;

import omo.sem.system.SmartHouse;
import omo.sem.objects.device.Device;
import omo.sem.objects.device.DeviceFixingState;

import java.util.logging.Logger;

/**
 * Class representing the TV fixing state.
 */
public class TvFixingState extends DeviceFixingState {
    // Logger
    private static final Logger log = Logger.getLogger(TvFixingState.class.getName());

    /**
     * Instantiates a new TV fixing state.
     *
     * @param device the device
     */
    public TvFixingState(Device device) {
        super(device);

        // Logging
        log.info(String.format("TV in room \"%s\" is being fixed now [%s]",
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
