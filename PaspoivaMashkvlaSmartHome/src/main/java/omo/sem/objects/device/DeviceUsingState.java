package omo.sem.objects.device;

/**
 * Abstract class representing device's using state.
 */
public abstract class DeviceUsingState extends DeviceState {
    /**
     * Instantiates a new device using state.
     *
     * @param device the device
     */
    public DeviceUsingState(Device device) {
        super(device);
    }
}
