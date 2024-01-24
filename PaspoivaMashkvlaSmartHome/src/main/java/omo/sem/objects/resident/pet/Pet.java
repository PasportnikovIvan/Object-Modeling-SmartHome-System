package omo.sem.objects.resident.pet;

import omo.sem.objects.Room;
import omo.sem.objects.resident.HouseResident;
import omo.sem.objects.resident.person.adult.Adult;
import omo.sem.objects.resident.pet.state.PetAwakeState;

import java.util.logging.Logger;

/**
 * Abstract class representing a pet
 */
public abstract class Pet extends HouseResident {
    // Logger
    private static final Logger log = Logger.getLogger(Pet.class.getName());

    public Pet(Room room, String name) {
        super(room, name);
        this.state = new PetAwakeState(this);
        this.deviceBreakingChance = 0.3;
    }

    /**
     * Feeds the pet
     * @param adult the adult
     */
    public abstract void feed(Adult adult);

    /**
     * Checks hungry event
     * @return true if dispatched
     */
    public abstract boolean getDispatchedHungerEvent();
}
