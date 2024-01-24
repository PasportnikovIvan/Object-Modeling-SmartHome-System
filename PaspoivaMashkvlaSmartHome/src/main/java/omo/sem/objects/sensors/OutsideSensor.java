package omo.sem.objects.sensors;

import omo.sem.objects.PhysicalHouse;

import java.io.IOException;

public abstract class OutsideSensor extends Sensor {
    protected PhysicalHouse house;

    public OutsideSensor(PhysicalHouse house) {
        this.house = house;
    }

    /**
     * Returns the location.
     * @return house
     */
    public PhysicalHouse getHouse() {
        return house;
    }
}
