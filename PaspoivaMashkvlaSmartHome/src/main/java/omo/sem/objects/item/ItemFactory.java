package omo.sem.objects.item;

import omo.sem.objects.Room;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for creating items.
 */
public class ItemFactory {
    private static ItemFactory instance;
    private final List<Item> items = new ArrayList<>();

    private ItemFactory() {}

    /**
     * Returns the instance.
     * @return instance
     */
    public static ItemFactory getInstance() {
        if (instance == null) {
            instance = new ItemFactory();
        }

        return instance;
    }

    /**
     * Creates an item.
     * @param type the type
     * @param room the room
     * @param name the name
     * @return item
     * @throws IllegalArgumentException unknown item type
     */
    public Item create(String type, Room room, String name) throws IllegalArgumentException {
        Item item = switch (type) {
            case "BICYCLE" -> new Bicycle(room, name);
            case "CAR" -> new Car(room, name);
            default -> throw new IllegalArgumentException("Type of item " + type + " was not found.");
        };

        this.items.add(item);
        return item;
    }

    /**
     * Returns items.
     * @return items
     */
    public List<Item> getItems() {
        return this.items;
    }
}
