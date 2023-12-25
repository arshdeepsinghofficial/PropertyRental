package rentals;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import beans.Apartment;
import beans.Condo;
import beans.House;
import beans.LeaseBuilder;
import database.*;

public class RentalProperty 
{
	public final String PROPERTY_RENTED = "Rented";
	public final String PROPERTY_VACANT = "Vacant";
	public final String PROPERTY_ALL = "All";
	public final String PAYMENT_PAID = "Paid";
	public final String PAYMENT_UNPAID = "Unpaid";
	public String address;
	public String city;
	public String state;
	public String postalCode;
	public double rent;
	public Tenant tenant;
	public Lease lease;
	public LeaseBuilder leaseBuilder;
	public String rentalStatus;
    public String paymentStatus;
	Database database;
	String response;
	String filter;
	
	// Constructor
	public RentalProperty()
	{
		database = new Database();
		this.tenant = new Tenant();
		this.lease= new Lease();
		this.leaseBuilder = new LeaseBuilder(this);
		filter = PROPERTY_ALL;
	}
	
	public RentalProperty(String address, String city, String state, String postalCode, double rent) {
		this.address = address;
		this.city = city;
		this.state = state;
		this.postalCode = postalCode;
		this.rent = rent;
		this.tenant = new Tenant();
		this.lease= new Lease();
		this.leaseBuilder = new LeaseBuilder(this);
	
		database= new Database();
		filter = PROPERTY_ALL;
		
	}

	// Getter methods for property fields
	public String getAddress() {
		return address;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public double getRent() {
		return rent;
	}

	// Abstract method for displaying property details
	public void displayDetails()
	{
		
	}

	// Factory method to create rental properties
	public String createRentalProperty(int propertyType, String address, String city, String state, String postalCode, double rent, String... args) 
	{
		RentalProperty property = null;
		switch (propertyType) {
		case 1:
			property = new Apartment(address, city, state, postalCode, rent, args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]));
			property.rentalStatus = PROPERTY_VACANT;
			property.paymentStatus = PAYMENT_UNPAID;
			((List<RentalProperty>)Database.mockObject.get(0)).add(property);
			break;
		case 2:
			property = new Condo(address, city, state, postalCode, rent, Integer.parseInt(args[0]), Integer.parseInt(args[1]));
			property.rentalStatus = PROPERTY_VACANT;
			property.paymentStatus = PAYMENT_UNPAID;
			((List<RentalProperty>)Database.mockObject.get(1)).add(property);

			break;
		case 3:
			property = new House(address, city, state, postalCode, rent, Integer.parseInt(args[0]) );
			property.rentalStatus = PROPERTY_VACANT;
			property.paymentStatus = PAYMENT_UNPAID;
			((List<RentalProperty>)Database.mockObject.get(2)).add(property);

			break;
		default:
			break;
		}
		
		response = "Property Added Successfully";
		return response;

	}
	
	public String rentAUnit(String name,String email, int propertyType,String city,String state,String postalCode,String startDate,String endDate, String... args)
	{
		this.tenant = new Tenant(name,email);
		
		
		if(tenant.isTenantRegistered(name, email))
		{
			if(isPropertyPresent(propertyType,city,state,postalCode,args))
			{
				switch (propertyType) 
				{
				case 1:
					for(Apartment property : ((List<Apartment>)Database.mockObject.get(0)))
					{
						if(property.city.equals(city) && property.state.equals(state) && property.postalCode.equals(postalCode) && property.apartmentNumber.equals(args[0]))
						{
							this.tenant = new Tenant(name,email);
							
							property.tenant = this.tenant;
							property.rentalStatus = PROPERTY_RENTED;
							try
							{
								SimpleDateFormat currentDateFormat = new SimpleDateFormat("dd/MM/yyyy");
								leaseBuilder.tenant = this.tenant;
								leaseBuilder.rentalProperty = property;
								leaseBuilder.rentAmount = property.rent;
								leaseBuilder.startDate = new SimpleDateFormat("dd/MM/yyyy").parse(startDate);
								leaseBuilder.endDate = new SimpleDateFormat("dd/MM/yyyy").parse(endDate);
							}
							catch(Exception e)
							{
								e.printStackTrace();
							}
							property.lease = new Lease(leaseBuilder);
							property.paymentStatus = PAYMENT_UNPAID;
							tenant.updateTenantRentalList(property.lease);
							lease.updateLeaseList(property.lease);
							
							Iterator<Tenant> t = ((List<Tenant>)Database.mockObject.get(3)).iterator();
							while(t.hasNext())
							{
								Tenant tenant = t.next();
								for(int i = 0;i<= tenant.interests.size()-1;i++)
								//for(RentalProperty rp : tenant.interests)
								{
									RentalProperty rp = tenant.interests.get(i);
									if((rp instanceof Apartment) && rp.city.equals(city) && rp.state.equals(state) && rp.postalCode.equals(postalCode) && ((Apartment)rp).apartmentNumber.equals(args[0]))
									{
										tenant.interests.remove(rp);
									}
									if((rp instanceof Condo) && rp.city.equals(city) && rp.state.equals(state) && rp.postalCode.equals(postalCode) && ((Condo)rp).streetNumber==Integer.parseInt(args[0]) && ((Condo)rp).unitNumber==Integer.parseInt(args[1]))
									{
										tenant.interests.remove(rp);
									}
									if((rp instanceof House) && rp.city.equals(city) && rp.state.equals(state) && rp.postalCode.equals(postalCode) && ((House)rp).streetNumber==Integer.parseInt(args[0]))
									{
										tenant.interests.remove(rp);
									}
								}
								
							}
							
							response = "Property Rented Successfully to " + property.tenant.name;
						}
					}
					break;
				case 2:
					for(Condo property : ((List<Condo>)Database.mockObject.get(1)))
					{
						if(property.city.equals(city) && property.state.equals(state) && property.postalCode.equals(postalCode) && property.streetNumber==Integer.parseInt(args[0]) && property.unitNumber==Integer.parseInt(args[1]))
						{
							this.tenant = new Tenant(name,email);

							property.tenant = this.tenant;
							property.rentalStatus = PROPERTY_RENTED;
							try
							{
								SimpleDateFormat currentDateFormat = new SimpleDateFormat("dd/MM/yyyy");
								leaseBuilder.tenant = this.tenant;
								leaseBuilder.rentalProperty = property;
								leaseBuilder.rentAmount = property.rent;
								leaseBuilder.startDate = new SimpleDateFormat("dd/MM/yyyy").parse(startDate);
								leaseBuilder.endDate = new SimpleDateFormat("dd/MM/yyyy").parse(endDate);
							}
							catch(Exception e)
							{
								e.printStackTrace();
							}
							property.lease = new Lease(leaseBuilder);
							property.paymentStatus = PAYMENT_UNPAID;
							tenant.updateTenantRentalList(property.lease);
							lease.updateLeaseList(property.lease);
							
							Iterator<Tenant> t = ((List<Tenant>)Database.mockObject.get(3)).iterator();
							while(t.hasNext())
							{
								Tenant tenant = t.next();
								for(int i = 0;i<= tenant.interests.size()-1;i++)
								//for(RentalProperty rp : tenant.interests)
								{
									RentalProperty rp = tenant.interests.get(i);
									if((rp instanceof Apartment) && rp.city.equals(city) && rp.state.equals(state) && rp.postalCode.equals(postalCode) && ((Apartment)rp).apartmentNumber.equals(args[0]))
									{
										tenant.interests.remove(rp);
									}
									if((rp instanceof Condo) && rp.city.equals(city) && rp.state.equals(state) && rp.postalCode.equals(postalCode) && ((Condo)rp).streetNumber==Integer.parseInt(args[0]) && ((Condo)rp).unitNumber==Integer.parseInt(args[1]))
									{
										tenant.interests.remove(rp);
									}
									if((rp instanceof House) && rp.city.equals(city) && rp.state.equals(state) && rp.postalCode.equals(postalCode) && ((House)rp).streetNumber==Integer.parseInt(args[0]))
									{
										tenant.interests.remove(rp);
									}
								}
							}
							
							response = "Property Rented Successfully to " + property.tenant.name;

						}
					}						
				break;
				case 3:
					for(House property : ((List<House>)Database.mockObject.get(2)))
					{
						if(property.city.equals(city) && property.state.equals(state) && property.postalCode.equals(postalCode) && property.streetNumber==Integer.parseInt(args[0]))
						{
							this.tenant = new Tenant(name,email);

							property.tenant = this.tenant;
							property.rentalStatus = PROPERTY_RENTED;
							try
							{
								SimpleDateFormat currentDateFormat = new SimpleDateFormat("dd/MM/yyyy");
								leaseBuilder.tenant = this.tenant;
								leaseBuilder.rentalProperty = property;
								leaseBuilder.rentAmount = property.rent;
								leaseBuilder.startDate = new SimpleDateFormat("dd/MM/yyyy").parse(startDate);
								leaseBuilder.endDate = new SimpleDateFormat("dd/MM/yyyy").parse(endDate);
							}
							catch(Exception e)
							{
								e.printStackTrace();
							}
							property.lease = new Lease(leaseBuilder);
							property.paymentStatus = PAYMENT_UNPAID;
							tenant.updateTenantRentalList(property.lease);
							lease.updateLeaseList(property.lease);
							
							Iterator<Tenant> t = ((List<Tenant>)Database.mockObject.get(3)).iterator();
							while(t.hasNext())
							{
								Tenant tenant = t.next();
								for(int i = 0;i<= tenant.interests.size()-1;i++)
								//for(RentalProperty rp : tenant.interests)
								{
									RentalProperty rp = tenant.interests.get(i);
									if((rp instanceof Apartment) && rp.city.equals(city) && rp.state.equals(state) && rp.postalCode.equals(postalCode) && ((Apartment)rp).apartmentNumber.equals(args[0]))
									{
										tenant.interests.remove(rp);
									}
									if((rp instanceof Condo) && rp.city.equals(city) && rp.state.equals(state) && rp.postalCode.equals(postalCode) && ((Condo)rp).streetNumber==Integer.parseInt(args[0]) && ((Condo)rp).unitNumber==Integer.parseInt(args[1]))
									{
										tenant.interests.remove(rp);
									}
									if((rp instanceof House) && rp.city.equals(city) && rp.state.equals(state) && rp.postalCode.equals(postalCode) && ((House)rp).streetNumber==Integer.parseInt(args[0]))
									{
										tenant.interests.remove(rp);
									}
								}
							}
							
							response = "Property Rented Successfully to " + property.tenant.name;
						}
					}						
					break;
				}
			}
			else
			{
				response = "Property details you have entered does not exists";
			}
		}
		else
		{
			response = "Tenant is not registered in the system.Please add a Tenant first";
		}
		return response;
	}
	
	public List getAllProperties()
	{
		ArrayList <Apartment> apartmentList = ((ArrayList<Apartment>)Database.mockObject.get(0));
		ArrayList <Condo> condoList = ((ArrayList<Condo>)Database.mockObject.get(1));
		ArrayList <House> houseList = ((ArrayList<House>)Database.mockObject.get(2));
		ArrayList<ArrayList<String[]>> output = new ArrayList<>();
		ArrayList<String[]> temp1 = new ArrayList<>();
		ArrayList<String[]> temp2 = new ArrayList<>();
		ArrayList<String[]> temp3 = new ArrayList<>();
		
		for(Apartment apartment : apartmentList)
		{
			String [] apartmentData = new String[9];
			if(filter.equals(PROPERTY_RENTED) && (!apartment.rentalStatus.equals(PROPERTY_RENTED)))
			{
				continue;
			}
			if(filter.equals(PROPERTY_VACANT) && (!apartment.rentalStatus.equals(PROPERTY_VACANT)))
			{
				continue;
			}
			apartmentData[0] = apartment.address;
			apartmentData[1] = apartment.city;
			apartmentData[2] = apartment.state;
			apartmentData[3] = apartment.postalCode;
			apartmentData[4] = apartment.rent+"";
			apartmentData[5] = apartment.apartmentNumber;
			apartmentData[6] = apartment.numberOfBedrooms+"";
			apartmentData[7] = apartment.numberOfBathrooms+"";
			apartmentData[8] = apartment.squarefootage+"";
			
			temp1.add(apartmentData);		
		}
		output.add(temp1);
		
		for(Condo condo : condoList)
		{
			String [] condoData = new String[7];
			if(filter.equals(PROPERTY_RENTED) && (!condo.rentalStatus.equals(PROPERTY_RENTED)))
			{
				continue;
			}
			if(filter.equals(PROPERTY_VACANT) && (!condo.rentalStatus.equals(PROPERTY_VACANT)))
			{
				continue;
			}
			condoData[0] = condo.address;
			condoData[1] = condo.city;
			condoData[2] = condo.state;
			condoData[3] = condo.postalCode;
			condoData[4] = condo.rent+"";
			condoData[5] = condo.streetNumber+"";
			condoData[6] = condo.unitNumber+"";
	
			temp2.add(condoData);		
		}
		output.add(temp2);
		
		for(House house : houseList)
		{
			String [] houseData = new String[6];
			if(filter.equals(PROPERTY_RENTED) && (!house.rentalStatus.equals(PROPERTY_RENTED)))
			{
				continue;
			}
			if(filter.equals(PROPERTY_VACANT) && (!house.rentalStatus.equals(PROPERTY_VACANT)))
			{
				continue;
			}
			houseData[0] = house.address;
			houseData[1] = house.city;
			houseData[2] = house.state;
			houseData[3] = house.postalCode;
			houseData[4] = house.rent+"";
			houseData[5] = house.streetNumber+"";
	
			temp3.add(houseData);		
		}
		output.add(temp3);
		
		return output;
	}
	
	public List getAllRentedUnits()
	{
		filter = PROPERTY_RENTED;
		ArrayList<ArrayList<String[]>> output = (ArrayList<ArrayList<String[]>>)getAllProperties();
		filter = PROPERTY_ALL;
		return output;
	}
	
	public List getAllVacantUnits()
	{
		filter = PROPERTY_VACANT;
		ArrayList<ArrayList<String[]>> output = (ArrayList<ArrayList<String[]>>)getAllProperties();
		filter = PROPERTY_ALL;
		return output;
	}
	
	public boolean isPropertyPresent(int propertyType,String city,String state,String postalCode, String... args)
	{
		boolean exists = false;
		
		switch (propertyType) 
		{
		case 1:
			for(Apartment property : ((List<Apartment>)Database.mockObject.get(0)))
			{
				if(property.city.equals(city) && property.state.equals(state) && property.postalCode.equals(postalCode) && property.apartmentNumber.equals(args[0]))
				{
					exists = true;
					break;
				}
			}
			break;
			
		case 2:
			for(Condo property : ((List<Condo>)Database.mockObject.get(1)))
			{
				if(property.city.equals(city) && property.state.equals(state) && property.postalCode.equals(postalCode) && property.streetNumber==Integer.parseInt(args[0]) && property.unitNumber==Integer.parseInt(args[1]))
				{
					exists = true;
					break;
				}
			}
			break;
			
		case 3:
			for(House property : ((List<House>)Database.mockObject.get(2)))
			{
				if(property.city.equals(city) && property.state.equals(state) && property.postalCode.equals(postalCode) && property.streetNumber==Integer.parseInt(args[0]))
				{
					exists = true;
					break;
				}
			}
			break;
		}
		
		return exists;
	}
	
	public static RentalProperty getPropertyDetails(int propertyType,String city,String state,String postalCode, String... args)
	{
		RentalProperty prpty = null;
		switch (propertyType) 
		{
		case 1:
			for(Apartment property : ((List<Apartment>)Database.mockObject.get(0)))
			{
				if(property.city.equals(city) && property.state.equals(state) && property.postalCode.equals(postalCode) && property.apartmentNumber.equals(args[0]))
				{
					prpty = property;
					return property;
				}
			}
			break;
			
		case 2:
			for(Condo property : ((List<Condo>)Database.mockObject.get(1)))
			{

				if(property.city.equals(city) && property.state.equals(state) && property.postalCode.equals(postalCode) && property.streetNumber==Integer.parseInt(args[0]) && property.unitNumber==Integer.parseInt(args[1]))
				{
					prpty = property;
					return property;
				}
			}
			break;
			
		case 3:
			for(House property : ((List<House>)Database.mockObject.get(2)))
			{
				if(property.city.equals(city) && property.state.equals(state) && property.postalCode.equals(postalCode) && property.streetNumber==Integer.parseInt(args[0]))
				{
					prpty = property;
					return property;
				}
			}
			break;
		}
		
		return prpty;

	}
	
	public List getCustomerInterestPropertiesVacancy()
	{
		ArrayList<String[]> notificationDetailsList = new ArrayList<>();
		List<Tenant> tenantList = (List<Tenant>)Database.mockObject.get(3);
		int size = tenantList.size();
		if(tenantList.size()!=0)
		{
			for(Tenant tenant : (List<Tenant>)Database.mockObject.get(3))
			{
				if(tenant.interests != null && tenant.interests.size() !=0)
				{
					for(RentalProperty property : tenant.interests)
					{
						String [] data = new String[10];
						
						try {
							Date currentDate = new Date();
							Date leaseEndDate = property.lease.getEndDate();
							if(((property.lease.getEndDate() != null)&&(currentDate.after(property.lease.getEndDate()))) || property.rentalStatus.equals(PROPERTY_VACANT))
							{
								property.rentalStatus = PROPERTY_VACANT;
								if(property instanceof Apartment)
								{
									data[0] = "1";//ProperyType
									data[1] = tenant.name;
									data[2] = tenant.email;
									data[3] = property.address;
									data[4] = property.city;
									data[5] = property.state;
									data[6] = property.postalCode;
									data[7] = property.rent+"";
									data[8] = ((Apartment) property).apartmentNumber+"";
								}
								else if(property instanceof Condo)
								{
									data[0] = "2";//ProperyType
									data[1] = tenant.name;
									data[2] = tenant.email;
									data[3] = property.address;
									data[4] = property.city;
									data[5] = property.state;
									data[6] = property.postalCode;
									data[7] = property.rent+"";
									data[8] = ((Condo) property).streetNumber+"";
									data[9] = ((Condo) property).unitNumber+"";
								}
								else
								{
									data[0] = "3";//ProperyType
									data[1] = tenant.name;
									data[2] = tenant.email;
									data[3] = property.address;
									data[4] = property.city;
									data[5] = property.state;
									data[6] = property.postalCode;
									data[7] = property.rent+"";
									data[8] = ((House) property).streetNumber+"";
								}

								notificationDetailsList.add(data);

							}
						}
						catch(Exception e)
						{
							System.out.println("Date Error");
						}
					}
				}
			}
		}

		return notificationDetailsList;
	}
	
	public String updatePaymentStatus(String name, String email, String paymentStatus,  int propertyType,String city,String state,String postalCode,String... args)
	{

		this.tenant = new Tenant(name,email);
		
		
		if(tenant.isTenantRegistered(name, email))
		{
			if(isPropertyPresent(propertyType,city,state,postalCode,args))
			{
				switch (propertyType) 
				{
				case 1:
					for(Apartment property : ((List<Apartment>)Database.mockObject.get(0)))
					{
						if(property.city.equals(city) && property.state.equals(state) && property.postalCode.equals(postalCode) && property.apartmentNumber.equals(args[0]))
						{
							if(property.tenant.name.equals(name) && property.tenant.email.equals(email))
							{
								property.paymentStatus = PAYMENT_PAID;
								response = "Property rent details updated successfully for Tenant : " + property.tenant.name;
							}
							else
							{
								response = "Tenant has not signed a lease yet for this property.";
							}
						}
					}
					break;
				case 2:
					for(Condo property : ((List<Condo>)Database.mockObject.get(1)))
					{
						if(property.city.equals(city) && property.state.equals(state) && property.postalCode.equals(postalCode) && property.streetNumber==Integer.parseInt(args[0]) && property.unitNumber==Integer.parseInt(args[1]))
						{
							
								if(property.tenant.name.equals(name) && property.tenant.email.equals(email))
								{
									property.paymentStatus = PAYMENT_PAID;
									response = "Property rent details updated successfully for Tenant : " + property.tenant.name;
								}
								else
								{
									response = "Tenant has not signed a lease yet for this property.";
								}
							
						}
					}						
				break;
				case 3:
					for(House property : ((List<House>)Database.mockObject.get(2)))
					{
						if(property.city.equals(city) && property.state.equals(state) && property.postalCode.equals(postalCode) && property.streetNumber==Integer.parseInt(args[0]))
						{
								if(property.tenant.name.equals(name) && property.tenant.email.equals(email))
								{
									property.paymentStatus = PAYMENT_PAID;
									response = "Property rent details updated successfully for Tenant : " + property.tenant.name;
								}
								else
								{
									response = "Tenant has not signed a lease yet for this property.";
								}
						}
					}						
					break;
				}
			}
			else
			{
				response = "Property details you have entered does not exists";
			}
		}
		else
		{
			response = "Tenant is not registered in the system.Please add a Tenant first";
		}
		return response;
	}
	
	public List getNonPaidRental()
	{
		ArrayList <Apartment> apartmentList = ((ArrayList<Apartment>)Database.mockObject.get(0));
		ArrayList <Condo> condoList = ((ArrayList<Condo>)Database.mockObject.get(1));
		ArrayList <House> houseList = ((ArrayList<House>)Database.mockObject.get(2));
		ArrayList<ArrayList<String[]>> output = new ArrayList<>();
		ArrayList<String[]> temp1 = new ArrayList<>();
		ArrayList<String[]> temp2 = new ArrayList<>();
		ArrayList<String[]> temp3 = new ArrayList<>();
		
		for(Apartment apartment : apartmentList)
		{
			String [] apartmentData = new String[9];
			
			if(apartment != null && apartment.paymentStatus.equals(PAYMENT_UNPAID) && apartment.rentalStatus.equals(PROPERTY_RENTED))
			{
				apartmentData[0] = apartment.address;
				apartmentData[1] = apartment.city;
				apartmentData[2] = apartment.state;
				apartmentData[3] = apartment.postalCode;
				apartmentData[4] = apartment.rent+"";
				apartmentData[5] = apartment.apartmentNumber;
				apartmentData[6] = apartment.numberOfBedrooms+"";
				apartmentData[7] = apartment.numberOfBathrooms+"";
				apartmentData[8] = apartment.squarefootage+"";

				temp1.add(apartmentData);	
			}
		}
		output.add(temp1);
		
		for(Condo condo : condoList)
		{
			String [] condoData = new String[7];
			
			if(condo != null && condo.paymentStatus.equals(PAYMENT_UNPAID) && condo.rentalStatus.equals(PROPERTY_RENTED))
			{
			condoData[0] = condo.address;
			condoData[1] = condo.city;
			condoData[2] = condo.state;
			condoData[3] = condo.postalCode;
			condoData[4] = condo.rent+"";
			condoData[5] = condo.streetNumber+"";
			condoData[6] = condo.unitNumber+"";
	
			temp2.add(condoData);	
			}
		}
		output.add(temp2);
		
		for(House house : houseList)
		{
			String [] houseData = new String[6];

			if(house != null && house.paymentStatus.equals(PAYMENT_UNPAID) && house.rentalStatus.equals(PROPERTY_RENTED))
			{
			houseData[0] = house.address;
			houseData[1] = house.city;
			houseData[2] = house.state;
			houseData[3] = house.postalCode;
			houseData[4] = house.rent+"";
			houseData[5] = house.streetNumber+"";
	
			temp3.add(houseData);	
			}
		}
		output.add(temp3);
		
		return output;
		
	}
	
}
