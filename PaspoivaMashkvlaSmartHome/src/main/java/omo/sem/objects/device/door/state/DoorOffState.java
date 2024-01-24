package omo.sem.objects.device.door.state;

import omo.sem.objects.device.Device;
import omo.sem.objects.device.DeviceOffState;
import omo.sem.system.SmartHouse;

import java.util.logging.Logger;

/**
 * Class representing the closed Door.
 */
public class DoorOffState extends DeviceOffState {
    // Logger
    private static final Logger log = Logger.getLogger(DoorOffState.class.getName());

    /**
     * Instantiates a new closed Door.
     *
     * @param device the device
     */
    public DoorOffState(Device device) {
        super(device);

        // Logging
        log.info(String.format("Door in room \"%s\" was closed now[%s]",
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
