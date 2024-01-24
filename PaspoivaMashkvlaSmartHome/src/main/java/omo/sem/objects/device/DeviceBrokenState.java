package omo.sem.objects.device;

/**
 * Abstract class representing device's broken state.
 */
public abstract class DeviceBrokenState extends DeviceState {
    /**
     * Instantiates a new device broken state.
     *
     * @param device the device
     */
    public DeviceBrokenState(Device device) {
        super(device);
    }
}
