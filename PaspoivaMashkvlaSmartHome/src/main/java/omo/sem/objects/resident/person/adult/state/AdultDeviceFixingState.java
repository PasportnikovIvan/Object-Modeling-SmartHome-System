package omo.sem.objects.resident.person.adult.state;

import omo.sem.objects.resident.HouseResidentState;
import omo.sem.system.SmartHouse;
import omo.sem.objects.device.Device;
import omo.sem.objects.resident.HouseResident;
import omo.sem.objects.resident.person.adult.Adult;
import java.util.logging.Logger;

/**
 * Class representing adult device fixing state.
 */
public class AdultDeviceFixingState extends HouseResidentState {
    // Logger
    private static final Logger log = Logger.getLogger(AdultDeviceFixingState.class.getName());

    public AdultDeviceFixingState(HouseResident houseResident) {
        super(houseResident);

        log.info(String.format("%s \"%s\" started fixing object \"%s\" [%s]",
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

        if (this.time > ((Device) houseResident.getUsableObject()).getFixingTimeInHours() * 3600L * 1000000000L) {
            ((Device) houseResident.getUsableObject()).completeFixing((Adult) houseResident);
            houseResident.changeState(new AdultWaitingState(houseResident));
        }
    }
}
