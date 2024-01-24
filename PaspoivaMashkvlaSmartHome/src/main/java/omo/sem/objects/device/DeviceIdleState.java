package omo.sem.objects.device;

/**
 * Abstract class representing device's idle state.
 */
public abstract class DeviceIdleState extends DeviceState {
    /**
     * Instantiates a new device idle state.
     *
     * @param device the device
     */
    public DeviceIdleState(Device device) {
        super(device);
    }
}
