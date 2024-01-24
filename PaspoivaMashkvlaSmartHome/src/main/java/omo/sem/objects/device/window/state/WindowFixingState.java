package omo.sem.objects.device.window.state;

import omo.sem.objects.device.Device;
import omo.sem.objects.device.DeviceFixingState;
import omo.sem.system.SmartHouse;

import java.util.logging.Logger;

/**
 * Class representing the Window fixing state.
 */
public class WindowFixingState extends DeviceFixingState {
    // Logger
    private static final Logger log = Logger.getLogger(WindowFixingState.class.getName());

    /**
     * Instantiates a new Window fixing state.
     *
     * @param device the device
     */
    public WindowFixingState(Device device) {
        super(device);

        // Logging
        log.info(String.format("Window in room \"%s\" is being fixed now [%s]",
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
