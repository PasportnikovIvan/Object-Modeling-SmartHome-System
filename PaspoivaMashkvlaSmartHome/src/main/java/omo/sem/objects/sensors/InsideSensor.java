package omo.sem.objects.sensors;

import omo.sem.objects.Room;

public abstract class InsideSensor extends Sensor {
    protected Room room;

    public InsideSensor(Room room) {
        this.room = room;
    }

    /**
     * Returns a location.
     * @return room
     */
    public Room getRoom() {
        return room;
    }
}