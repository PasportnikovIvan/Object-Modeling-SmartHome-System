package omo.sem.objects.device.kettle.state;

import omo.sem.objects.device.Device;
import omo.sem.objects.device.DeviceBrokenState;
import omo.sem.system.SmartHouse;
import omo.sem.system.event.events.BrokenDeviceEvent;

import java.util.logging.Logger;

/**
 * Class representing the Kettle broken state.
 */
public class KettleBrokenState extends DeviceBrokenState {
    // Logger
    private static final Logger log = Logger.getLogger(KettleBrokenState.class.getName());

    /**
     * Instantiates a new Kettle broken state.
     *
     * @param device the device
     */
    public KettleBrokenState(Device device) {
        super(device);

        // Event
        SmartHouse.getInstance().getEventDispatcher().dispatchEvent(new BrokenDeviceEvent(device, device.getRoom()), "global");

        // Logging
        log.info(String.format("Kettle in room \"%s\" is broken now [%s]",
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
