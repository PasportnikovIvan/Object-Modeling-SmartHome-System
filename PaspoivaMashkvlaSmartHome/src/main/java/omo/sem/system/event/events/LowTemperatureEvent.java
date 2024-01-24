package omo.sem.system.event.events;

import omo.sem.objects.location.Location;
import omo.sem.system.event.Event;
import omo.sem.system.event.Source;

/**
 * Class representing a low temperature event.
 */
public class LowTemperatureEvent extends Event {
    /**
     * Instantiates a new low temperature event.
     *
     * @param source   the source
     * @param location the location
     */
    public LowTemperatureEvent(Source source, Location location) {
        super(source, location);
    }
}