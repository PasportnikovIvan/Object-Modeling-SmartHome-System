package omo.sem.objects.location;

import omo.sem.objects.PhysicalHouse;
import omo.sem.system.SmartHouse;
import omo.sem.objects.resident.person.PersonGender;
import omo.sem.objects.housePartsBuilder.SmartHouseBuilder;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HouseTest {

   @Test
    void checkHouseBuilder() {
       SmartHouse system = SmartHouse.getInstance();
       SmartHouseBuilder houseBuilder = new SmartHouseBuilder();

       PhysicalHouse physicalHouse = houseBuilder
               .addFloor(1)
                    .addRoom("Bedroom")
                        .addPerson("ADULT", "Sebek", PersonGender.MALE)
                        .end()
                    .addRoom("Living room")
                  .end()
                    .end()
               .addFloor(2)
                    .addRoom("Kitchen")
                    .addDevice("FRIDGE", "Fridge")
                    .end()
                    .end()
               .getResult();

       system.init(physicalHouse);

       assertEquals(2, physicalHouse.getFloors().size());
       assertEquals(2, physicalHouse.getFloors().get(0).getRooms().size());
       assertEquals(0, physicalHouse.getFloors().get(0).getRooms().get(0).getDevices().size());
       assertEquals(1, physicalHouse.getFloors().get(1).getRooms().get(0).getDevices().size());
   }
  
}