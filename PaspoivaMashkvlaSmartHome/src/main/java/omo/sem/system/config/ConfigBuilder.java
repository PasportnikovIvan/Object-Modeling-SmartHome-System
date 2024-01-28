package omo.sem.system.config;

import omo.sem.objects.PhysicalHouse;
import omo.sem.objects.resident.person.PersonGender;
import omo.sem.objects.housePartsBuilder.FloorBuilder;
import omo.sem.objects.housePartsBuilder.RoomBuilder;
import omo.sem.objects.housePartsBuilder.SmartHouseBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * The type Config builder.
 * <p>
 * An auxiliary static class for parsing and initializing the configuration.
 */
public class ConfigBuilder {
    private static JSONObject jo;

    /**
     * Build a PhysicalHouse based on the configuration file at the specified path.
     *
     * @param path the path to the configuration file
     * @return the PhysicalHouse object representing the configured smart house
     */
    public static PhysicalHouse build(String path) {
        initializeJsonFromFile(path);

        SmartHouseBuilder houseBuilder = new SmartHouseBuilder();
        addExternalSensors(houseBuilder);
        addFloors(houseBuilder);

        return houseBuilder.getResult();
    }

    /**
     * Initialize the JSON object from the specified configuration file.
     *
     * @param path the path to the configuration file
     */
    private static void initializeJsonFromFile(String path) {
        File file = new File(path);
        try {
            String content = new String(Files.readAllBytes(Paths.get(file.toURI())));
            jo = new JSONObject(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Add external sensors to the SmartHouseBuilder based on the configuration.
     *
     * @param houseBuilder the SmartHouseBuilder to which sensors will be added
     */
    private static void addExternalSensors(SmartHouseBuilder houseBuilder) {
        JSONArray outsideSensors = jo.optJSONArray("sensors");

        if (outsideSensors != null) {
            for (Object externalSensor : outsideSensors) {
                houseBuilder.addSensor(externalSensor.toString());
            }
        }
    }

    /**
     * Add floors to the SmartHouseBuilder based on the configuration.
     *
     * @param houseBuilder the SmartHouseBuilder to which floors will be added
     */
    private static void addFloors(SmartHouseBuilder houseBuilder) {
        JSONArray floors = jo.optJSONArray("floors");

        if (floors != null) {
            for (Object floorObject : floors) {
                JSONObject floor = (JSONObject) floorObject;
                FloorBuilder floorBuilder = houseBuilder.addFloor(floor.getInt("level"));
                addRooms(floorBuilder, floor);
            }
        }
    }

    /**
     * Add rooms to the FloorBuilder based on the configuration.
     *
     * @param floorBuilder the FloorBuilder to which rooms will be added
     * @param floor        the JSON object representing a floor in the configuration
     */
    private static void addRooms(FloorBuilder floorBuilder, JSONObject floor) {
        JSONArray rooms = floor.optJSONArray("rooms");

        if (rooms != null) {
            for (Object roomObject : rooms) {
                JSONObject room = (JSONObject) roomObject;
                RoomBuilder roomBuilder = floorBuilder.addRoom(room.getString("name"));
                addRoomDetails(roomBuilder, room);
            }
        }
    }

    /**
     * Add details to the RoomBuilder based on the configuration.
     *
     * @param roomBuilder the RoomBuilder to which details will be added
     * @param room         the JSON object representing a room in the configuration
     */
    private static void addRoomDetails(RoomBuilder roomBuilder, JSONObject room) {
        addInsideSensors(roomBuilder, room);
        addItems(roomBuilder, room);
        addDevices(roomBuilder, room);
        addPersons(roomBuilder, room);
        addPets(roomBuilder, room);
    }

    /**
     * Add inside sensors to the RoomBuilder based on the configuration.
     *
     * @param roomBuilder the RoomBuilder to which sensors will be added
     * @param room         the JSON object representing a room in the configuration
     */
    private static void addInsideSensors(RoomBuilder roomBuilder, JSONObject room) {
        JSONArray insideSensors = room.optJSONArray("sensors");

        if (insideSensors != null) {
            for (Object sensorObject : insideSensors) {
                roomBuilder.addSensor(sensorObject.toString());
            }
        }
    }

    /**
     * Add items to the RoomBuilder based on the configuration.
     *
     * @param roomBuilder the RoomBuilder to which items will be added
     * @param room         the JSON object representing a room in the configuration
     */
    private static void addItems(RoomBuilder roomBuilder, JSONObject room) {
        JSONArray items = room.optJSONArray("items");

        if (items != null) {
            for (Object itemObject : items) {
                JSONObject item = (JSONObject) itemObject;
                roomBuilder.addItem(item.getString("type"), item.getString("name"));
            }
        }
    }

    /**
     * Add devices to the RoomBuilder based on the configuration.
     *
     * @param roomBuilder the RoomBuilder to which devices will be added
     * @param room         the JSON object representing a room in the configuration
     */
    private static void addDevices(RoomBuilder roomBuilder, JSONObject room) {
        JSONArray devices = room.optJSONArray("devices");

        if (devices != null) {
            for (Object deviceObject : devices) {
                JSONObject device = (JSONObject) deviceObject;
                roomBuilder.addDevice(device.getString("type"), device.getString("name"));
            }
        }
    }

    /**
     * Add persons to the RoomBuilder based on the configuration.
     *
     * @param roomBuilder the RoomBuilder to which persons will be added
     * @param room         the JSON object representing a room in the configuration
     */
    private static void addPersons(RoomBuilder roomBuilder, JSONObject room) {
        JSONArray persons = room.optJSONArray("persons");

        if (persons != null) {
            for (Object personObject : persons) {
                JSONObject person = (JSONObject) personObject;
                PersonGender gender = getPersonGender(person.getString("gender"));
                roomBuilder.addPerson(person.getString("type"), person.getString("name"), gender);
            }
        }
    }

    /**
     * Get the PersonGender enum based on the provided gender string.
     *
     * @param gender the gender string
     * @return the PersonGender enum
     * @throws IllegalArgumentException if an invalid gender string is provided
     */
    private static PersonGender getPersonGender(String gender) {
        if (gender.equals("MALE")) {
            return PersonGender.MALE;
        } else if (gender.equals("FEMALE")) {
            return PersonGender.FEMALE;
        } else {
            throw new IllegalArgumentException("There is no such gender.");
        }
    }

    /**
     * Add pets to the RoomBuilder based on the configuration.
     *
     * @param roomBuilder the RoomBuilder to which pets will be added
     * @param room         the JSON object representing a room in the configuration
     */
    private static void addPets(RoomBuilder roomBuilder, JSONObject room) {
        JSONArray pets = room.optJSONArray("pets");

        if (pets != null) {
            for (Object petObject : pets) {
                JSONObject pet = (JSONObject) petObject;
                roomBuilder.addPet(pet.getString("type"), pet.getString("name"));
            }
        }
    }
}
