package omo.sem.objects.resident;

/**
 * Abstract class representing house resident state
 */
public abstract class HouseResidentState {
    // References
    protected HouseResident houseResident;

    // Status
    protected long time = 0;

    public HouseResidentState(HouseResident houseResident) {
        this.houseResident = houseResident;
    }

    /**
     * Update
     * @param time the time
     */
    public abstract void update(long time);
}