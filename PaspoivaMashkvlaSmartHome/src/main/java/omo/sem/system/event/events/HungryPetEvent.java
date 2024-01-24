package omo.sem.system.event.events;

import omo.sem.objects.location.Location;
import omo.sem.system.SmartHouse;
import omo.sem.system.event.Event;
import omo.sem.system.event.Source;

import java.util.logging.Logger;

/**
 * Class representing a hungry pet event.
 */
public class HungryPetEvent extends Event {
    // Logger
    private static final Logger log = Logger.getLogger(HungryPetEvent.class.getName());

    /**
     * Instantiates a new hungry pet event.
     *
     * @param source   the source
     * @param location the location
     */
    public HungryPetEvent(Source source, Location location) {
        super(source, location);

        log.info(String.format("Pet \"%s\" in room \"%s\": \"I'm HUUUNGRYYY RRRR >_<\" [%s]",
                source.getName(),
                location.getName(),
                SmartHouse.getInstance().getSimulation().getFormattedTime()));
    }
}