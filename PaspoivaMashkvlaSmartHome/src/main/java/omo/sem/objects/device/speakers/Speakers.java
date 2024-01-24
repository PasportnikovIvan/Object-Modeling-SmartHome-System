package omo.sem.objects.device.speakers;

import omo.sem.objects.device.Device;
import omo.sem.objects.device.Documentation;
import omo.sem.objects.resident.HouseResident;
import omo.sem.objects.resident.person.adult.Adult;
import omo.sem.objects.Room;
import omo.sem.objects.device.speakers.state.*;

/**
 * Class representing the Speakers.
 */
public class Speakers extends Device {
    /**
     * Instantiates a new Speakers.
     *
     * @param room the room
     * @param name the name
     */
    public Speakers(Room room, String name) {
        super(room, name);
        this.state = new SpeakersIdleState(this);
        this.fixingTimeInHours = 1;
        this.operatingTimeInHours = 9000;
        this.usageTimeInHour = 3;
        this.doc = new Documentation(this, this.fixingTimeInHours);
    }

    /**
     * Turns the Speakers on.
     */
    @Override
    public void on() {
        if (isOff()) {
            changeState(new SpeakersIdleState(this));
        }
    }

    /**
     * Turns the Speakers off.
     */
    @Override
    public void off() {
        if (isOn()) {
            changeState(new SpeakersOffState(this));
        }
    }

    /**
     * Use the Speakers.
     * @param houseResident resident that uses object
     */
    @Override
    public void use(HouseResident houseResident) {
        if (!isUsing() && !isBroken()) {
            if (Math.random() <= houseResident.getDeviceBreakingChance()) {
                changeState(new SpeakersBrokenState(this));
                return;
            }

            if (!(houseResident instanceof Adult)) return;

            setUser(houseResident);
            ((Adult) houseResident).useObject(this);
            changeState(new SpeakersUsingState(this));
        }
    }

    /**
     * UnUse the Speakers.
     * @param houseResident houseResident that unUses object
     */
    @Override
    public void unUse(HouseResident houseResident) {
        if (isUsing() && houseResident.equals(getUser())) {
            setUser(null);
            ((Adult) houseResident).unUseObject(this);
            changeState(new SpeakersIdleState(this));
        }
    }

    /**
     * Fix the Speakers.
     * @param person person that is fixing the device
     */
    @Override
    public void fix(Adult person) {
        if (isBroken()) {
            setUser(person);
            person.fixDevice(this);
            changeState(new SpeakersFixingState(this));
        }
    }

    /**
     * Complete fixing the Speakers.
     * @param person person that is fixing the device
     */
    @Override
    public void completeFixing(Adult person) {
        if (isFixing() && person.equals(getUser())) {
            setUser(null);
            person.completeFixingDevice(this);
            changeState(new SpeakersIdleState(this));
        }
    }

    /**
     * To break the Speakers.
     */
    @Override
    public void toBreak() {
        changeState(new SpeakersBrokenState(this));
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
