package omo.sem.objects.device.airConditioner.state;

import omo.sem.objects.device.Device;
import omo.sem.objects.device.DeviceFixingState;
import omo.sem.system.SmartHouse;

import java.util.logging.Logger;

/**
 * Class representing the AirConditioner fixing state.
 */
public class AirConditionerFixingState extends DeviceFixingState {
    // Logger
    private static final Logger log = Logger.getLogger(AirConditionerFixingState.class.getName());

    /**
     * Instantiates a new AirConditioner fixing state.
     *
     * @param device the device
     */
    public AirConditionerFixingState(Device device) {
        super(device);

        // Logging
        log.info(String.format("AirConditioner in room \"%s\" is being fixed now [%s]",
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
