package omo.sem.objects.houseResident.person.adult;

import omo.sem.objects.resident.HouseResident;
import omo.sem.objects.resident.person.adult.Adult;
import omo.sem.objects.resident.person.adult.state.*;
import omo.sem.system.SmartHouse;
import omo.sem.objects.device.Device;
import omo.sem.objects.resident.person.PersonFactory;
import omo.sem.objects.resident.person.PersonGender;
import omo.sem.objects.PhysicalHouse;
import omo.sem.objects.housePartsBuilder.SmartHouseBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class AdultTest {

    SmartHouse system = SmartHouse.getInstance();

    @BeforeEach
    void setup() throws Exception {
        SmartHouseBuilder houseBuilder = new SmartHouseBuilder();

        PhysicalHouse physicalHouse = houseBuilder
                .addFloor(1)
                .addRoom("Bedroom")
                .addDevice("FRIDGE", "Fridge")
                .addPerson("ADULT", "Sebek", PersonGender.MALE)
                .end()
                .addRoom("Living room")
                .end()
                .end()
                .addFloor(2)
                .addRoom("Kitchen")
                .end()
                .end()
                .getResult();

        system.init(physicalHouse);
    }

    @Test
    void checkAdultWaitingState() {
        HouseResident person = PersonFactory.getInstance().getPersons().get(0);

        person.changeState(new AdultWaitingState(person));

        assertTrue(person.getState() instanceof AdultWaitingState);
    }

    @Test
    void checkAdultSleepingState() throws IOException {
        HouseResident person = PersonFactory.getInstance().getPersons().get(0);
        person.changeState(new AdultWaitingState(person));

        system.getSimulation().setHour(2);
        person.update(2 * 3600L * 1000000000L);

        assertTrue(person.getState() instanceof AdultSleepingState);
    }

    @Test
    void checkAdultDeviceUsingState() {
        HouseResident person = PersonFactory.getInstance().getPersons().get(0);
        Device device = system.getHouse().getFloors().get(0).getRooms().get(0).getDevices().get(0);

        ((Adult) person).useObject(device);

        assertTrue(person.getState() instanceof AdultDeviceUsingState);
    }

    @Test
    void checkAdultDeviceFixingState() {
        HouseResident person = PersonFactory.getInstance().getPersons().get(0);
        Device device = system.getHouse().getFloors().get(0).getRooms().get(0).getDevices().get(0);

        device.toBreak();
        device.fix((Adult) person);

        assertTrue(person.getState() instanceof AdultDeviceFixingState);
    }

    @Test
    void checkMaleAdultWorkingState() throws IOException {
        HouseResident male = PersonFactory.getInstance().getPersons().get(0);
        male.changeState(new AdultWaitingState(male));

        system.getSimulation().setHour(15);
        male.update(15 * 3600L * 1000000000L);

        assertTrue(male.getState() instanceof AdultWorkingState);
    }
}