package omo.sem.objects.resident.person.child;

import omo.sem.objects.Room;
import omo.sem.objects.resident.person.Person;
import omo.sem.objects.resident.person.PersonGender;
import omo.sem.objects.resident.person.adult.Adult;
import omo.sem.objects.resident.person.child.state.ChildAwakeState;
import omo.sem.system.SmartHouse;
import omo.sem.system.event.events.ChildEvent;

import java.util.logging.Logger;

/**
 * Class representing the child.
 */
public class Child extends Person {
    // Logger
    private static final Logger log = Logger.getLogger(Child.class.getName());

    // Constants
    private final double MIN_TRIGGERED_TIME_IN_HOURS = 4;
    private final double MAX_TRIGGERED_TIME_IN_HOURS = 48;

    // State
    private long timeFromLastDispatchedEvent = 0;
    private boolean dispatchedEvent = false;
    private double triggeredTimeInHours;

    public Child(Room room, String name, PersonGender gender) {
        super(room, name);
        this.state = new ChildAwakeState(this);
        this.gender = gender;
        this.triggeredTimeInHours = calculateTriggeredTime();
        this.deviceBreakingChance = 0.3;
    }

    /**
     * Calculates event triggered time.
     * @return time
     */
    private double calculateTriggeredTime() {
        return Math.random() * (MAX_TRIGGERED_TIME_IN_HOURS - MIN_TRIGGERED_TIME_IN_HOURS + 1) + MIN_TRIGGERED_TIME_IN_HOURS;
    }

    /**
     * Cares the child.
     * @param adult the adult
     */
    public void care(Adult adult) {
        adult.careChild(this);
        dispatchedEvent = false;
    }

    /**
     * Checks child event.
     * @return true if dispatched
     */
    public boolean getDispatchedEvent() {
        return dispatchedEvent;
    }

    /**
     * Update.
     * @param time the time
     */
    @Override
    public void update(long time) {
        this.time += time;
        this.timeFromLastDispatchedEvent += time;

        if (!dispatchedEvent && timeFromLastDispatchedEvent >= triggeredTimeInHours * 3600 * 1000000000) {
            dispatchedEvent = true;
            timeFromLastDispatchedEvent = 0;
            triggeredTimeInHours = calculateTriggeredTime();
            changeState(new ChildAwakeState(this));
            SmartHouse.getInstance().getEventDispatcher().dispatchEvent(new ChildEvent(this, room), "global");
        }

        this.state.update(time);
    }
}
