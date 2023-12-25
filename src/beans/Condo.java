package beans;

import rentals.RentalProperty;

public class Condo extends RentalProperty
{
	public int streetNumber;
	public int unitNumber;
    
	public Condo(String address, String city, String state, String postalCode, double rent, int streetNumber, int unitNumber) 
	{
        super(address, city, state, postalCode, rent);
        this.streetNumber = streetNumber;
        this.unitNumber = unitNumber;
    }

    @Override
    public void displayDetails() {

    }

//    @Override
//    public void addObserver(Tenant tenant) {
//
//    }

}
