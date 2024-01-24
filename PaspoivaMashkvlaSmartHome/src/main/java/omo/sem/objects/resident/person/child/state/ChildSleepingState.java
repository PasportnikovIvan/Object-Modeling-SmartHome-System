package omo.sem.objects.resident.person.child.state;

import omo.sem.objects.resident.HouseResident;
import omo.sem.objects.resident.HouseResidentState;
import omo.sem.system.SmartHouse;
import omo.sem.system.simulation.Simulation;

import java.util.logging.Logger;

/**
 * Class representing child sleeping state.
 */
public class ChildSleepingState extends HouseResidentState {
    // Logger
    private static final Logger log = Logger.getLogger(ChildSleepingState.class.getName());

    //Constants
    private final int START_WAKE_TIME = 21;
    private final int END_WAKE_TIME = 8;

    public ChildSleepingState(HouseResident houseResident) {
        super(houseResident);

        log.info(String.format("Child \"%s\" went to bed [%s]",
                houseResident.getName(),
                SmartHouse.getInstance().getSimulation().getFormattedTime()));
    }

    /**
     * Update.
     * @param time the time
     */
    @Override
    public void update(long time) {
        this.time += time;

        Simulation simulationTime = SmartHouse.getInstance().getSimulation();

        if (simulationTime.getHour() < START_WAKE_TIME && simulationTime.getHour() >= END_WAKE_TIME) {
            houseResident.changeState(new ChildAwakeState(houseResident));

            log.info(String.format("Child \"%s\" woke up [%s]",
                    houseResident.getName(),
                    SmartHouse.getInstance().getSimulation().getFormattedTime()));
        }
    }
}
