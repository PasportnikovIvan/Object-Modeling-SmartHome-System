package omo.sem.objects.resident.person.adult.state;

import omo.sem.system.SmartHouse;
import omo.sem.system.simulation.Simulation;
import omo.sem.objects.resident.HouseResident;
import omo.sem.objects.resident.HouseResidentState;

import java.util.logging.Logger;

/**
 * Class representing adult sleeping state.
 */
public class AdultSleepingState extends HouseResidentState {
    // Logger
    private static final Logger log = Logger.getLogger(AdultSleepingState.class.getName());

    //Constants
    private final int START_WAKE_TIME = 23;
    private final int END_WAKE_TIME = 7;

    public AdultSleepingState(HouseResident houseResident) {
        super(houseResident);

        log.info(String.format("%s \"%s\" went to bed [%s]",
                houseResident.getClass().getSimpleName(),
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
            houseResident.changeState(new AdultWaitingState(houseResident));
            log.info(String.format("%s \"%s\" woke up [%s]",
                    houseResident.getClass().getSimpleName(),
                    houseResident.getName(),
                    SmartHouse.getInstance().getSimulation().getFormattedTime()));
        }
    }
}
