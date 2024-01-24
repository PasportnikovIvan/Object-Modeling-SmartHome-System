package omo.sem.system.task;

import omo.sem.objects.PhysicalHouse;
import omo.sem.objects.Room;
import omo.sem.objects.resident.person.child.Child;
import omo.sem.objects.resident.pet.Pet;
import omo.sem.system.event.Event;
import omo.sem.objects.device.Device;
import omo.sem.objects.resident.person.adult.Adult;
import omo.sem.system.event.events.*;

/**
 * Class representing tasks for event handling.
 * <p>
 * Used in case no one has been able to handle the event at the moment, then it will go into the queue.
 */
public class Task {
    private final Event event;

    /**
     * Instantiates a new Task.
     *
     * @param event the event
     */
    public Task(Event event) {
        this.event = event;
    }

    /**
     * Applies task to an adult.
     *
     * @param adult adult
     */
    public void apply(Adult adult) {
        if (event instanceof BrokenDeviceEvent) {
            ((Device) event.getSource()).fix(adult);
        } else if (event instanceof ChildEvent) {
            ((Child) event.getSource()).care(adult);
        } else if (event instanceof HungryPetEvent) {
            ((Pet) event.getSource()).feed(adult);
        } else if (event instanceof MusicEvent) {
            ((Room) event.getLocation()).chill(adult);
        }
    }
}
