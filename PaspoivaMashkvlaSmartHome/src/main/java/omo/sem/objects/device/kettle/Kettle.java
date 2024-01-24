package omo.sem.objects.device.kettle;

import omo.sem.objects.device.Device;
import omo.sem.objects.device.Documentation;
import omo.sem.objects.resident.HouseResident;
import omo.sem.objects.resident.person.adult.Adult;
import omo.sem.objects.Room;
import omo.sem.objects.device.kettle.state.*;

/**
 * Class representing the Kettle.
 */
public class Kettle extends Device {
    /**
     * Instantiates a new Kettle.
     *
     * @param room the room
     * @param name the name
     */
    public Kettle(Room room, String name) {
        super(room, name);
        this.state = new KettleIdleState(this);
        this.fixingTimeInHours = 0.5;
        this.operatingTimeInHours = 5000;
        this.usageTimeInHour = 0.25;
        this.doc = new Documentation(this, this.fixingTimeInHours);
    }

    /**
     * Turns the Kettle on.
     */
    @Override
    public void on() {
        if (isOff()) {
            changeState(new KettleIdleState(this));
        }
    }

    /**
     * Turns the Kettle off.
     */
    @Override
    public void off() {
        if (isOn()) {
            changeState(new KettleOffState(this));
        }
    }

    /**
     * Use the Kettle.
     * @param houseResident resident that uses object
     */
    @Override
    public void use(HouseResident houseResident) {
        if (!isUsing() && !isBroken()) {
            if (Math.random() <= houseResident.getDeviceBreakingChance()) {
                changeState(new KettleBrokenState(this));
                return;
            }

            if (!(houseResident instanceof Adult)) return;

            setUser(houseResident);
            ((Adult) houseResident).useObject(this);
            changeState(new KettleUsingState(this));
        }
    }

    /**
     * UnUse the Kettle.
     * @param houseResident houseResident that unUses object
     */
    @Override
    public void unUse(HouseResident houseResident) {
        if (isUsing() && houseResident.equals(getUser())) {
            setUser(null);
            ((Adult) houseResident).unUseObject(this);
            changeState(new KettleIdleState(this));
        }
    }

    /**
     * Fix the Kettle.
     * @param person person that is fixing the device
     */
    @Override
    public void fix(Adult person) {
        if (isBroken()) {
            setUser(person);
            person.fixDevice(this);
            changeState(new KettleFixingState(this));
        }
    }

    /**
     * Complete fixing the Kettle.
     * @param person person that is fixing the device
     */
    @Override
    public void completeFixing(Adult person) {
        if (isFixing() && person.equals(getUser())) {
            setUser(null);
            person.completeFixingDevice(this);
            changeState(new KettleIdleState(this));
        }
    }

    /**
     * To break the Kettle.
     */
    @Override
    public void toBreak() {
        changeState(new KettleBrokenState(this));
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
