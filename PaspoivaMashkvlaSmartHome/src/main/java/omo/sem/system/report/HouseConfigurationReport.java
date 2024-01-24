package omo.sem.system.report;

import omo.sem.objects.PhysicalHouse;
import omo.sem.objects.resident.pet.Pet;
import omo.sem.objects.resident.pet.PetFactory;
import omo.sem.system.SmartHouse;
import omo.sem.objects.device.Device;
import omo.sem.objects.resident.person.Person;
import omo.sem.objects.resident.person.PersonFactory;
import omo.sem.objects.Floor;
import omo.sem.objects.Room;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for generating house configuration report.
 */
public class HouseConfigurationReport {
    private FileWriter houseConfReport = null;

    public HouseConfigurationReport() {
    }

    /**
     * Generates report.
     * @throws IOException writing to file is unsuccessful
     */
    public void generateReport() throws IOException {
        try {
            this.houseConfReport = new FileWriter("report/HouseConfigurationReport.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        PhysicalHouse physicalHouse = SmartHouse.getInstance().getHouse();
        List<Person> persons = new ArrayList<>(PersonFactory.getInstance().getPersons());
        List<Pet> pets = new ArrayList<>(PetFactory.getInstance().getPets());
        int amountOfPeople = persons.size();

        houseConfReport.write("------------------------------------House------------------------------------\n");
        houseConfReport.write("House has " + physicalHouse.getFloors().size() + " floor(s).\n");

        for (Floor floor : physicalHouse.getFloors()) {
            houseConfReport.write("Floor " + "number " + floor.getLevel() + " has "
                + floor.getRooms().size() + " rooms: \n");
            for (Room room : floor.getRooms()) {
                houseConfReport.write(String.format("Room \"%s\" has %s device(s): \n",
                        room.getName(),
                        room.getDevices().size()));
                for (Device device : room.getDevices()) {
                    houseConfReport.write(device.getName() + "\n");
                }
            }
        }
        houseConfReport.write("-----------------------------------Residents---------------------------------\n");
        houseConfReport.write("There are " + amountOfPeople + " residents living in the house: "
                + persons.size() + " people and " + pets.size() + " pet(s).\n");

        for (Person person : persons) {
            houseConfReport.write(String.format("%s \"%s\" is %s\n",
                    person.getClass().getSimpleName(),
                    person.getName(),
                    person.getGender()));
        }

        houseConfReport.write("Pets: \n");
        for (Pet pet : pets) {
            houseConfReport.write(String.format("%s \"%s\" \n",
                    pet.getClass().getSimpleName(),
                    pet.getName()));
        }

        houseConfReport.flush();
        houseConfReport.close();
    }

}
