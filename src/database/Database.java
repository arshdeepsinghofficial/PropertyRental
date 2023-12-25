package database;

import java.util.ArrayList;
import java.util.List;

import rentals.Lease;
import rentals.RentalProperty;
import rentals.Tenant;

public class Database 
{
	
	public static List<Object> mockObject;
	
	//public Database()
	static
	{
		mockObject = new ArrayList<Object>();
		mockObject.add(new ArrayList<RentalProperty>());
		mockObject.add(new ArrayList<RentalProperty>());
		mockObject.add(new ArrayList<RentalProperty>());
		mockObject.add(new ArrayList<Tenant>());
		mockObject.add(new ArrayList<Lease>());
	}

}


