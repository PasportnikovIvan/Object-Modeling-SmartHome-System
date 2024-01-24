package omo.sem.system.event.events;

import omo.sem.objects.location.Location;
import omo.sem.system.SmartHouse;
import omo.sem.system.event.Event;
import omo.sem.system.event.Source;

import java.util.logging.Logger;

public class DayEvent extends Event {
    /**
     * Instantiates a new day event.
     *
     * @param source   the source
     * @param location the location
     */
    public DayEvent(Source source, Location location) {
        super(source, location);
    }
}
