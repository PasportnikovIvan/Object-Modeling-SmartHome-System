package omo.sem.system.event.events;

import omo.sem.objects.location.Location;
import omo.sem.system.event.Event;
import omo.sem.system.event.Source;

/**
 * Class representing a normal temperature event.
 */
public class NormalTemperatureEvent extends Event {
    /**
     * Instantiates a new normal temperature event.
     *
     * @param source   the source
     * @param location the location
     */
    public NormalTemperatureEvent(Source source, Location location) {
        super(source, location);
    }
}
