package beans;

import rentals.RentalProperty;

public class House extends RentalProperty
{
	public int streetNumber;
	 
	 public House(String address, String city, String state, String postalCode, double rent, int streetNumber) 
	 {
         super(address, city, state, postalCode, rent);
         this.streetNumber = streetNumber;
     }

     @Override
     public void displayDetails() {

     }

//     @Override
//     public void addObserver(Tenant tenant) {
//
//     }
}
