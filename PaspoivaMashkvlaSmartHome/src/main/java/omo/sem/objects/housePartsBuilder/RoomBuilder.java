package omo.sem.objects.housePartsBuilder;

import omo.sem.objects.device.DeviceFactory;
import omo.sem.objects.item.ItemFactory;
import omo.sem.objects.resident.person.PersonFactory;
import omo.sem.objects.resident.person.PersonGender;
import omo.sem.objects.Floor;
import omo.sem.objects.Room;
import omo.sem.objects.resident.pet.PetFactory;
import omo.sem.objects.sensors.InsideSensor;
import omo.sem.objects.sensors.factories.InsideSensorFactory;
import omo.sem.objects.sensors.factories.SensorFactory;

/**
 * Class for building the room.
 */
public class RoomBuilder {
    private final Room room;
    private final FloorBuilder floorBuilder;
    private final Floor floor;

    public RoomBuilder(FloorBuilder floorBuilder, Floor floor, String name) {
        this.floorBuilder = floorBuilder;
        this.floor = floor;
        this.room = new Room(name);

        floor.addRoom(room);
    }

    /**
     * Adds a person.
     * @param type person's type (child, adult)
     * @param name the name
     * @param gender the gender
     * @return room builder
     */
    public RoomBuilder addPerson(String type, String name, PersonGender gender) {
        PersonFactory personFactory = PersonFactory.getInstance();

        room.addHouseResident(personFactory.create(type, name, gender, room));
        return this;
    }

    /**
     * Adds a device.
     * @param type device's type
     * @param name the name
     * @return room builder
     */
    public RoomBuilder addDevice(String type, String name) {
        DeviceFactory deviceFactory = DeviceFactory.getInstance();

        room.addDevice(deviceFactory.create(type, room, name));
        return this;
    }

    /**
     * Adds a sensor.
     * @param type sensor's type
     * @return room builder
     */
    public RoomBuilder addSensor(String type) {
        SensorFactory sensorFactory = InsideSensorFactory.getInstance();

        room.addSensor((InsideSensor) sensorFactory.create(type, room));
        return this;
    }

    /**
     * Adds a pet.
     * @param type pet's type
     * @param name the name
     * @return room builder
     */
    public RoomBuilder addPet(String type, String name) {
        PetFactory petFactory = PetFactory.getInstance();

        room.addHouseResident(petFactory.create(type, name, room));
        return this;
    }

    /**
     * Adds an item.
     * @param type item's type
     * @param name the name
     * @return room builder
     */
    public RoomBuilder addItem(String type, String name) {
        ItemFactory itemFactory = ItemFactory.getInstance();

        room.addItem(itemFactory.create(type, room, name));
        return this;
    }

    /**
     * Ends a builder.
     * @return floor builder
     */
    public FloorBuilder end() {
        return floorBuilder;
    }
}
