package omo.sem.objects.device.tv;

import omo.sem.objects.device.Device;
import omo.sem.objects.device.Documentation;
import omo.sem.objects.resident.HouseResident;
import omo.sem.objects.resident.person.adult.Adult;
import omo.sem.objects.Room;
import omo.sem.objects.device.tv.state.*;

/**
 * Class representing the TV.
 */
public class Tv extends Device {
    /**
     * Instantiates a new TV.
     *
     * @param room the room
     * @param name the name
     */
    public Tv(Room room, String name) {
        super(room, name);
        this.state = new TvIdleState(this);
        this.fixingTimeInHours = 1.5;
        this.operatingTimeInHours = 4000;
        this.usageTimeInHour = 2;
        this.doc = new Documentation(this, this.fixingTimeInHours);
    }

    /**
     * Turns the TV on.
     */
    @Override
    public void on() {
        if (isOff()) {
            changeState(new TvIdleState(this));
        }
    }

    /**
     * Turns the TV off.
     */
    @Override
    public void off() {
        if (isOn()) {
            changeState(new TvOffState(this));
        }
    }

    /**
     * Use the TV.
     * @param houseResident resident that uses object
     */
    @Override
    public void use(HouseResident houseResident) {
        if (!isUsing() && !isBroken()) {
            if (Math.random() <= houseResident.getDeviceBreakingChance()) {
                changeState(new TvBrokenState(this));
                return;
            }

            if (!(houseResident instanceof Adult)) return;

            setUser(houseResident);
            ((Adult) houseResident).useObject(this);
            changeState(new TvUsingState(this));
        }
    }

    /**
     * UnUse the TV.
     * @param houseResident houseResident that unUses object
     */
    @Override
    public void unUse(HouseResident houseResident) {
        if (isUsing() && houseResident.equals(getUser())) {
            setUser(null);
            ((Adult) houseResident).unUseObject(this);
            changeState(new TvIdleState(this));
        }
    }

    /**
     * Fix the TV.
     * @param person person that is fixing the device
     */
    @Override
    public void fix(Adult person) {
        if (isBroken()) {
            setUser(person);
            person.fixDevice(this);
            changeState(new TvFixingState(this));
        }
    }

    /**
     * Complete fixing the TV.
     * @param person person that is fixing the device
     */
    @Override
    public void completeFixing(Adult person) {
        if (isFixing() && person.equals(getUser())) {
            setUser(null);
            person.completeFixingDevice(this);
            changeState(new TvIdleState(this));
        }
    }

    /**
     * To break the TV.
     */
    @Override
    public void toBreak() {
        changeState(new TvBrokenState(this));
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
