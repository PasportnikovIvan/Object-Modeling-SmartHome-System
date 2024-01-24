package omo.sem.objects.resident.pet.state;

import omo.sem.objects.device.Device;
import omo.sem.objects.device.DeviceFactory;
import omo.sem.objects.device.DeviceIdleState;
import omo.sem.objects.resident.HouseResident;
import omo.sem.objects.resident.HouseResidentState;
import omo.sem.objects.resident.pet.Pet;
import omo.sem.system.SmartHouse;
import omo.sem.system.simulation.Simulation;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Class representing pet awake state
 */
public class PetAwakeState extends HouseResidentState {
    // Logger
    private static final Logger log = Logger.getLogger(PetAwakeState.class.getName());

    // Constants
    private final double MIN_TRIGGERED_TIME_IN_HOURS = 6;
    private final double MAX_TRIGGERED_TIME_IN_HOURS = 24;
    private final int START_SLEEPING_TIME = 23;
    private final int END_SLEEPING_TIME = 7;

    // State
    private long timeFromLastDispatchedDeviceUsingEvent = 0;
    private double triggeredTimeInHours;

    public PetAwakeState(HouseResident houseResident) {
        super(houseResident);
        this.triggeredTimeInHours = calculateTriggeredTime();
    }

    /**
     * Calculates device using triggered time
     * @return time
     */
    private double calculateTriggeredTime() {
        return Math.random() * (MAX_TRIGGERED_TIME_IN_HOURS - MIN_TRIGGERED_TIME_IN_HOURS + 1) + MIN_TRIGGERED_TIME_IN_HOURS;
    }

    /**
     * Uses the device and resets the time
     * @param device the device
     */
    private void useDeviceAndResetDeviceUsingEvent(Device device) {
        this.timeFromLastDispatchedDeviceUsingEvent = 0;
        this.triggeredTimeInHours = calculateTriggeredTime();

        if (Math.random() <= houseResident.getDeviceBreakingChance()) {
            log.info(String.format("%s \"%s\" broke the object \"%s\" while playing with it :( [%s]",
                    houseResident.getClass().getSimpleName(),
                    houseResident.getName(),
                    device.getName(),
                    SmartHouse.getInstance().getSimulation().getFormattedTime()));

            device.toBreak();
        } else {
            log.info(String.format("%s \"%s\" didn't break the object \"%s\" while playing with it, WOOOW :D [%s]",
                    houseResident.getClass().getSimpleName(),
                    houseResident.getName(),
                    device.getName(),
                    SmartHouse.getInstance().getSimulation().getFormattedTime()));
        }
    }

    /**
     * Update
     * @param time the time
     */
    @Override
    public void update(long time) {
        this.time += time;
        this.timeFromLastDispatchedDeviceUsingEvent += time;

        Simulation simulationTime = SmartHouse.getInstance().getSimulation();

        // Sleeping time
        if (!((Pet) houseResident).getDispatchedHungerEvent()) {
            if (simulationTime.getHour() >= START_SLEEPING_TIME || simulationTime.getHour() < END_SLEEPING_TIME) {
                houseResident.changeState(new PetSleepingState(houseResident));
                return;
            }
        }

        // Device using
        if (this.timeFromLastDispatchedDeviceUsingEvent >= triggeredTimeInHours * 3600L * 1000000000L) {
            List<Device> devices = houseResident.getRoom().getDevices().stream()
                    .filter(device -> device.getState() instanceof DeviceIdleState)
                    .collect(Collectors.toList());

            if (devices.isEmpty()) {
                List<Device> allDevices = DeviceFactory.getInstance().getDevices().stream()
                        .filter(device -> device.getState() instanceof DeviceIdleState)
                        .collect(Collectors.toList());

                if (!allDevices.isEmpty()) {
                    Device device = allDevices.get((int) (Math.random() * allDevices.size()));
                    houseResident.moveTo(device.getRoom());
                    useDeviceAndResetDeviceUsingEvent(device);
                }
            } else {
                Device device = devices.get((int) (Math.random() * devices.size()));
                useDeviceAndResetDeviceUsingEvent(device);
            }
        }
    }
}
