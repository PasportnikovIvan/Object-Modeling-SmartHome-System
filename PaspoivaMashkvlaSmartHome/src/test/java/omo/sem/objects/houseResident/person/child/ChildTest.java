package omo.sem.objects.houseResident.person.child;

import omo.sem.objects.PhysicalHouse;
import omo.sem.objects.housePartsBuilder.SmartHouseBuilder;
import omo.sem.objects.resident.HouseResident;
import omo.sem.objects.resident.person.*;
import omo.sem.objects.resident.person.child.state.*;
import omo.sem.system.SmartHouse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class ChildTest {

    SmartHouse system = SmartHouse.getInstance();

    @BeforeEach
    void setup() {
        SmartHouseBuilder houseBuilder = new SmartHouseBuilder();

        PhysicalHouse house = houseBuilder
                .addFloor(1)
                .addRoom("Bedroom")
                .addDevice("TV", "Tv")
                .addDevice("SPEAKERS", "Speakers")
                .end()
                .addRoom("Living room")
                .addPerson("CHILD", "Jonny", PersonGender.MALE)
                .end()
                .end()
                .addFloor(2)
                .addRoom("Living room")
                .end()
                .end()
                .getResult();

        system.init(house);
    }

    @Test
    void checkChildSleepingState() {
        HouseResident child = PersonFactory.getInstance().getPersons().get(0);

        child.changeState(new ChildSleepingState(child));

        assertTrue((child.getState() instanceof ChildSleepingState));
    }

    @Test
    void checkChildAwakeState() {
        HouseResident child = PersonFactory.getInstance().getPersons().get(0);

        child.changeState(new ChildAwakeState(child));

        assertTrue((child.getState() instanceof ChildAwakeState));
    }
}
