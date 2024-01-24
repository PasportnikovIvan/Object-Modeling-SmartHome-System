package omo.sem.system.event;
import omo.sem.objects.location.Location;

/**
 * Abstract class representing event.
 */
public abstract class Event {
    private final Source source;
    private final Location location;

    /**
     * Instantiates a new A event.
     *
     * @param source   the source
     * @param location the location
     */
    public Event(Source source, Location location) {
        this.source = source;
        this.location = location;
    }

    /**
     * Returns event's source.
     *
     * @return event's source
     */
    public Source getSource() {
        return source;
    }

    /**
     * Returns event's location.
     *
     * @return event's location
     */
    public Location getLocation() {
        return location;
    }
}
