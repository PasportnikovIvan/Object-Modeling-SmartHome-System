package omo.sem.objects;

import omo.sem.objects.address.Address;
import omo.sem.objects.location.Location;
import omo.sem.objects.sensors.OutsideSensor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class representing a house.
 */
public class PhysicalHouse implements Location {
    private final Address address = new Address("Praha", 16000, "Terronska 1023");
    private final List<Floor> floors = new ArrayList<>();
    private final List<OutsideSensor> sensors = new ArrayList<>();

    /**
     * Adds a floor.
     * @param floor the floor
     */
    public void addFloor(Floor floor) {
        this.floors.add(floor);
    }

    /**
     * Adds an external sensor.
     * @param sensor the sensor
     */
    public void addSensor(OutsideSensor sensor) {
        this.sensors.add(sensor);
    }

    /**
     * Returns external sensors.
     * @return sensors
     */
    public List<OutsideSensor> getSensors() {
        return sensors;
    }

    /**
     * Returns the people outside.
     * @return Amount of People outside
     */
    // because normally only room have people and they instantly go to the work...
    public int getAmountOfPeopleOutside() {
        Random rand = new Random();
        int amountOfPeopleOutside = rand.nextInt((10 - 0) + 1) + 0;
        return amountOfPeopleOutside;
    }

    /**
     * Returns the name.
     * @return name
     */
    public String getName() {
        return "House";
    }

    /**
     * Returns the floors.
     * @return floors
     */
    public List<Floor> getFloors() {
        return floors;
    }

    /**
     * Returns the address.
     * @return address
     */
    public Address getAddress() {
        return address;
    }
}
