package omo.sem.objects.device.airConditioner.state;

import omo.sem.objects.device.Device;
import omo.sem.objects.device.DeviceOffState;
import omo.sem.system.SmartHouse;

import java.util.logging.Logger;

/**
 * Class representing the AirConditioner off state.
 */
public class AirConditionerOffState extends DeviceOffState {
    // Logger
    private static final Logger log = Logger.getLogger(AirConditionerOffState.class.getName());

    /**
     * Instantiates a new AirConditioner off state.
     *
     * @param device the device
     */
    public AirConditionerOffState(Device device) {
        super(device);

        // Logging
        log.info(String.format("AirConditioner in room \"%s\" was turned off now[%s]",
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
