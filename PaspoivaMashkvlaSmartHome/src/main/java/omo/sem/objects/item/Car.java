package omo.sem.objects.item;

import omo.sem.objects.Room;

/**
 * Class representing a car.
 */
public class Car extends Item {
    public Car(Room room, String name) {
        super(room, name);
        this.operatingTimeInHours = 2000;
        this.usageTimeInHour = 2;
        this.hungerPerHour = 0;
        this.leisurePerHour = 40;
    }
}
