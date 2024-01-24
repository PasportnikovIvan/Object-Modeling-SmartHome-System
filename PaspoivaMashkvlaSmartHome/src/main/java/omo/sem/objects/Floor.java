package omo.sem.objects;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a floor.
 */
public class Floor {
    private final int level;
    private final List<Room> rooms = new ArrayList<>();

    public Floor(int level) {
        this.level = level;
    }

    /**
     * Adds a room.
     * @param room the room to add
     */
    public void addRoom(Room room) {
        this.rooms.add(room);
    }

    /**
     * Returns the rooms.
     * @return rooms
     */
    public List<Room> getRooms() {
        return rooms;
    }

    /**
     * Returns the level.
     * @return level
     */
    public int getLevel() {
        return level;
    }
}
