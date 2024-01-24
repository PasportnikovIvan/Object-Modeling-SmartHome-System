package omo.sem.system.event.events;

import omo.sem.objects.location.Location;
import omo.sem.system.SmartHouse;
import omo.sem.system.event.Event;
import omo.sem.system.event.Source;

import java.util.logging.Logger;

/**
 * Class representing a caring child event.
 */
public class ChildEvent extends Event {
    // Logger
    private static final Logger log = Logger.getLogger(ChildEvent.class.getName());

    /**
     * Instantiates a new caring child event.
     *
     * @param source   the source
     * @param location the location
     */
    public ChildEvent(Source source, Location location) {
        super(source, location);

        log.info(String.format("Child \"%s\" in room \"%s\": \"Is needed to be care :'(\" [%s]",
                source.getName(),
                location.getName(),
                SmartHouse.getInstance().getSimulation().getFormattedTime()));
    }
}
