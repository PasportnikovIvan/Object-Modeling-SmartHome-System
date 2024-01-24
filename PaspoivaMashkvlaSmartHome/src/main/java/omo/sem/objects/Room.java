package omo.sem.objects;

import omo.sem.objects.device.Device;
import omo.sem.objects.item.Item;
import omo.sem.objects.resident.HouseResident;
import omo.sem.objects.location.Location;
import omo.sem.objects.resident.person.adult.Adult;
import omo.sem.objects.sensors.InsideSensor;
import omo.sem.system.SmartHouse;
import omo.sem.system.simulation.TimeTracker;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Class representing a room.
 */
public class Room implements Location, TimeTracker {

    private static final Logger log = Logger.getLogger(Room.class.getName());

    private final String name;
    private final List<HouseResident> houseResidents = new ArrayList<>();
    private final List<Device> devices = new ArrayList<>();
    private final List<Item> items = new ArrayList<>();
    private final List<InsideSensor> sensors = new ArrayList<>();

    private double temperature = 22;
    private final double DECREASE_TEMP_RATE_PER_HOUR = 2;

    public Room(String name) {
        this.name = name;

        //init for the temperature
        SmartHouse.getInstance().getSimulation().subscribe(this);
    }

    /**
     * Adds an houseResident.
     * @param houseResident the houseResident
     */
    public void addHouseResident(HouseResident houseResident) {
        this.houseResidents.add(houseResident);
    }

    /**
     * Updating the temperature of the room.
     * @param time the time
     */
    public void update(long time) {
        this.temperature -= time * (DECREASE_TEMP_RATE_PER_HOUR / (3600D * 1000000000));
    }

    /**
     * Returns the temperature.
     * @return temperature
     */
    public double getTemperature() {
        return this.temperature;
    }

    /**
     * Sets the temperature.
     * @param temperature the temperature
     */
    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    /**
     * Adds a device.
     * @param device the device
     */
    public void addDevice(Device device) {
        this.devices.add(device);
    }

    /**
     * Adds an internal sensor.
     * @param sensor the sensor
     */
    public void addSensor(InsideSensor sensor) {
        this.sensors.add(sensor);
    }

    /**
     * Adds an item.
     * @param item the item
     */
    public void addItem(Item item) {
        this.items.add(item);
    }

    /**
     * Returns the name.
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the devices.
     * @return devices
     */
    public List<Device> getDevices() {
        return devices;
    }

    /**
     * Returns the internal sensors.
     * @return sensors
     */
    public List<InsideSensor> getSensors() {
        return sensors;
    }

    /**
     * Returns the items.
     * @return items
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     * Chilling in the room
     * @param adult the adult
     */
    public void chill(Adult adult) {
        adult.chillInRoom(this);
    }
}