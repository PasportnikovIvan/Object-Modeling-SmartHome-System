package omo.sem.objects.device.kettle.state;

import omo.sem.objects.device.Device;
import omo.sem.objects.device.DeviceOffState;
import omo.sem.system.SmartHouse;

import java.util.logging.Logger;

/**
 * Class representing the Kettle off state.
 */
public class KettleOffState extends DeviceOffState {
    // Logger
    private static final Logger log = Logger.getLogger(KettleOffState.class.getName());

    /**
     * Instantiates a new Kettle off state.
     *
     * @param device the device
     */
    public KettleOffState(Device device) {
        super(device);

        // Logging
        log.info(String.format("Kettle in room \"%s\" was turned off now[%s]",
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
