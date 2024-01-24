package omo.sem.objects.sensors;

import omo.sem.system.SmartHouse;
import omo.sem.system.event.Source;
import omo.sem.system.simulation.TimeTracker;

/**
 * Abstract class representing a sensor.
 */
public abstract class Sensor implements TimeTracker, Source {
    protected long time = 0;

    public Sensor() {
        // Init
        SmartHouse.getInstance().getSimulation().subscribe(this);
    }

    /**
     * Returns a name.
     * @return the name
     */
    public String getName() {
        return this.getClass().getSimpleName();
    }

    /**
     * Switches a status.
     */
    protected abstract void switchStatus();

    /**
     * Resets a status.
     */
    public abstract void resetStatus();
}
