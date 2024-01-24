package omo.sem.system.config;

import omo.sem.objects.PhysicalHouse;
import omo.sem.objects.resident.person.PersonGender;
import omo.sem.objects.housePartsBuilder.FloorBuilder;
import omo.sem.objects.housePartsBuilder.SmartHouseBuilder;
import omo.sem.objects.housePartsBuilder.RoomBuilder;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * The type Config builder.
 * <p>
 * An auxiliary static class for parsing and initialising the configuration.
 */
public class ConfigBuilder {
    private static JSONObject jo;

    /**
     * Build house.
     *
     * @param path the path
     * @return the house
     */
    public static PhysicalHouse build(String path) {
        File file = new File(path);
        try {
            String content = new String(Files.readAllBytes(Paths.get(file.toURI())));
            jo = new JSONObject(content);
        } catch (IOException e) {
            e.printStackTrace();
        }

        SmartHouseBuilder houseBuilder = new SmartHouseBuilder();

        JSONArray ousideSensors = null;

        try {
            ousideSensors = jo.getJSONArray("sensors");
        } catch (Exception ignored) {}

        if (ousideSensors != null) {
            for (Object externalSensor : ousideSensors) {
                houseBuilder.addSensor(externalSensor.toString());
            }
        }

        JSONArray floors = null;

        try {
            floors = jo.getJSONArray("floors");
        } catch (Exception ignored) {}

        if (floors != null) {
            for (Object floorObject : floors) {
                JSONObject floor = (JSONObject) floorObject;
                FloorBuilder floorBuilder = houseBuilder.addFloor(floor.getInt("level"));

                JSONArray rooms = null;

                try {
                    rooms = floor.getJSONArray("rooms");
                } catch (Exception ignored) {}

                if (rooms != null) {
                    for (Object roomObject : rooms) {
                        JSONObject room = (JSONObject) roomObject;
                        RoomBuilder roomBuilder = floorBuilder.addRoom(room.getString("name"));

                        JSONArray insideSensors = null;

                        try {
                            insideSensors = room.getJSONArray("sensors");
                        } catch (Exception ignored) {}

                        if (insideSensors != null) {
                            for (Object sensorObject : insideSensors) {
                                roomBuilder.addSensor(sensorObject.toString());
                            }
                        }

                        // Items
                        JSONArray items = null;

                        try {
                            items = room.getJSONArray("items");
                        } catch (Exception ignored) {}

                        if (items != null) {
                            for (Object itemObject : items) {
                                JSONObject item = (JSONObject) itemObject;

                                roomBuilder.addItem(item.getString("type"), item.getString("name"));
                            }
                        }

                        JSONArray devices = null;

                        try {
                            devices = room.getJSONArray("devices");
                        } catch (Exception ignored) {}

                        if (devices != null) {
                            for (Object deviceObject : devices) {
                                JSONObject device = (JSONObject) deviceObject;

                                roomBuilder.addDevice(device.getString("type"), device.getString("name"));
                            }
                        }

                        JSONArray persons = null;

                        try {
                            persons = room.getJSONArray("persons");
                        } catch (Exception ignored) {}

                        if (persons != null) {
                            for (Object personObject : persons) {
                                JSONObject person = (JSONObject) personObject;
                                PersonGender gender;

                                if (person.getString("gender").equals("MALE")) {
                                    gender = PersonGender.MALE;
                                } else if (person.getString("gender").equals("FEMALE")) {
                                    gender = PersonGender.FEMALE;
                                } else {
                                    throw new IllegalArgumentException("There is no such gender.");
                                }

                                roomBuilder.addPerson(person.getString("type"), person.getString("name"), gender);
                            }
                        }

                        JSONArray pets = null;

                        try {
                            pets = room.getJSONArray("pets");
                        } catch (Exception ignored) {}

                        if (pets != null) {
                            for (Object petObject : pets) {
                                JSONObject pet = (JSONObject) petObject;

                                roomBuilder.addPet(pet.getString("type"), pet.getString("name"));
                            }
                        }
                    }
                }
            }
        }

        return houseBuilder.getResult();
    }
}
