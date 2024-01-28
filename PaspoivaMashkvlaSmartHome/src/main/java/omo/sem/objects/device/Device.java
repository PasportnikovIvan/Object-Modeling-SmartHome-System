package omo.sem.objects.device;

import omo.sem.objects.resident.HouseResident;
import omo.sem.system.SmartHouse;
import omo.sem.system.event.Source;
import omo.sem.system.simulation.TimeTracker;
import omo.sem.objects.UsableObject;
import omo.sem.objects.resident.person.adult.Adult;
import omo.sem.objects.Room;

/**
 * Abstract class representing device.
 */
public abstract class Device implements TimeTracker, Source, UsableObject {
    protected Room room;
    protected DeviceState state;
    protected String name;
    protected HouseResident user = null;
    protected long time = 0;

// Constants
    protected int operatingTimeInHours = 0;
    protected double usageTimeInHour = 0;
    protected Documentation doc;

// Consumption
    protected double currentElectricityConsumption = 0;
    protected double lastElectricityConsumption = 0;
    protected double fixingTimeInHours = 0;

    /**
     * Instantiates a new device.
     *
     * @param room the room
     * @param name the name
     */
    public Device(Room room, String name) {
        this.room = room;
        this.name = name;

        // Init
        SmartHouse.getInstance().getSimulation().subscribe(this);
    }

    /**
     * Changes device's state (Using, Fixing, Broken, Idle, On, Off).
     *
     * @param state state to apply
     */
    public void changeState(DeviceState state) {
        this.state = state;
    }

    /**
     * Returns the room in which device is located.
     *
     * @return room
     */
    public Room getRoom() {
        return room;
    }

    /**
     * Returns device's documentation.
     *
     * @return documentation documentation
     */
    public Documentation getDocumentation() {
        return doc;
    }

    /**
     * Returns current electricity consumption.
     *
     * @return consumption current electricity consumption
     */
    public double getCurrentElectricityConsumption() {
        return currentElectricityConsumption;
    }

    /**
     * Sets electricity consumption.
     *
     * @param consumption consumption to set
     */
    public void setCurrentElectricityConsumption(double consumption) {
        currentElectricityConsumption = consumption;
    }

    /**
     * Calculates electricity consumption.
     *
     * @return consumption double
     */
    public double calculateElectricityConsumption() {
        double result = currentElectricityConsumption - lastElectricityConsumption;
        lastElectricityConsumption = currentElectricityConsumption;

        return result;
    }

    /**
     * Returns current device's time.
     *
     * @return time
     */
    public long getTime() {
        return time;
    }

    /**
     * Sets device's time.
     *
     * @param time time to set
     */
    public void setTime(long time) {
        this.time = time;
    }

    /**
     * Returns time that device can be used before breaking.
     * @return time
     */
    public int getOperatingTimeInHours() {
        return operatingTimeInHours;
    }

    /**
     * Returns time which is needed to fix the device.
     *
     * @return time to fix
     */
    public double getFixingTimeInHours() {
        return fixingTimeInHours;
    }

    /**
     * Returns device's current state.
     *
     * @return state
     */
    public DeviceState getState() {
        return state;
    }

    /**
     * Returns houseResident that is currently using the device.
     *
     * @return user user
     */
    public HouseResident getUser() {
        return user;
    }

    /**
     * Sets a new user for the device.
     *
     * @param houseResident user to set
     */
    public void setUser(HouseResident houseResident) {
        user = houseResident;
    }

    /**
     * Checks if device is turned on.
     * @return true if turned on
     */
    public boolean isOn() {
        return state instanceof DeviceIdleState;
    }

    /**
     * Checks if device is turned off.
     * @return true if turned off
     */
    public boolean isOff() {
        return state instanceof DeviceOffState;
    }

    /**
     * Checks if device is broken.
     * @return true if broken
     */
    public boolean isBroken() {
        return state instanceof DeviceBrokenState;
    }

    /**
     * Checks if device is being fixed.
     * @return true if being fixed
     */
    public boolean isFixing() {
        return state instanceof DeviceFixingState;
    }

    /**
     * Returns device's name (Fridge, Oven, etc.).
     * @return name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Returns time that device is supposed to be used.
     * @return time
     */
    @Override
    public double getUsageTimeInHour() {
        return usageTimeInHour;
    }

    /**
     * Checks if device is being used.
     * @return true if being used
     */
    @Override
    public boolean isUsing() {
        return state instanceof DeviceUsingState;
    }

    /**
     * Turns the device on.
     */
    public abstract void on();

    /**
     * Turns the device off.
     */
    public abstract void off();

    /**
     * Fix device.
     *
     * @param person person that is fixing the device
     */
    public abstract void fix(Adult person);

    /**
     * Complete fixing the device.
     *
     * @param person person that is fixing the device
     */
    public abstract void completeFixing(Adult person);

    /**
     * Break the device.
     */
    public abstract void toBreak();
}
