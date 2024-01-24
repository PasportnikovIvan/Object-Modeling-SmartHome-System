package omo.sem.objects.device.fridge.state;

import omo.sem.system.SmartHouse;
import omo.sem.objects.device.Device;
import omo.sem.objects.device.DeviceIdleState;

import java.util.logging.Logger;

/**
 * Class representing the Fridge idle state.
 */
public class FridgeIdleState extends DeviceIdleState {
    // Logger
    private static final Logger log = Logger.getLogger(FridgeIdleState.class.getName());

    /**
     * Instantiates a new Fridge idle state.
     *
     * @param device the device
     */
    public FridgeIdleState(Device device) {
        super(device);
        this.ELECTRICITY_CONSUMPTION = 5;

        // Logging
        log.info(String.format("Fridge in room \"%s\" is not being used now [%s]",
                device.getRoom().getName(),
                SmartHouse.getInstance().getSimulation().getFormattedTime()));
    }

    /**
     * Update.
     * @param time the time
     */
    @Override
    public void update(long time) {
        // Wear out time
        if (device.getTime() > device.getOperatingTimeInHours() * 3600L * 1000000000L) {
            device.changeState(new FridgeBrokenState(device));
        }

        this.time += time;
        device.setTime(device.getTime() + time);

        // Consumption
        device.setCurrentElectricityConsumption(device.getCurrentElectricityConsumption()
                + (ELECTRICITY_CONSUMPTION / (3600D * 1000000000L)) * time);

    }
}
