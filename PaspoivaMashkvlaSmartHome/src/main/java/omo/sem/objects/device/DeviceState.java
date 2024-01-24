package omo.sem.objects.device;

/**
 * Abstract class representing device's state.
 */
public abstract class DeviceState {
    protected long time;
    protected Device device;

    protected double ELECTRICITY_CONSUMPTION = 0;

    /**
     * Instantiates a new device state.
     *
     * @param device the device
     */
    public DeviceState(Device device) {
        this.device = device;
    }

    /**
     * Gets electricity consumption.
     *
     * @return the electricity consumption
     */
    public double getElectricityConsumption() {
        return ELECTRICITY_CONSUMPTION;
    }

    /**
     * Update.
     *
     * @param time the time
     */
    public abstract void update(long time);
}
