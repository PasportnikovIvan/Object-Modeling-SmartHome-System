package omo.sem.objects.resident.person;

import omo.sem.objects.resident.HouseResident;
import omo.sem.objects.Room;

/**
 * Abstract class representing the person.
 */
public abstract class Person extends HouseResident {
    protected PersonGender gender;

    public Person(Room room, String name) {
        super(room, name);
    }

    /**
     * Returns the gender.
     * @return gender
     */
    public PersonGender getGender() {
        return gender;
    }
}
