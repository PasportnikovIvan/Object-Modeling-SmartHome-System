package omo.sem.objects.resident.pet;

import omo.sem.objects.Room;
import omo.sem.objects.location.Location;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for creating pets
 */
public class PetFactory {
    private static PetFactory instance;
    private final List<Pet> pets = new ArrayList<>();

    private PetFactory() {}

    /**
     * Returns the instance
     * @return instance
     */
    public static PetFactory getInstance() {
        if (instance == null) {
            instance = new PetFactory();
        }

        return instance;
    }

    /**
     * Creates a pet
     * @param type the type
     * @param name the name
     * @param room the room
     * @return pet
     */
    public Pet create(String type, String name, Location room) {
        Pet pet = switch (type) {
            case "CHMONYA" -> new Chmonya((Room) room, name);
            default -> throw new IllegalArgumentException("Type of pet " + type + " was not found.");
        };

        pets.add(pet);
        return pet;
    }

    /**
     * Returns pets
     * @return pets
     */
    public List<Pet> getPets() {
        return pets;
    }
}