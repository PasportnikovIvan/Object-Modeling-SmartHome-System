package omo.sem.objects.device.light.state;

import omo.sem.objects.device.Device;
import omo.sem.objects.device.DeviceBrokenState;
import omo.sem.system.SmartHouse;
import omo.sem.system.event.events.BrokenDeviceEvent;

import java.util.logging.Logger;

/**
 * Class representing the Light broken state.
 */
public class LightBrokenState extends DeviceBrokenState {
    // Logger
    private static final Logger log = Logger.getLogger(LightBrokenState.class.getName());

    /**
     * Instantiates a new Light broken state.
     *
     * @param device the device
     */
    public LightBrokenState(Device device) {
        super(device);

        // Event
        SmartHouse.getInstance().getEventDispatcher().dispatchEvent(new BrokenDeviceEvent(device, device.getRoom()), "global");

        // Logging
        log.info(String.format("Light in room \"%s\" is broken now [%s]",
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
