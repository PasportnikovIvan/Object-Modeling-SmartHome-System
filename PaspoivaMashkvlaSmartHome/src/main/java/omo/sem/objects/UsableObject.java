package omo.sem.objects;

import omo.sem.objects.resident.HouseResident;

/**
 * Interface representing usable object.
 */
public interface UsableObject {
    /**
     * Returns name of the object.
     * @return name
     */
    String getName();

    /**
     * Use the object.
     * @param houseResident houseResident that uses object
     */
    void use(HouseResident houseResident);

    /**
     * UnUse the object.
     * @param inhabitant inhabitant that unUses object
     */
    void unUse(HouseResident inhabitant);

    /**
     * Returns time that object is supposed to be used.
     * @return time
     */
    double getUsageTimeInHour();

    /**
     * Checks if object is being used.
     * @return true if being used
     */
    boolean isUsing();

}
