package omo.sem.objects.sensors;

import omo.sem.objects.Room;
import omo.sem.system.SmartHouse;
import omo.sem.system.event.events.LowTemperatureEvent;
import omo.sem.system.event.events.NormalTemperatureEvent;

public class TemperatureSensor extends InsideSensor{

    // Constants
    private final double MIN_TEMP = 18;
    private final double MAX_TEMP = 25;

    // status
    public enum TemperatureSensorStatus {
        NORMAL,
        COLD
    }

    private TemperatureSensorStatus status = TemperatureSensorStatus.NORMAL;

    public TemperatureSensor(Room room) {
        super(room);
    }

    /**
     * Update.
     * @param time the time
     */
    @Override
    public void update(long time) {
        this.time =+ time;

        double temp = room.getTemperature();

        if (temp < MIN_TEMP && status == TemperatureSensorStatus.NORMAL) {
            switchStatus();
            SmartHouse.getInstance().getEventDispatcher().dispatchEvent(new LowTemperatureEvent(this, room), room.getName());
        } else if (temp > MAX_TEMP && status == TemperatureSensorStatus.COLD) {
            switchStatus();
            SmartHouse.getInstance().getEventDispatcher().dispatchEvent(new NormalTemperatureEvent(this, room), room.getName());
        }
    }

    /**
     * Switches the status.
     */
    @Override
    protected void switchStatus() {
        status = status == TemperatureSensorStatus.NORMAL ? TemperatureSensorStatus.COLD : TemperatureSensorStatus.NORMAL;
    }

    /**
     * Resets the status.
     */
    @Override
    public void resetStatus() {
        status = TemperatureSensorStatus.NORMAL;
    }

    /**
     * Returns the status.
     * @return status
     */
    public TemperatureSensorStatus getStatus() {
        return this.status;
    }
}
