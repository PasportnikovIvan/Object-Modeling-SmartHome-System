package omo.sem.objects.device.fridge.state;

import omo.sem.system.SmartHouse;
import omo.sem.objects.device.Device;
import omo.sem.objects.device.DeviceOffState;

import java.util.logging.Logger;

/**
 * Class representing the Fridge off state.
 */
public class FridgeOffState extends DeviceOffState {
    // Logger
    private static final Logger log = Logger.getLogger(FridgeOffState.class.getName());

    /**
     * Instantiates a new Fridge off state.
     *
     * @param device the device
     */
    public FridgeOffState(Device device) {
        super(device);

        // Logging
        log.info(String.format("Fridge in room \"%s\" was turned off now[%s]",
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
