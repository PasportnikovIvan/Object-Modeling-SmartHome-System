package omo.sem.objects.device.airConditioner;

import omo.sem.objects.device.Device;
import omo.sem.objects.device.Documentation;
import omo.sem.objects.resident.HouseResident;
import omo.sem.objects.resident.person.adult.Adult;
import omo.sem.objects.Room;
import omo.sem.objects.device.airConditioner.state.*;

/**
 * Class representing the AirConditioner.
 */
public class AirConditioner extends Device {

    private final double COOLING_PER_HOUR = 3;
    private final double HEATING_PER_HOUR = 5;

    /**
     * Instantiates a new AirConditioner.
     *
     * @param room the room
     * @param name the name
     */
    public AirConditioner(Room room, String name) {
        super(room, name);
        this.state = new AirConditionerIdleState(this);
        this.fixingTimeInHours = 1;
        this.operatingTimeInHours = 2000;
        this.usageTimeInHour = 2;
        this.doc = new Documentation(this, this.fixingTimeInHours);
    }

    /**
     * Gets cooling per hour.
     *
     * @return the cooling per hour
     */
    public double getCOOLING_PER_HOUR() {
        return COOLING_PER_HOUR;
    }

    /**
     * Gets heating per hour.
     *
     * @return the heating per hour
     */
    public double getHEATING_PER_HOUR() {
        return HEATING_PER_HOUR;
    }

    /**
     * Turns the AirConditioner on.
     */
    @Override
    public void on() {
        if (isOff()) {
            changeState(new AirConditionerIdleState(this));
        }
    }

    /**
     * Turns the AirConditioner off.
     */
    @Override
    public void off() {
        if (isOn()) {
            changeState(new AirConditionerOffState(this));
        }
    }

    /**
     * Use the AirConditioner.
     * @param houseResident resident that uses object
     */
    @Override
    public void use(HouseResident houseResident) {
        if (!isUsing() && !isBroken()) {
            if (Math.random() <= houseResident.getDeviceBreakingChance()) {
                changeState(new AirConditionerBrokenState(this));
                return;
            }

            if (!(houseResident instanceof Adult)) return;

            setUser(houseResident);
            ((Adult) houseResident).useObject(this);
            changeState(new AirConditionerUsingState(this));
        }
    }

    /**
     * UnUse the AirConditioner.
     * @param houseResident houseResident that unUses object
     */
    @Override
    public void unUse(HouseResident houseResident) {
        if (isUsing() && houseResident.equals(getUser())) {
            setUser(null);
            ((Adult) houseResident).unUseObject(this);
            changeState(new AirConditionerIdleState(this));
        }
    }

    /**
     * Fix the AirConditioner.
     * @param person person that is fixing the device
     */
    @Override
    public void fix(Adult person) {
        if (isBroken()) {
            setUser(person);
            person.fixDevice(this);
            changeState(new AirConditionerFixingState(this));
        }
    }

    /**
     * Complete fixing the AirConditioner.
     * @param person person that is fixing the device
     */
    @Override
    public void completeFixing(Adult person) {
        if (isFixing() && person.equals(getUser())) {
            setUser(null);
            person.completeFixingDevice(this);
            changeState(new AirConditionerIdleState(this));
        }
    }

    /**
     * To break the AirConditioner.
     */
    @Override
    public void toBreak() {
        changeState(new AirConditionerBrokenState(this));
    }

    /**
     * Update.
     * @param time the time
     */
    @Override
    public void update(long time) {
        this.time += time;
        state.update(time);
    }
}
