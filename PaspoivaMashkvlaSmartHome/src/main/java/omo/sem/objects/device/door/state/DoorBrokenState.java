package omo.sem.objects.device.door.state;

import omo.sem.objects.device.Device;
import omo.sem.objects.device.DeviceBrokenState;
import omo.sem.system.SmartHouse;
import omo.sem.system.event.events.BrokenDeviceEvent;

import java.util.logging.Logger;

/**
 * Class representing the Door broken state.
 */
public class DoorBrokenState extends DeviceBrokenState {
    // Logger
    private static final Logger log = Logger.getLogger(DoorBrokenState.class.getName());

    /**
     * Instantiates a new Door broken state.
     *
     * @param device the device
     */
    public DoorBrokenState(Device device) {
        super(device);

        // Event
        SmartHouse.getInstance().getEventDispatcher().dispatchEvent(new BrokenDeviceEvent(device, device.getRoom()), "global");

        // Logging
        log.info(String.format("Door in room \"%s\" is broken now [%s]",
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
