package beans;

import rentals.RentalProperty;

public class Apartment extends RentalProperty 
{
	public String apartmentNumber;
	public int numberOfBedrooms;
	public int numberOfBathrooms;
	public int squarefootage;
    public Apartment(String address, String city, String state, String postalCode, double rent, String apartmentNumber, int numberOfBedrooms, int numberOfBathrooms, int squarefootage) 
    {
        super(address, city, state, postalCode, rent);
        this.apartmentNumber = apartmentNumber;
        this.numberOfBedrooms = numberOfBedrooms;
        this.numberOfBathrooms = numberOfBathrooms;
        this.squarefootage = squarefootage;
    }

    @Override
    public void displayDetails() {

    }

   // @Override
   // public void addObserver(Tenant tenant) {

}

