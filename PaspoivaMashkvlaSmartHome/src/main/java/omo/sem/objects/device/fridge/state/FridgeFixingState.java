package omo.sem.objects.device.fridge.state;

import omo.sem.system.SmartHouse;
import omo.sem.objects.device.Device;
import omo.sem.objects.device.DeviceFixingState;

import java.util.logging.Logger;

/**
 * Class representing the Fridge fixing state.
 */
public class FridgeFixingState extends DeviceFixingState {
    // Logger
    private static final Logger log = Logger.getLogger(FridgeFixingState.class.getName());

    /**
     * Instantiates a new Fridge fixing state.
     *
     * @param device the device
     */
    public FridgeFixingState(Device device) {
        super(device);

        // Logging
        log.info(String.format("Fridge in room \"%s\" is being fixed now [%s]",
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
