package omo.sem.objects.resident.person.adult.state;

import omo.sem.objects.resident.HouseResidentState;
import omo.sem.objects.resident.HouseResident;
import omo.sem.system.SmartHouse;
import omo.sem.system.simulation.Simulation;

import java.util.logging.Logger;

/**
 * Class representing adult working state.
 */
public class AdultWorkingState extends HouseResidentState {
    // Logger
    private static final Logger log = Logger.getLogger(AdultWorkingState.class.getName());

    //Constants
    private final int START_HOME_TIME = 10;
    private final int END_HOME_TIME = 17;

    public AdultWorkingState(HouseResident houseResident) {
        super(houseResident);

        log.info(String.format("%s \"%s\" went to work [%s]",
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

        // Go home
        if (simulationTime.getHour() < START_HOME_TIME || simulationTime.getHour() >= END_HOME_TIME) {
            houseResident.changeState(new AdultWaitingState(houseResident));

            log.info(String.format("%s \"%s\" arrived home from work [%s]",
                    houseResident.getClass().getSimpleName(),
                    houseResident.getName(),
                    SmartHouse.getInstance().getSimulation().getFormattedTime()));
        }
    }
}
