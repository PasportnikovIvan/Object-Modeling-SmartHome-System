package omo.sem.objects.resident.pet;

import omo.sem.objects.Room;
import omo.sem.objects.resident.person.adult.Adult;
import omo.sem.objects.resident.pet.state.PetAwakeState;
import omo.sem.system.SmartHouse;
import omo.sem.system.event.events.HungryPetEvent;

/**
 * Class representing a dragon
 */
public class Chmonya extends Pet {
    // Constants
    private final double MIN_TRIGGERED_TIME_IN_HOURS = 5;
    private final double MAX_TRIGGERED_TIME_IN_HOURS = 25;

    // State
    private long timeFromLastDispatchedHungerEvent = 0;
    private boolean dispatchedHungerEvent = false;
    private double triggeredHungerTimeInHours;

    public Chmonya(Room room, String name) {
        super(room, name);
        this.triggeredHungerTimeInHours = calculateTriggeredTime();
    }

    /**
     * Calculates event triggered time
     * @return time
     */
    private double calculateTriggeredTime() {
        return Math.random() * (MAX_TRIGGERED_TIME_IN_HOURS - MIN_TRIGGERED_TIME_IN_HOURS + 1) + MIN_TRIGGERED_TIME_IN_HOURS;
    }

    /**
     * Feeds the dragon
     * @param adult the adult
     */
    public void feed(Adult adult) {
        adult.feedPet(this);
        dispatchedHungerEvent = false;
    }

    /**
     * Checks hungry event
     * @return true if dispatched
     */
    public boolean getDispatchedHungerEvent() {
        return dispatchedHungerEvent;
    }

    /**
     * Update
     * @param time the time
     */
    @Override
    public void update(long time) {
        this.time += time;
        this.timeFromLastDispatchedHungerEvent += time;

        if (!dispatchedHungerEvent && timeFromLastDispatchedHungerEvent >= triggeredHungerTimeInHours * 3600 * 1000000000) {
            dispatchedHungerEvent = true;
            timeFromLastDispatchedHungerEvent = 0;
            triggeredHungerTimeInHours = calculateTriggeredTime();
            changeState(new PetAwakeState(this));
            SmartHouse.getInstance().getEventDispatcher().dispatchEvent(new HungryPetEvent(this, room), "global");
        }

        state.update(time);
    }
}
