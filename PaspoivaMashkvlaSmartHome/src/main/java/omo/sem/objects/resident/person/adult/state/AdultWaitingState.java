package omo.sem.objects.resident.person.adult.state;

import omo.sem.objects.item.Item;
import omo.sem.objects.item.ItemFactory;
import omo.sem.objects.resident.HouseResident;
import omo.sem.objects.resident.HouseResidentState;
import omo.sem.system.SmartHouse;
import omo.sem.system.simulation.Simulation;
import omo.sem.system.task.TaskSystem;
import omo.sem.objects.device.Device;
import omo.sem.objects.device.DeviceIdleState;
import omo.sem.objects.device.DeviceFactory;
import omo.sem.objects.resident.person.PersonGender;
import omo.sem.objects.resident.person.adult.Adult;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class representing adult waiting state.
 */
public class AdultWaitingState extends HouseResidentState {
    // Constants
    private final double WAITING_TIME_IN_HOUR = 0.4;
    private final int START_WORKING_TIME = 10;
    private final int END_WORKING_TIME = 17;
    private final int START_SLEEPING_TIME = 23;
    private final int END_SLEEPING_TIME = 7;

    public AdultWaitingState(HouseResident houseResident) {
        super(houseResident);
    }

    /**
     * Update.
     * @param time the time
     */
    @Override
    public void update(long time) {
        this.time += time;

        Simulation simulationTime = SmartHouse.getInstance().getSimulation();
        TaskSystem taskSystem = SmartHouse.getInstance().getTaskSystem();

        // Sleeping time
        if (simulationTime.getHour() >= START_SLEEPING_TIME || simulationTime.getHour() < END_SLEEPING_TIME) {
            houseResident.changeState(new AdultSleepingState(houseResident));
            return;
        }

        // Work time
        if (simulationTime.getHour() >= START_WORKING_TIME && simulationTime.getHour() < END_WORKING_TIME
                && ((Adult) houseResident).getGender() == PersonGender.MALE) {
            houseResident.changeState(new AdultWorkingState(houseResident));
            return;
        }

        // Check tasks
        if (!taskSystem.isEmpty()) {
            taskSystem.getTask().apply((Adult) houseResident);
            return;
        }

        // Delay between using items or devices
        if (this.time < WAITING_TIME_IN_HOUR * 3600L * 1000000000L) return;

        if (Math.random() < 0.5) {
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
                    device.use(houseResident);
                }
            } else {
                devices.get((int) (Math.random() * devices.size())).use(houseResident);
            }
        } else {
            List<Item> items = houseResident.getRoom().getItems().stream()
                    .filter(item -> !item.isUsing())
                    .collect(Collectors.toList());

            if (items.isEmpty()) {
                List<Item> allItems = ItemFactory.getInstance().getItems().stream()
                        .filter(item -> !item.isUsing())
                        .collect(Collectors.toList());

                if (!allItems.isEmpty()) {
                    Item item = allItems.get((int) (Math.random() * allItems.size()));
                    houseResident.moveTo(item.getRoom());
                    item.use(houseResident);
                }
            } else {
                items.get((int) (Math.random() * items.size())).use(houseResident);
            }
        }
        }
}
