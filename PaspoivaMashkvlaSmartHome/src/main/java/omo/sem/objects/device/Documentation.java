package omo.sem.objects.device;
import omo.sem.system.SmartHouse;

import java.util.logging.Logger;

/**
 * Class representing device's documentation.
 */
public class Documentation {
    // Logger
    private static final Logger log = Logger.getLogger(Documentation.class.getName());

    private final Device device;
    private final double fixingTimeInHours;

    /**
     * Instantiates a new Documentation.
     *
     * @param device            the device
     * @param fixingTimeInHours the fixing time in hours
     */
    public Documentation(Device device, double fixingTimeInHours) {
        this.device = device;
        this.fixingTimeInHours = fixingTimeInHours;
    }

    /**
     * Just logging...
     *
     * @param device that is being fixed
     */
    public void fixDevice(Device device) {
        log.info(String.format("Reading manual for the device \"%s\"... [%s]",
                device.getName(),
                SmartHouse.getInstance().getSimulation().getFormattedTime()));
    }
}
