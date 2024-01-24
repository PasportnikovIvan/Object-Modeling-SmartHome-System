package omo.sem.system.simulation;

import java.io.IOException;

/**
 * Interface representing time.
 */
public interface TimeTracker {

    void update(long time) throws IOException;

}
