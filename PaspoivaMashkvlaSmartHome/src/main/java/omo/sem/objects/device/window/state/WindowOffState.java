package omo.sem.objects.device.window.state;

import omo.sem.objects.device.Device;
import omo.sem.objects.device.DeviceOffState;
import omo.sem.system.SmartHouse;

import java.util.logging.Logger;

/**
 * Class representing the Window off state.
 */
public class WindowOffState extends DeviceOffState {
    // Logger
    private static final Logger log = Logger.getLogger(WindowOffState.class.getName());

    /**
     * Instantiates a new Window off state.
     *
     * @param device the device
     */
    public WindowOffState(Device device) {
        super(device);

        // Logging
        log.info(String.format("Window in room \"%s\" was closed now[%s]",
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
