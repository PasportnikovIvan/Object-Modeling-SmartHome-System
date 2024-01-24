package omo.sem.objects.device.door.state;

import omo.sem.objects.device.Device;
import omo.sem.objects.device.DeviceFixingState;
import omo.sem.system.SmartHouse;

import java.util.logging.Logger;

/**
 * Class representing the Door fixing state.
 */
public class DoorFixingState extends DeviceFixingState {
    // Logger
    private static final Logger log = Logger.getLogger(DoorFixingState.class.getName());

    /**
     * Instantiates a new Door fixing state.
     *
     * @param device the device
     */
    public DoorFixingState(Device device) {
        super(device);

        // Logging
        log.info(String.format("Door in room \"%s\" is being fixed now [%s]",
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
