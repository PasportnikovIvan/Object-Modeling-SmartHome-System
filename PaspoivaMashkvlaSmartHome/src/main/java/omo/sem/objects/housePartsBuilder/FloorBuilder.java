package omo.sem.objects.housePartsBuilder;

import omo.sem.objects.Floor;
import omo.sem.objects.PhysicalHouse;

/**
 * Class for building the floors.
 */
public class FloorBuilder {
    private final Floor floor;
    private final SmartHouseBuilder houseBuilder;
    private final PhysicalHouse physicalHouse;

    public FloorBuilder(SmartHouseBuilder houseBuilder, PhysicalHouse physicalHouse, int level) {
        this.houseBuilder = houseBuilder;
        this.physicalHouse = physicalHouse;
        this.floor = new Floor(level);

        physicalHouse.addFloor(floor);
    }

    /**
     * Adds the room to the floor.
     * @param name the name
     * @return room builder
     */
    public RoomBuilder addRoom(String name) {
        return new RoomBuilder(this, floor, name);
    }

    /**
     * Ends a builder.
     * @return house builder
     */
    public SmartHouseBuilder end() {
        return houseBuilder;
    }
}
