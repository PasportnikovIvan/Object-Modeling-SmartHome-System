package omo.sem.objects.device;

import omo.sem.objects.device.airConditioner.AirConditioner;
import omo.sem.objects.device.door.Door;
import omo.sem.objects.device.fridge.Fridge;
import omo.sem.objects.Room;
import omo.sem.objects.device.kettle.Kettle;
import omo.sem.objects.device.light.Light;
import omo.sem.objects.device.speakers.Speakers;
import omo.sem.objects.device.tv.Tv;
import omo.sem.objects.device.window.Window;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for creating devices.
 */
public class DeviceFactory {
    private static DeviceFactory instance;
    private final List<Device> devices = new ArrayList<>();

    private DeviceFactory() {}

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static DeviceFactory getInstance() {
        if (instance == null) {
            instance = new DeviceFactory();
        }

        return instance;
    }

    /**
     * Creates the device.
     *
     * @param type device's type
     * @param room device's room
     * @param name device's name
     * @return device a device
     */
    public Device create(String type, Room room, String name) {
        Device device = switch (type) {
            case "AIR_CONDITIONER" -> new AirConditioner(room, name);
            case "DOOR" -> new Door(room, name);
            case "FRIDGE" -> new Fridge(room, name);
            case "KETTLE" -> new Kettle(room, name);
            case "LIGHT" -> new Light(room, name);
            case "SPEAKERS" -> new Speakers(room, name);
            case "TV" -> new Tv(room, name);
            case "WINDOW" -> new Window(room, name);
            default -> throw new IllegalArgumentException("Type of device " + type + " was not found.");
        };

        this.devices.add(device);
        return device;
    }

    /**
     * Returns devices.
     *
     * @return devices devices
     */
    public List<Device> getDevices() {
        return this.devices;
    }
}
