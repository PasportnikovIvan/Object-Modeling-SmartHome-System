package omo.sem.objects.resident;

import omo.sem.system.SmartHouse;
import omo.sem.system.event.Source;
import omo.sem.system.simulation.TimeTracker;
import omo.sem.objects.UsableObject;
import omo.sem.objects.Room;
import java.util.logging.Logger;

/**
 * Abstract class representing house resident
 */
public abstract class HouseResident implements TimeTracker, Source {
    // Logger
    private static final Logger log = Logger.getLogger(HouseResident.class.getName());

    protected final String name;
    protected Room room;
    protected HouseResidentState state;
    protected UsableObject usingObject = null;
    protected long time = 0;

    // Constants
    protected double deviceBreakingChance = 0;

    public HouseResident(Room room, String name) {
        this.room = room;
        this.name = name;

        // Init
        SmartHouse.getInstance().getSimulation().subscribe(this);
    }

    /**
     * Returns the room in which house resident is located
     * @return room
     */
    public Room getRoom() {
        return room;
    }

    /**
     * Returns the name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Moves to another room
     * @param room the room
     */
    public void moveTo(Room room) {
        log.info(String.format("%s \"%s\" moved from room \"%s\" to room \"%s\" [%s]",
                this.getClass().getSimpleName(),
                this.getName(),
                this.getRoom().getName(),
                room.getName(),
                SmartHouse.getInstance().getSimulation().getFormattedTime()));

        this.room = room;
    }

    /**
     * Returns the chance of breaking the device
     * @return chance
     */
    public double getDeviceBreakingChance() {
        return deviceBreakingChance;
    }

    /**
     * Returns the using object
     * @return device/item
     */
    public UsableObject getUsableObject() {
        return usingObject;
    }

    /**
     * Changes the state
     * @param state the state to set
     */
    public void changeState(HouseResidentState state) {
        this.state = state;
    }

    /**
     * Returns the current state
     * @return state
     */
    public HouseResidentState getState() {
        return this.state;
    }
}
