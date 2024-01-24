package omo.sem.objects.device.light;

import omo.sem.objects.device.Device;
import omo.sem.objects.device.Documentation;
import omo.sem.objects.resident.HouseResident;
import omo.sem.objects.resident.person.adult.Adult;
import omo.sem.objects.Room;
import omo.sem.objects.device.light.state.*;

/**
 * Class representing the Light.
 */
public class Light extends Device {
    /**
     * Instantiates a new Light.
     *
     * @param room the room
     * @param name the name
     */
    public Light(Room room, String name) {
        super(room, name);
        this.state = new LightIdleState(this);
        this.fixingTimeInHours = 2;
        this.operatingTimeInHours = 9000;
        this.usageTimeInHour = 9;
        this.doc = new Documentation(this, this.fixingTimeInHours);
    }

    /**
     * Turns the Light on.
     */
    @Override
    public void on() {
        if (isOff()) {
            changeState(new LightIdleState(this));
        }
    }

    /**
     * Turns the Light off.
     */
    @Override
    public void off() {
        if (isOn()) {
            changeState(new LightOffState(this));
        }
    }

    /**
     * Use the Light.
     * @param houseResident resident that uses object
     */
    @Override
    public void use(HouseResident houseResident) {
        if (!isUsing() && !isBroken()) {
            if (Math.random() <= houseResident.getDeviceBreakingChance()) {
                changeState(new LightBrokenState(this));
                return;
            }

            if (!(houseResident instanceof Adult)) return;

            setUser(houseResident);
            ((Adult) houseResident).useObject(this);
            changeState(new LightUsingState(this));
        }
    }

    /**
     * UnUse the Light.
     * @param houseResident houseResident that unUses object
     */
    @Override
    public void unUse(HouseResident houseResident) {
        if (isUsing() && houseResident.equals(getUser())) {
            setUser(null);
            ((Adult) houseResident).unUseObject(this);
            changeState(new LightIdleState(this));
        }
    }

    /**
     * Fix the Light.
     * @param person person that is fixing the device
     */
    @Override
    public void fix(Adult person) {
        if (isBroken()) {
            setUser(person);
            person.fixDevice(this);
            changeState(new LightFixingState(this));
        }
    }

    /**
     * Complete fixing the Light.
     * @param person person that is fixing the device
     */
    @Override
    public void completeFixing(Adult person) {
        if (isFixing() && person.equals(getUser())) {
            setUser(null);
            person.completeFixingDevice(this);
            changeState(new LightIdleState(this));
        }
    }

    /**
     * To break the Light.
     */
    @Override
    public void toBreak() {
        changeState(new LightBrokenState(this));
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
