package omo.sem.objects.device;

import omo.sem.objects.device.fridge.state.FridgeBrokenState;
import omo.sem.objects.device.fridge.state.FridgeIdleState;
import omo.sem.objects.device.tv.state.TvIdleState;
import omo.sem.objects.device.tv.state.TvOffState;
import omo.sem.objects.device.tv.state.TvUsingState;
import omo.sem.system.SmartHouse;
import omo.sem.objects.resident.person.PersonFactory;
import omo.sem.objects.resident.person.PersonGender;
import omo.sem.objects.resident.person.adult.Adult;
import omo.sem.objects.PhysicalHouse;
import omo.sem.objects.housePartsBuilder.SmartHouseBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DeviceTest {

    SmartHouse system = SmartHouse.getInstance();

    @BeforeEach
    void setup() {
        SmartHouseBuilder houseBuilder = new SmartHouseBuilder();

        PhysicalHouse physicalHouse = houseBuilder

                .addFloor(1)
                .addRoom("Bedroom")
                .addPerson("ADULT", "Sebek", PersonGender.MALE)
                .addDevice("TV", "Tv")
                .end()
                .addRoom("Living room")
                .end()
                .end()

                .addFloor(2)
                .addRoom("Kitchen")
                .addPerson("ADULT", "Vova", PersonGender.MALE)
                .addDevice("FRIDGE", "Fridge")
                .end()
                .end()
                .getResult();

        system.init(physicalHouse);
    }

    @Test
    void checkBrokenState() {
        //Fridge
        Device device = system.getHouse().getFloors().get(1).getRooms().get(0).getDevices().get(0);

        device.toBreak();

        assertTrue(device.getState() instanceof FridgeBrokenState);
        assertTrue(device.isBroken());
    }

    @Test
    void checkFixingState() {
        //Fridge
        Device device = system.getHouse().getFloors().get(1).getRooms().get(0).getDevices().get(0);

        device.toBreak();
        device.fix((Adult) PersonFactory.getInstance().getPersons().get(0));

        assertEquals("FridgeFixingState", device.getState().getClass().getSimpleName());
        assertTrue(device.isFixing());
    }

    @Test
    void checkCompleteFixing() {
        //Fridge
        Device device = system.getHouse().getFloors().get(1).getRooms().get(0).getDevices().get(0);

        device.toBreak();
        device.fix((Adult) PersonFactory.getInstance().getPersons().get(0));
        device.completeFixing((Adult) PersonFactory.getInstance().getPersons().get(0));

        assertTrue(device.getState() instanceof FridgeIdleState);
        assertFalse(device.isBroken());
    }

    @Test
    void checkUsingState() {
        //TV
        Device device = system.getHouse().getFloors().get(0).getRooms().get(0).getDevices().get(0);

        device.use(PersonFactory.getInstance().getPersons().get(0));

        assertTrue(device.getState() instanceof TvUsingState);
        assertTrue(device.isUsing());
    }

    @Test
    void checkOnState() {
        //TV
        Device device = system.getHouse().getFloors().get(0).getRooms().get(0).getDevices().get(0);

        device.off();
        assertTrue(device.getState() instanceof TvOffState);
        assertTrue(device.isOff());

        device.on();
        assertTrue(device.getState() instanceof TvIdleState);
        assertTrue(device.isOn());
    }

    @Test
    void checkOffState() {
        //TV
        Device device = system.getHouse().getFloors().get(0).getRooms().get(0).getDevices().get(0);

        device.off();

        assertTrue(device.getState() instanceof TvOffState);
        assertTrue(device.isOff());
    }
}