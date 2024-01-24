package omo.sem.objects.sensors;

import omo.sem.objects.Room;

/**
 * Class representing a light sensor.
 */
public class LightSensor extends InsideSensor {
    // State
    private enum LightSensorStatus {
        ENOUGH_LIGHT,
        NOT_ENOUGH_LIGHT
    }

    private LightSensorStatus status = LightSensorStatus.ENOUGH_LIGHT;

    public LightSensor(Room room) {
        super(room);
    }

    /**
     * Update.
     * @param time the time
     */
    @Override
    public void update(long time) {
        this.time =+ time;
    }

    /**
     * Switches the status.
     */
    @Override
    protected void switchStatus() {
        status = status == LightSensorStatus.ENOUGH_LIGHT ? LightSensorStatus.NOT_ENOUGH_LIGHT : LightSensorStatus.ENOUGH_LIGHT;
    }

    /**
     * Resets the status.
     */
    @Override
    public void resetStatus() {
        status = LightSensorStatus.ENOUGH_LIGHT;
    }

    /**
     * Returns the status.
     * @return status
     */
    public LightSensorStatus getStatus() {
        return this.status;
    }
}
