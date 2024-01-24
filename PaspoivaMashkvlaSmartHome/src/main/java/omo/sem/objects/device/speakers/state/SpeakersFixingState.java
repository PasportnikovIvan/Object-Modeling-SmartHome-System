package omo.sem.objects.device.speakers.state;

import omo.sem.objects.device.Device;
import omo.sem.objects.device.DeviceFixingState;
import omo.sem.system.SmartHouse;

import java.util.logging.Logger;

/**
 * Class representing the Speakers fixing state.
 */
public class SpeakersFixingState extends DeviceFixingState {
    // Logger
    private static final Logger log = Logger.getLogger(SpeakersFixingState.class.getName());

    /**
     * Instantiates a new Speakers fixing state.
     *
     * @param device the device
     */
    public SpeakersFixingState(Device device) {
        super(device);

        // Logging
        log.info(String.format("Speakers in room \"%s\" is being fixed now [%s]",
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
