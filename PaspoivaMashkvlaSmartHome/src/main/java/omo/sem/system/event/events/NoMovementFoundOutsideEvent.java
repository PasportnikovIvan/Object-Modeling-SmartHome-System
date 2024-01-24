package omo.sem.system.event.events;

import omo.sem.objects.location.Location;
import omo.sem.system.event.Event;
import omo.sem.system.event.Source;

public class NoMovementFoundOutsideEvent extends Event {
    /**
     * No one is outside.
     *
     * @param source   the source
     * @param location the location
     */
    public NoMovementFoundOutsideEvent(Source source, Location location) {
        super(source, location);
    }
}
