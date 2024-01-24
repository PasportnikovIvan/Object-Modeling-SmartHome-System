package omo.sem.objects.item;

import omo.sem.objects.Room;

/**
 * Class representing a bike.
 */
public class Bicycle extends Item {
    public Bicycle(Room room, String name) {
        super(room, name);
        this.operatingTimeInHours = 1000;
        this.usageTimeInHour = 1;
        this.hungerPerHour = 0;
        this.leisurePerHour = 35;
    }
}