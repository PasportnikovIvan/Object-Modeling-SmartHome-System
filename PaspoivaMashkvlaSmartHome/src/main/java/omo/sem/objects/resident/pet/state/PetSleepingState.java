package omo.sem.objects.resident.pet.state;

import omo.sem.objects.resident.HouseResident;
import omo.sem.objects.resident.HouseResidentState;
import omo.sem.system.SmartHouse;
import omo.sem.system.simulation.Simulation;

import java.util.logging.Logger;

/**
 * Class representing pet sleeping state
 */
public class PetSleepingState extends HouseResidentState {
    // Logger
    private static final Logger log = Logger.getLogger(PetSleepingState.class.getName());

    //Constants
    private final int START_WAKE_TIME = 23;
    private final int END_WAKE_TIME = 7;

    public PetSleepingState(HouseResident houseResident) {
        super(houseResident);

        log.info(String.format("Pet \"%s\" is sleeping now :3 [%s]",
                houseResident.getName(),
                SmartHouse.getInstance().getSimulation().getFormattedTime()));
    }

    /**
     * Update
     * @param time the time
     */
    @Override
    public void update(long time) {
        this.time += time;

        Simulation simulationTime = SmartHouse.getInstance().getSimulation();

        if (simulationTime.getHour() < START_WAKE_TIME && simulationTime.getHour() >= END_WAKE_TIME) {
            houseResident.changeState(new PetAwakeState(houseResident));

            log.info(String.format("Pet \"%s\" woke up [%s]",
                    houseResident.getName(),
                    SmartHouse.getInstance().getSimulation().getFormattedTime()));
        }

    }
}
