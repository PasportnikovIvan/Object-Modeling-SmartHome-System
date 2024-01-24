package omo.sem.objects.device.speakers.state;

import omo.sem.objects.device.Device;
import omo.sem.objects.device.DeviceOffState;
import omo.sem.system.SmartHouse;

import java.util.logging.Logger;

/**
 * Class representing the Speakers off state.
 */
public class SpeakersOffState extends DeviceOffState {
    // Logger
    private static final Logger log = Logger.getLogger(SpeakersOffState.class.getName());

    /**
     * Instantiates a new Speakers off state.
     *
     * @param device the device
     */
    public SpeakersOffState(Device device) {
        super(device);

        // Logging
        log.info(String.format("Speakers in room \"%s\" was turned off now[%s]",
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
