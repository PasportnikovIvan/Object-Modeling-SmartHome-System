package omo.sem.objects.device.kettle.state;

import omo.sem.objects.device.Device;
import omo.sem.objects.device.DeviceFixingState;
import omo.sem.system.SmartHouse;

import java.util.logging.Logger;

/**
 * Class representing the Kettle fixing state.
 */
public class KettleFixingState extends DeviceFixingState {
    // Logger
    private static final Logger log = Logger.getLogger(KettleFixingState.class.getName());

    /**
     * Instantiates a new Kettle fixing state.
     *
     * @param device the device
     */
    public KettleFixingState(Device device) {
        super(device);

        // Logging
        log.info(String.format("Kettle in room \"%s\" is being fixed now [%s]",
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
