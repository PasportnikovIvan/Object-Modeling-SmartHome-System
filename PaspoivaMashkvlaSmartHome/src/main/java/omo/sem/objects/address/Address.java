package omo.sem.objects.address;

/**
 * Class representing the Address of the house.
 * **/
public class Address {

    String city;
    int PSC;
    String streetAndHouseNum;

    public Address(String  city, int PSC, String streetAndHouseNum) {
        this.city = city;
        this.PSC = PSC;
        this.streetAndHouseNum = streetAndHouseNum;
    }
}
