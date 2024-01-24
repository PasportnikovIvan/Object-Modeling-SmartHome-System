package omo.sem.objects.resident.person;

import omo.sem.objects.location.Location;
import omo.sem.objects.resident.person.adult.Adult;
import omo.sem.objects.Room;
import omo.sem.objects.resident.person.child.Child;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for creating persons.
 */
public class PersonFactory {
    private static PersonFactory instance;
    private final List<Person> persons = new ArrayList<>();

    private PersonFactory() {}

    public static PersonFactory getInstance() {
        if (instance == null) {
            instance = new PersonFactory();
        }

        return instance;
    }

    /**
     * Creates the person.
     * @param type the type (adult, child)
     * @param name the name
     * @param gender the gender
     * @param room the room
     * @return person
     */
    public Person create(String type, String name, PersonGender gender, Location room) {
        Person person = switch (type) {
            case "ADULT" -> new Adult((Room) room, name, gender);
            case "CHILD" -> new Child((Room) room, name, gender);
            default -> throw new IllegalArgumentException("Type of person " + type + " was not found.");
        };

        persons.add(person);
        return person;
    }

    /**
     * Returns persons.
     * @return persons
     */
    public List<Person> getPersons() {
        return persons;
    }
}
