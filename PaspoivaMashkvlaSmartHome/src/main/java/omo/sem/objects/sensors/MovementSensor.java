package omo.sem.objects.sensors;

import omo.sem.objects.PhysicalHouse;
import omo.sem.system.SmartHouse;
import omo.sem.system.event.events.MovementFoundOutsideEvent;
import omo.sem.system.event.events.NoMovementFoundOutsideEvent;

/**
 * Class representing a movement sensor.
 */
public class MovementSensor extends OutsideSensor {
    // Constants
    private final double MIN_PEOPLE_OUTSIDE = 0;
    private final double MAX_PEOPLE_OUTSIDE = 1;

    // Status
    private enum MovementSensorStatus {
        MOVEMENT,
        NO_MOVEMENT
    }

    private MovementSensorStatus status = MovementSensorStatus.NO_MOVEMENT;

    public MovementSensor(PhysicalHouse house) {
        super(house);
    }

    /**
     * Update.
     * @param time the time
     */
    @Override
    public void update(long time) {
        this.time =+ time;

        double amountOfPeopleOutside = house.getAmountOfPeopleOutside();

        if (amountOfPeopleOutside == MIN_PEOPLE_OUTSIDE && status == MovementSensorStatus.MOVEMENT) {
            switchStatus();
            SmartHouse.getInstance().getEventDispatcher().dispatchEvent(new NoMovementFoundOutsideEvent(this, house), "global");
        } else if (amountOfPeopleOutside >= MAX_PEOPLE_OUTSIDE && status == MovementSensorStatus.NO_MOVEMENT) {
            switchStatus();
            SmartHouse.getInstance().getEventDispatcher().dispatchEvent(new MovementFoundOutsideEvent(this, house), "global");
        }
    }

    /**
     * Switches the status.
     */
    @Override
    protected void switchStatus() {
        status = status == MovementSensorStatus.MOVEMENT ? MovementSensorStatus.NO_MOVEMENT : MovementSensorStatus.MOVEMENT;
    }

    /**
     * Resets the status.
     */
    @Override
    public void resetStatus() {
        status = MovementSensorStatus.NO_MOVEMENT;
    }

    /**
     * Returns the status.
     * @return status
     */
    public MovementSensorStatus getStatus() {
        return this.status;
    }
}
