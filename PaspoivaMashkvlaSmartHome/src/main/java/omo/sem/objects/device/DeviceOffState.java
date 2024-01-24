package omo.sem.objects.device;

/**
 * Abstract class representing device's off state.
 */
public abstract class DeviceOffState extends DeviceState {
    /**
     * Instantiates a new device off state.
     *
     * @param device the device
     */
    public DeviceOffState(Device device) {
        super(device);
    }
}
