package omo.sem.objects.houseResident;

import omo.sem.objects.PhysicalHouse;
import omo.sem.objects.housePartsBuilder.SmartHouseBuilder;
import omo.sem.objects.resident.pet.*;
import omo.sem.objects.resident.pet.state.*;
import omo.sem.system.SmartHouse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class PetTest {

    SmartHouse system = SmartHouse.getInstance();

    @BeforeEach
    void setup() {
        SmartHouseBuilder houseBuilder = new SmartHouseBuilder();

        PhysicalHouse PhysicalHouse = houseBuilder
                .addFloor(1)
                .addRoom("Bedroom")
                .addDevice("TV", "Tv")
                .addDevice("SPEAKERS", "Speakers")
                .end()
                .addRoom("Living room")
                .addPet("CHMONYA", "Chmonya")
                .end()
                .end()
                .addFloor(2)
                .addRoom("Living room")
                .end()
                .end()
                .getResult();

        system.init(PhysicalHouse);
    }

    @Test
    void checkPetState() {
        Pet pet = PetFactory.getInstance().getPets().get(0);

        pet.changeState(new PetSleepingState(pet));

        assertTrue(pet.getState() instanceof PetSleepingState);
    }

    @Test
    void checkPetAwakeState() {
        Pet pet = PetFactory.getInstance().getPets().get(0);

        pet.changeState(new PetAwakeState(pet));

        assertTrue(pet.getState() instanceof PetAwakeState);
    }

    @Test
    void checkHungryEvent() throws IOException {
        Pet pet = PetFactory.getInstance().getPets().get(0);

        pet.update(26 * 3600L * 1000000000L);

        assertTrue(pet.getDispatchedHungerEvent());
    }
}
