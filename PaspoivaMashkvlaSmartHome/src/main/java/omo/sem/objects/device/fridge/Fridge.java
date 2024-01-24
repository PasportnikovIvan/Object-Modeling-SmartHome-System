package omo.sem.objects.device.fridge;

import omo.sem.objects.device.Device;
import omo.sem.objects.device.Documentation;
import omo.sem.objects.resident.HouseResident;
import omo.sem.objects.resident.person.adult.Adult;
import omo.sem.objects.Room;
import omo.sem.objects.device.fridge.state.*;

/**
 * Class representing the Fridge.
 */
public class Fridge extends Device {
    /**
     * Instantiates a new Fridge.
     *
     * @param room the room
     * @param name the name
     */
    public Fridge(Room room, String name) {
        super(room, name);
        this.state = new FridgeIdleState(this);
        this.fixingTimeInHours = 1;
        this.operatingTimeInHours = 2500;
        this.usageTimeInHour = 0.15;
        this.doc = new Documentation(this, this.fixingTimeInHours);
    }

    /**
     * Turns the fridge on.
     */
    @Override
    public void on() {
        if (isOff()) {
            changeState(new FridgeIdleState(this));
        }
    }

    /**
     * Turns the fridge off.
     */
    @Override
    public void off() {
        if (isOn()) {
            changeState(new FridgeOffState(this));
        }
    }

    /**
     * Use the fridge.
     * @param houseResident resident that uses object
     */
    @Override
    public void use(HouseResident houseResident) {
        if (!isUsing() && !isBroken()) {
            if (Math.random() <= houseResident.getDeviceBreakingChance()) {
                changeState(new FridgeBrokenState(this));
                return;
            }

            if (!(houseResident instanceof Adult)) return;

            setUser(houseResident);
            ((Adult) houseResident).useObject(this);
            changeState(new FridgeUsingState(this));
        }
    }

    /**
     * UnUse the fridge.
     * @param houseResident houseResident that unUses object
     */
    @Override
    public void unUse(HouseResident houseResident) {
        if (isUsing() && houseResident.equals(getUser())) {
            setUser(null);
            ((Adult) houseResident).unUseObject(this);
            changeState(new FridgeIdleState(this));
        }
    }

    /**
     * Fix the fridge.
     * @param person person that is fixing the device
     */
    @Override
    public void fix(Adult person) {
        if (isBroken()) {
            setUser(person);
            person.fixDevice(this);
            changeState(new FridgeFixingState(this));
        }
    }

    /**
     * Complete fixing the fridge.
     * @param person person that is fixing the device
     */
    @Override
    public void completeFixing(Adult person) {
        if (isFixing() && person.equals(getUser())) {
            setUser(null);
            person.completeFixingDevice(this);
            changeState(new FridgeIdleState(this));
        }
    }

    /**
     * To break the fridge.
     */
    @Override
    public void toBreak() {
        changeState(new FridgeBrokenState(this));
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
