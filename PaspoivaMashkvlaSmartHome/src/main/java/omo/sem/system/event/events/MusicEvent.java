package omo.sem.system.event.events;

import omo.sem.objects.location.Location;
import omo.sem.system.SmartHouse;
import omo.sem.system.event.Event;
import omo.sem.system.event.Source;

import java.util.logging.Logger;

/**
 * Class representing a music event.
 */
public class MusicEvent extends Event {
    // Logger
    private static final Logger log = Logger.getLogger(MusicEvent.class.getName());

    /**
     * Instantiates a new music event.
     *
     * @param source   the source
     * @param location the location
     */
    public MusicEvent(Source source, Location location) {
        super(source, location);

        log.info(String.format("Music is playing now \"%s\" in room \"%s\": \"Chilling rn fr\" [%s]",
                source.getName(),
                location.getName(),
                SmartHouse.getInstance().getSimulation().getFormattedTime()));
    }
}