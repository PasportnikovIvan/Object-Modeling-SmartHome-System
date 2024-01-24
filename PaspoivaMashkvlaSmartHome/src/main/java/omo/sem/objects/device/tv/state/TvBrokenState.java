package omo.sem.objects.device.tv.state;

import omo.sem.system.SmartHouse;
import omo.sem.objects.device.Device;
import omo.sem.objects.device.DeviceBrokenState;
import omo.sem.system.event.events.BrokenDeviceEvent;
import java.util.logging.Logger;

/**
 * Class representing the TV broken state.
 */
public class TvBrokenState extends DeviceBrokenState {
    // Logger
    private static final Logger log = Logger.getLogger(TvBrokenState.class.getName());

    /**
     * Instantiates a new TV broken state.
     *
     * @param device the device
     */
    public TvBrokenState(Device device) {
        super(device);

        // Event
        SmartHouse.getInstance().getEventDispatcher().dispatchEvent(new BrokenDeviceEvent(device, device.getRoom()), "global");

        // Logging
        log.info(String.format("TV in room \"%s\" is broken now [%s]",
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
