package omo.sem.system.event.events;

import omo.sem.system.event.Event;
import omo.sem.system.event.Source;
import omo.sem.objects.location.Location;

/**
 * Class representing a device broken event.
 */
public class BrokenDeviceEvent extends Event {
    /**
     * Instantiates a new device is broken event.
     *
     * @param source   the source
     * @param location the location
     */
    public BrokenDeviceEvent(Source source, Location location) {
        super(source, location);
    }
}
