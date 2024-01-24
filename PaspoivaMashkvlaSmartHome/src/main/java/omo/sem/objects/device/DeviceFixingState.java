package omo.sem.objects.device;

/**
 * Abstract class representing device's fixing state.
 */
public abstract class DeviceFixingState extends DeviceState {
    /**
     * Instantiates a new device fixing state.
     *
     * @param device the device
     */
    public DeviceFixingState(Device device) {
        super(device);
    }
}
