package omo.sem.objects.housePartsBuilder;

import omo.sem.objects.PhysicalHouse;
import omo.sem.objects.sensors.OutsideSensor;
import omo.sem.objects.sensors.factories.OutsideSensorFactory;

/**
 * Class for building the house.
 */
public class SmartHouseBuilder {

    private final PhysicalHouse physicalHouse = new PhysicalHouse();

    public SmartHouseBuilder() {
    }

    public SmartHouseBuilder addSensor(String type) {
        OutsideSensorFactory outsideSensorFactory = OutsideSensorFactory.getInstance();

        physicalHouse.addSensor((OutsideSensor) outsideSensorFactory.create(type, physicalHouse));
        return this;
    }

    /**
     * Adds a floor.
     * @param level floor's level
     * @return floor builder
     */
    public FloorBuilder addFloor(int level) {
        return new FloorBuilder(this, physicalHouse, level);
    }

    /**
     * Returns the house.
     * @return house
     */
    public PhysicalHouse getResult() {
        return this.physicalHouse;
    }
}
