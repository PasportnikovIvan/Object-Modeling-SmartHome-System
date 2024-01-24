package omo.sem.objects.resident.person.child.state;

import omo.sem.objects.device.Device;
import omo.sem.objects.device.DeviceIdleState;
import omo.sem.objects.resident.HouseResident;
import omo.sem.objects.resident.HouseResidentState;
import omo.sem.objects.resident.person.child.Child;
import omo.sem.system.SmartHouse;
import omo.sem.system.simulation.Simulation;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Class representing child awake state.
 */
public class ChildAwakeState extends HouseResidentState {
    // Logger
    private static final Logger log = Logger.getLogger(ChildAwakeState.class.getName());

    // Constants
    private final double MIN_TRIGGERED_TIME_IN_HOURS = 2;
    private final double MAX_TRIGGERED_TIME_IN_HOURS = 24;
    private final int START_SLEEPING_TIME = 21;
    private final int END_SLEEPING_TIME =  8;

    // State
    private long timeFromLastDispatchedDeviceUsingEvent = 0;
    private double triggeredTimeInHours;

    public ChildAwakeState(HouseResident houseResident) {
        super(houseResident);
        this.triggeredTimeInHours = calculateTriggeredTime();
    }

    /**
     * Calculates device using triggered time.
     * @return time
     */
    private double calculateTriggeredTime() {
        return Math.random() * (MAX_TRIGGERED_TIME_IN_HOURS - MIN_TRIGGERED_TIME_IN_HOURS + 1) + MIN_TRIGGERED_TIME_IN_HOURS;
    }

    /**
     * Uses the device and resets the time.
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
            log.info(String.format("%s \"%s\" didn't break the object \"%s\" while playing with it :D [%s]",
                    houseResident.getClass().getSimpleName(),
                    houseResident.getName(),
                    device.getName(),
                    SmartHouse.getInstance().getSimulation().getFormattedTime()));
        }
    }

    /**
     * Update.
     * @param time the time
     */
    @Override
    public void update(long time) {
        this.time += time;
        this.timeFromLastDispatchedDeviceUsingEvent += time;

        Simulation simulationTime = SmartHouse.getInstance().getSimulation();

        // Sleeping time
        if (!((Child) houseResident).getDispatchedEvent() && !((Child) houseResident).getDispatchedEvent()) {
            if (simulationTime.getHour() >= START_SLEEPING_TIME || simulationTime.getHour() < END_SLEEPING_TIME) {
                houseResident.changeState(new ChildSleepingState(houseResident));
                return;
            }
        }

        // Device using
        if (this.timeFromLastDispatchedDeviceUsingEvent >= triggeredTimeInHours * 3600L * 1000000000L) {
            List<Device> devices = houseResident.getRoom().getDevices().stream()
                    .filter(device -> device.getState() instanceof DeviceIdleState)
                    .collect(Collectors.toList());

            if (devices.isEmpty()) {
                log.info(String.format("Child \"%s\" didn't find any available device in room \"%s\" :( [%s]",
                        houseResident.getName(),
                        houseResident.getRoom().getName(),
                        SmartHouse.getInstance().getSimulation().getFormattedTime()));
            } else {
                Device device = devices.get((int) (Math.random() * devices.size()));
                useDeviceAndResetDeviceUsingEvent(device);
            }
        }
    }
}
