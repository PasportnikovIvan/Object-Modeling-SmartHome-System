package omo.sem.objects.device.tv.state;

import omo.sem.system.SmartHouse;
import omo.sem.objects.device.Device;
import omo.sem.objects.device.DeviceOffState;

import java.util.logging.Logger;

/**
 * Class representing the TV off state.
 */
public class TvOffState extends DeviceOffState {
    // Logger
    private static final Logger log = Logger.getLogger(TvOffState.class.getName());

    /**
     * Instantiates a new TV off state.
     *
     * @param device the device
     */
    public TvOffState(Device device) {
        super(device);

        // Logging
        log.info(String.format("TV in room \"%s\" was turned off now[%s]",
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
