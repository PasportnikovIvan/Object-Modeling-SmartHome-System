package omo.sem.objects.device.door;

import omo.sem.objects.device.Device;
import omo.sem.objects.device.Documentation;
import omo.sem.objects.resident.HouseResident;
import omo.sem.objects.resident.person.adult.Adult;
import omo.sem.objects.Room;
import omo.sem.objects.device.door.state.*;

/**
 * Class representing the Door.
 */
public class Door extends Device {
    /**
     * Instantiates a new Door.
     *
     * @param room the room
     * @param name the name
     */
    public Door(Room room, String name) {
        super(room, name);
        this.state = new DoorIdleState(this);
        this.fixingTimeInHours = 3;
        this.operatingTimeInHours = 20_000;
        this.usageTimeInHour = 0.125;
        this.doc = new Documentation(this, this.fixingTimeInHours);
    }

    /**
     * Opens the Door.
     */
    @Override
    public void on() {
        if (isOff()) {
            changeState(new DoorIdleState(this));
        }
    }

    /**
     * Closes the Door.
     */
    @Override
    public void off() {
        if (isOn()) {
            changeState(new DoorOffState(this));
        }
    }

    /**
     * Use the Door.
     * @param houseResident resident that uses object
     */
    //TODO
    @Override
    public void use(HouseResident houseResident) {
        if (!isUsing() && !isBroken()) {
            if (Math.random() <= houseResident.getDeviceBreakingChance()) {
                changeState(new DoorBrokenState(this));
                return;
            }

            if (!(houseResident instanceof Adult)) return;

            setUser(houseResident);
            ((Adult) houseResident).useObject(this);
            changeState(new DoorUsingState(this));
        }
    }

    /**
     * UnUse the Door.
     * @param houseResident houseResident that unUses object
     */
    @Override
    public void unUse(HouseResident houseResident) {
        if (isUsing() && houseResident.equals(getUser())) {
            setUser(null);
            ((Adult) houseResident).unUseObject(this);
            changeState(new DoorIdleState(this));
        }
    }

    /**
     * Fix the Door.
     * @param person person that is fixing the device
     */
    @Override
    public void fix(Adult person) {
        if (isBroken()) {
            setUser(person);
            person.fixDevice(this);
            changeState(new DoorFixingState(this));
        }
    }

    /**
     * Complete fixing the Door.
     * @param person person that is fixing the device
     */
    @Override
    public void completeFixing(Adult person) {
        if (isFixing() && person.equals(getUser())) {
            setUser(null);
            person.completeFixingDevice(this);
            changeState(new DoorIdleState(this));
        }
    }

    /**
     * To break the Door.
     */
    @Override
    public void toBreak() {
        changeState(new DoorBrokenState(this));
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
