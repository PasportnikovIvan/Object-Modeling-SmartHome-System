package omo.sem.objects.resident.person.adult.state;

import omo.sem.objects.resident.HouseResident;
import omo.sem.objects.resident.HouseResidentState;
import omo.sem.system.SmartHouse;

import java.util.logging.Logger;

/**
 * Class representing adult device using state.
 */
public class AdultDeviceUsingState extends HouseResidentState {
    // Logger
    private static final Logger log = Logger.getLogger(AdultDeviceUsingState.class.getName());

    public AdultDeviceUsingState(HouseResident houseResident) {
        super(houseResident);

        log.info(String.format("%s \"%s\" started using object \"%s\" [%s]",
                houseResident.getClass().getSimpleName(),
                houseResident.getName(),
                houseResident.getUsableObject().getName(),
                SmartHouse.getInstance().getSimulation().getFormattedTime()));
    }

    /**
     * Update.
     * @param time the time
     */
    @Override
    public void update(long time) {
        this.time += time;

        // UnUse
        if (this.time > houseResident.getUsableObject().getUsageTimeInHour() * 3600L * 1000000000L) {
            houseResident.getUsableObject().unUse(houseResident);
            houseResident.changeState(new AdultWaitingState(houseResident));
        }
    }
}
