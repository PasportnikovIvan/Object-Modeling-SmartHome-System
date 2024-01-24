package omo.sem.objects.item;

import omo.sem.objects.Room;
import omo.sem.objects.UsableObject;
import omo.sem.objects.resident.HouseResident;
import omo.sem.objects.resident.person.adult.Adult;

/**
 * Abstract class representing an item.
 */
public abstract class Item implements UsableObject {
    // Constants
    protected int operatingTimeInHours = 0;
    protected double usageTimeInHour = 0;
    protected int hungerPerHour = 0;
    protected int leisurePerHour = 0;

    // References
    private final String name;
    private final Room room;

    // State
    private boolean isUsing = false;

    public Item(Room room, String name) {
        this.room = room;
        this.name = name;
    }

    /**
     * Returns the room in which item is located.
     * @return room
     */
    public Room getRoom() {
        return room;
    }

    /**
     * Returns the name.
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the time that item is supposed to be used.
     * @return time
     */
    @Override
    public double getUsageTimeInHour() {
        return usageTimeInHour;
    }

    /**
     * Uses the item.
     * @param houseResident houseResident that uses object
     */
    @Override
    public void use(HouseResident houseResident) {
        ((Adult) houseResident).useObject(this);
        isUsing = true;
    }

    /**
     * UnUses the item.
     * @param houseResident houseResident that unUses object
     */
    @Override
    public void unUse(HouseResident houseResident) {
        ((Adult) houseResident).unUseObject(this);
        isUsing = false;
    }

    /**
     * Checks if item is being used.
     * @return true if being used
     */
    @Override
    public boolean isUsing() {
        return isUsing;
    }
}
