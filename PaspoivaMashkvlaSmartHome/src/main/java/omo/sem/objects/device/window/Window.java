package omo.sem.objects.device.window;

import omo.sem.objects.device.Device;
import omo.sem.objects.device.Documentation;
import omo.sem.objects.resident.HouseResident;
import omo.sem.objects.resident.person.adult.Adult;
import omo.sem.objects.Room;
import omo.sem.objects.device.window.state.*;

/**
 * Class representing the Window.
 */
public class Window extends Device {
    /**
     * Instantiates a new Window.
     *
     * @param room the room
     * @param name the name
     */
    public Window(Room room, String name) {
        super(room, name);
        this.state = new WindowIdleState(this);
        this.fixingTimeInHours = 2;
        this.operatingTimeInHours = 15_000;
        this.usageTimeInHour = 1;
        this.doc = new Documentation(this, this.fixingTimeInHours);
    }

    /**
     *  The Window stays closed.
     */
    @Override
    public void on() {
        if (isOff()) {
            changeState(new WindowIdleState(this));
        }
    }

    /**
     * Closes the Window.
     */
    @Override
    public void off() {
        if (isOn()) {
            changeState(new WindowOffState(this));
        }
    }

    /**
     * The Window is opened.
     * @param houseResident resident that uses object
     */
    //TODO
    @Override
    public void use(HouseResident houseResident) {
        if (!isUsing() && !isBroken()) {
            if (Math.random() <= houseResident.getDeviceBreakingChance()) {
                changeState(new WindowBrokenState(this));
                return;
            }

            if (!(houseResident instanceof Adult)) return;

            setUser(houseResident);
            ((Adult) houseResident).useObject(this);
            changeState(new WindowUsingState(this));
        }
    }

    /**
     * Closes the Window.
     * @param houseResident houseResident that unUses object
     */
    @Override
    public void unUse(HouseResident houseResident) {
        if (isUsing() && houseResident.equals(getUser())) {
            setUser(null);
            ((Adult) houseResident).unUseObject(this);
            changeState(new WindowIdleState(this));
        }
    }

    /**
     * Fix the Window.
     * @param person person that is fixing the device
     */
    @Override
    public void fix(Adult person) {
        if (isBroken()) {
            setUser(person);
            person.fixDevice(this);
            changeState(new WindowFixingState(this));
        }
    }

    /**
     * Complete fixing the Window.
     * @param person person that is fixing the device
     */
    @Override
    public void completeFixing(Adult person) {
        if (isFixing() && person.equals(getUser())) {
            setUser(null);
            person.completeFixingDevice(this);
            changeState(new WindowIdleState(this));
        }
    }

    /**
     * To break the Window.
     */
    @Override
    public void toBreak() {
        changeState(new WindowBrokenState(this));
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
