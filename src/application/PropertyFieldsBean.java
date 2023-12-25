package application;

import java.util.ArrayList;
import java.util.List;

import controller.StartegyController;

public class PropertyFieldsBean 
{
	public String name;
	public String email;
	public int propertyType = 0;
	public String address;
	public String city;
	public String state;
	public String postalCode;
	public Double rent;
	public String apartmentNumber;
	public String numberOfBedrooms;
	public String numberOfBathrooms;
	public String squareFootage;
	public String condoStreetNumber;
	public String unitNumber;
	public String houseStreetNumber;
	public String startDate;
	public String endDate;
	public String paymentStatus;
	ArrayList<String[]> interestedProperties ;
	List list;
	
	String serverResponse;
	StartegyController controller = new StartegyController();
	
}
