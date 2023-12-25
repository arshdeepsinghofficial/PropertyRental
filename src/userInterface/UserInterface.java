package userInterface;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import controller.StartegyController;
import rentals.Lease;
import rentals.RentalProperty;;

public class UserInterface 	
{
	protected int userChoice = 0;
	protected String input = "";
	protected String address;
    protected String city;
    protected String state;
    protected String postalCode;
    protected double rent;
    protected String civicAddress;
    protected String apartmentNumber;
    protected int numberOfApartments;
    protected int numberOfBedrooms;
    protected int numberOfBathrooms;
    protected int squareFootage;
    protected String streetNumber;
    public String unitNumber;
    public int propertyType;
    public String name;
    public String email;
    public String startDate;
    public String endDate;
    public String paymentStatus;
    String [][] apartmentList; 
    ArrayList<String[]> interestedProperties ;
    StartegyController controller;
    String serverResponse = "";
    List list;
	public static void main(String areg[])
	{
		UserInterface userInterface = new UserInterface();
		userInterface.controller = new StartegyController();
		System.out.println("Welcome to the RentalProperties");
		do
		{
			userInterface.printMenu();
			userInterface.setStrategy();
			userInterface.executeStrategy();
		}while(userInterface.userChoice != 12);
		
	}
	public void printMenu()
	{
		System.out.println("Please choose an option");
		System.out.println("1. Add a property");
		System.out.println("2. Add a tenant");
		System.out.println("3. Rent a unit");
		System.out.println("4. Display properties");
		System.out.println("5. Display tenants");
		System.out.println("6. Display Rented Units");
		System.out.println("7. Display Vacant Units");
		System.out.println("8. Display all leases");
		System.out.println("9. NotifyVacantProperties");
		System.out.println("10. Update Payment Status");
		System.out.println("11. Display Non Paid Rental List");
		System.out.println("12. Exit");
		System.out.print("Waiting for your response...");
	}
	
	public void setStrategy()
	{
		boolean choiceConfirmation = false;
		do
		{
		try
		{
			userChoice = Integer.parseInt(getInput());
			choiceConfirmation = true;
		}
		catch(Exception e)
		{
			System.out.println("You have made an Invalid Choice ! Please Enter Valid Choice");
			System.out.print("Waiting for your response...");
			//System.exit(0);
		}
		}while (choiceConfirmation == false);
	}
	
	public String getInput()
	{
		Scanner scanner = new Scanner(System.in);
		return scanner.nextLine();
	}
	
	public void executeStrategy()
	{
		switch (userChoice)
		{
		case 1:
		{
			getPropertySpecificDetails();
			if(propertyType == 1)
				serverResponse = controller.createRentalPropertyController(propertyType, address, city, state, postalCode, rent,apartmentNumber,numberOfBedrooms+"",numberOfBathrooms+"",squareFootage+"");
			else if(propertyType == 2)
				serverResponse = controller.createRentalPropertyController(propertyType, address, city, state, postalCode, rent,streetNumber+"",unitNumber+"");
			else
				serverResponse = controller.createRentalPropertyController(propertyType, address, city, state, postalCode, rent,streetNumber+"");
			
			System.out.println(serverResponse); 
			break;
		}
		case 2:
		{
			getTenantDetails();
			getInterestedPropertyList();
			//serverResponse = controller.addTenantController(name, email, new ArrayList<RentalProperty>(), new ArrayList<Lease>());
			serverResponse = controller.addTenantController(name, email, interestedProperties);
			System.out.println(serverResponse);
			break;
		}	
		case 3:
		{
			getTenantDetails();
			getPropertyIdentificationDetails();
			getLeaseDetails();
			if(propertyType == 1)
				serverResponse = controller.rentAUnitController(name, email, propertyType, city, state, postalCode, startDate, endDate, apartmentNumber);
			else if(propertyType == 2)
				serverResponse = controller.rentAUnitController(name, email, propertyType, city, state, postalCode, startDate, endDate, streetNumber+"",unitNumber+"");
			else
				serverResponse = controller.rentAUnitController(name, email, propertyType, city, state, postalCode, startDate, endDate, streetNumber+"");
			
			System.out.println(serverResponse); 
			break;
		}
		case 4:
		{
			list = controller.getAllPropertiesController();
			displayAllProperties();
			break;
		}
		case 5:
		{
			list = controller.getAllTenantsController();
			displayAllTenants();
			break;
		}
		case 6:
		{
			list = controller.getAllRentedUnitsController();
			displayAllRentedUnits();
			break;
		}	
		case 7:
		{
			list = controller.getAllVacantUnitsController();
			displayAllVacantUnits();
			break;
		}
		case 8:
		{
			list = controller.getAllLeasesController();
			displayAllLeases();
			break;
		}
		case 9:
		{
			list = controller.getCustomerInterestPropertiesVacancyController();
			notifyVacantProperties();
			break;
		}
		case 10:
		{
			getTenantDetails();
			getPropertyIdentificationDetails();
			getpaymentStatus();
			//name = controller.updatePaymentStatusController(name, email, paymentStatus );
			
			if(propertyType == 1)
				serverResponse = controller.updatePaymentStatusController(name, email, paymentStatus, propertyType, city, state, postalCode, apartmentNumber);
			else if(propertyType == 2)
				serverResponse = controller.updatePaymentStatusController(name, email, paymentStatus, propertyType, city, state, postalCode, streetNumber+"",unitNumber+"");
			else
				serverResponse = controller.updatePaymentStatusController(name, email, paymentStatus, propertyType, city, state, postalCode, streetNumber+"");
			
			System.out.println(serverResponse); 
			break;
		}
		case 11:
		{
			list = controller.getNonPaidRentalController();
			displayNonPaidRentals();
			break;
		}
		case 12:
		{
			System.out.println("!Exiting the System.Thanks for using system.");
			break;
		}
		default:
			System.out.println("You Entered and Invalid choice");
			//System.out.println("!Exiting the System.Thanks for using system.");
			break;
			
		}
	}
	
	public void getPropertyCommonDetails()
	{
		boolean choiceConfirmation = true;
		
		do
		{
			try
			{
				System.out.println("Address");
				address = getInput();
				System.out.println("City");
				city = getInput();
				System.out.println("State");
				state = getInput();
				System.out.println("Postal Code");
				postalCode = getInput();
				System.out.println("Rent");
				rent = Double.parseDouble(getInput());
				choiceConfirmation = true;
			}
			catch(Exception e)
			{
				System.out.println("You have Entered and Improper Details ! . Please Enter Correct Details");
				choiceConfirmation = false;
			}
		}while(choiceConfirmation != true);
	}
	
	public void getPropertySpecificDetails()
	{
		boolean choiceConfirmation = false;
		try
		{
			do
			{
			System.out.println("Please Enter Type of Property you want to add");
			System.out.println("1. Apartment");
			System.out.println("2. Condo");
			System.out.println("3. House");
			try
			{
				input = getInput();
				propertyType = Integer.parseInt(input);
				choiceConfirmation = true;
			}
			catch(Exception e)
			{
				System.out.println("! Invalid choice");
				choiceConfirmation = false;
			}
			}while(choiceConfirmation != true);
			
			getPropertyCommonDetails();
			
			switch(Integer.parseInt(input))
			{
			case 1 : 
				System.out.println("Please enter Apartment Number");
				apartmentNumber = getInput();
				System.out.println("Please enter number of BedRooms");
				numberOfBedrooms = Integer.parseInt(getInput());
				System.out.println("Please enter number of BathRooms");
				numberOfBathrooms = Integer.parseInt(getInput());
				System.out.println("Please enter number of Square Footage");
				squareFootage = Integer.parseInt(getInput());
				break;
				
			case 2: 
				
				System.out.println("Please enter Street Number");
				streetNumber = getInput();
				System.out.println("Please enter Unit Number");
				unitNumber = getInput();
				break;
				
			case 3: 
				
				System.out.println("Please enter Street Number");
				streetNumber = getInput();
				break;
			}
			
		}
		catch(Exception e)
		{
			
		}
	}
	
	public void getTenantDetails()
	{
		System.out.println("Please Enter name of Tenant");
		name = getInput();
		System.out.println("Please Enter email ID of Tenant");
		email = getInput();
	}
	
	public void getPropertyIdentificationDetails()
	{
		System.out.println("Please Enter Type of Property to rent");
		System.out.println("1. Apartment");
		System.out.println("2. Condo");
		System.out.println("3. House");
		try
		{
			input = getInput();
			propertyType = Integer.parseInt(input);
		}
		catch(Exception e)
		{
			System.out.println("! Invalid choice");
		}
		
		switch(propertyType)
		{
		case 1:
			System.out.println("City");
			city = getInput();
			System.out.println("State");
			state = getInput();
			System.out.println("Postal Code");
			postalCode = getInput();
			System.out.println("Please Enter Apartment Number");
			apartmentNumber = getInput();
			break;
		case 2: 
			System.out.println("City");
			city = getInput();
			System.out.println("State");
			state = getInput();
			System.out.println("Postal Code");
			postalCode = getInput();
			System.out.println("Please enter Street Number");
			streetNumber = getInput();
			System.out.println("Please enter Unit Number");
			unitNumber = getInput();
			break;
			
		case 3: 
			System.out.println("City");
			city = getInput();
			System.out.println("State");
			state = getInput();
			System.out.println("Postal Code");
			postalCode = getInput();
			System.out.println("Please enter Street Number");
			streetNumber = getInput();
			break;
			
		default :
			System.out.println("You Entered a Invalid Choice");
			break;
		}
	}
	
	public void getLeaseDetails()
	{
		System.out.println("Please Enter lease start date in DD/MM/YYYY format");
		startDate = getInput();
		System.out.println("Please Enter lease end date in DD/MM/YYYY format");
		endDate = getInput();
	}
	
	public void displayAllLeases()
	{
		//System.out.println(Arrays.toString(list.toArray()));
		
		for(String [] s : (ArrayList<String[]>)list)
		{
			System.out.println("Tenant Name : "+s[0]);
			System.out.println("Tenant Email : "+s[1]);
			System.out.println("Property Address : "+s[2]);
			System.out.println("Start Date : "+s[3]);
			System.out.println("End Date : "+s[4]);
			System.out.println("Rent Amount : "+s[5]);
			//System.out.println("Is Rent Paid : "+s[6]);
			//System.out.println("Lease ID: "+s[7]);
			//System.out.println("Lease Type: "+s[8]);
			
		}
	}
	
	public void displayAllProperties()
	{
		
		ArrayList<String[]> apartmentList = (ArrayList<String[]>)list.get(0);
		ArrayList<String[]> condoList = (ArrayList<String[]>)list.get(1);
		ArrayList<String[]> houseList = (ArrayList<String[]>)list.get(2);
		
		System.out.println("***********APARTMENTS*************");
		for(String [] apartment : apartmentList)
		{
				System.out.println("Address : "+apartment[0]);
				System.out.println("City : "+apartment[1]);
				System.out.println("State : "+apartment[2]);
				System.out.println("Postal Code : "+apartment[3]);
				System.out.println("Rent : "+apartment[4]);
				System.out.println("Apartment Number : "+apartment[5]);
				System.out.println("Number of BedRooms : "+apartment[6]);
				System.out.println("Number of BathRooms: "+apartment[7]);
				System.out.println("Square Footage: "+apartment[8]);
		}

		System.out.println("***********CONDOS*************");
		for(String [] condo : condoList)
		{
				System.out.println("Address : "+condo[0]);
				System.out.println("City : "+condo[1]);
				System.out.println("State : "+condo[2]);
				System.out.println("Postal Code : "+condo[3]);
				System.out.println("Rent : "+condo[4]);
				System.out.println("Street Number : "+condo[5]);
				System.out.println("Unit Number : "+condo[6]);

		}
		
		System.out.println("***********HOUSES*************");
		for(String [] houses : houseList)
		{
				System.out.println("Address : "+houses[0]);
				System.out.println("City : "+houses[1]);
				System.out.println("State : "+houses[2]);
				System.out.println("Postal Code : "+houses[3]);
				System.out.println("Rent : "+houses[4]);
				System.out.println("Street Number : "+houses[5]);
	
		}
	}
	
	public void displayAllTenants()
	{		
		for(String [] s : (ArrayList<String[]>)list)
		{
			System.out.println("Tenant Name : "+s[0]);
			System.out.println("Tenant Email : "+s[1]);
		}
	}
	
	public void displayAllRentedUnits()
	{
		displayAllProperties();
	}
	
	public void displayAllVacantUnits()
	{
		displayAllProperties();
	}
	
	public void getInterestedPropertyList()
	{
		interestedProperties = new ArrayList<>();
		int count = 0;
		//ArrayList <String[]> interestedProperties = new ArrayList<String []>();
		
		System.out.println("Please enter number of properties you are Interested in");
		try
		{
			count = Integer.parseInt(getInput());
		}
		catch(Exception e)
		{
			System.out.println("You Entered an Invalid Choice");
		}
		
		for(int i=1;i<=count;i++)
		{
			System.out.println("Please Enter details of property Number : "+i);
			getPropertyIdentificationDetails();
			if(propertyType == 1)
			{
				String[] property = {propertyType+"",city,state,postalCode,apartmentNumber};
				interestedProperties.add(property);
			}
			else if(propertyType == 2)
			{
				String[] property = {propertyType+"",city,state,postalCode,streetNumber,unitNumber};
				interestedProperties.add(property);
			}
			else if(propertyType == 3)
			{
				String[] property = {propertyType+"",city,state,postalCode,streetNumber};
				interestedProperties.add(property);
			}
			
			
		}
	}
	
	public void notifyVacantProperties()
	{
		if(list != null)
		{
		for (String s[] : (ArrayList<String[]>)list)
		{
			if(s[0].equals("1"))
			{
				System.out.println("Dear " + s[1]);
				//System.out.println();
				System.out.println("The property with details : ");
				System.out.println("  Address - "+s[3]);
				System.out.println("  City - "+s[4]);
				System.out.println("  State - "+s[5]);
				System.out.println("  Postal Code : "+s[6]);
				System.out.println("  Apartment Number - "+s[8]);
				System.out.println("  Rent - "+ s[7]);
				//System.out.println();
				System.out.println("is Vacant now.");
				System.out.println("You can connect with your agent for other details");
				System.out.println();
				System.out.println("Thanks and Regards,");
				System.out.println("RentalProperties");
				//System.out.println("Tenant Name : "+s[1]+"  Tenant Email : "+s[2]+"  Address : "+s[3]+"  City : "+s[4]+"  State : "+s[5]+"  PostalCode : "+s[6]+"  Rent : "+s[7]+"  Apartment Number : "+s[8]);
			}
			else if(s[0].equals("2"))
			{
				System.out.println("Dear " + s[1]);
				//System.out.println();
				System.out.println("The property with details : ");
				System.out.println("  Address - "+s[3]);
				System.out.println("  City - "+s[4]);
				System.out.println("  State - "+s[5]);
				System.out.println("  Postal Code : "+s[6]);
				System.out.println("  Street Number - "+s[8]);
				System.out.println("  Unit Number - "+s[9]);
				System.out.println("  Rent - "+ s[7]);
				//System.out.println();
				System.out.println("is Vacant now.");
				System.out.println("You can connect with your agent for other details");
				System.out.println();
				System.out.println("Thanks and Regards,");
				System.out.println("RentalProperties");
				//System.out.println("Tenant Name : "+s[1]+"  Tenant Email : "+s[2]+"  Address : "+s[3]+"  City : "+s[4]+"  State : "+s[5]+"  PostalCode : "+s[6]+"  Rent : "+s[7]+"  Street Number : "+s[8]+"  Unit Number : "+s[9]);
			}
			else if(s[0].equals("3"))
			{
				System.out.println("Dear " + s[1]);
				//System.out.println();
				System.out.println("The property with details : ");
				System.out.println("  Address - "+s[3]);
				System.out.println("  City - "+s[4]);
				System.out.println("  State - "+s[5]);
				System.out.println("  Postal Code : "+s[6]);
				System.out.println("  Street Number - "+s[8]);
				System.out.println("  Rent - "+ s[7]);
				//System.out.println();
				System.out.println("is Vacant now.");
				System.out.println("You can connect with your agent for other details");
				System.out.println();
				System.out.println("Thanks and Regards,");
				System.out.println("RentalProperties");
				//System.out.println("Tenant Name : "+s[1]+"  Tenant Email : "+s[2]+"  Address : "+s[3]+"  City : "+s[4]+"  State : "+s[5]+"  PostalCode : "+s[6]+"  Rent : "+s[7]+"  Street Number : "+s[8]);
			}
					
		}
		}
	}
	
	public void getpaymentStatus()
	{
		
		boolean choiceConfirmation = false;
		do
		{
		try
		{
			System.out.println("Please Enter Payment Status");
			System.out.println("1. Paid");
			System.out.println("2. Unpaid");
			input = getInput();
			if( Integer.parseInt(input) == 1)
			{
				paymentStatus = "Paid";
				choiceConfirmation = true;
			}
			else if(Integer.parseInt(input) == 2)
			{
				paymentStatus = "Unpaid";
				choiceConfirmation = true;
			}
			else
			{
				System.out.println("You have entered invalid choice!. Please Enter valid choice");
			}
		}
		catch(Exception e)
		{
			System.out.println("You have entered invalid choice!. Please Enter valid choice");
		}
		}while(choiceConfirmation == false);
	}
	
	public void displayNonPaidRentals()
	{
		ArrayList<String[]> apartmentList = (ArrayList<String[]>)list.get(0);
		ArrayList<String[]> condoList = (ArrayList<String[]>)list.get(1);
		ArrayList<String[]> houseList = (ArrayList<String[]>)list.get(2);
		
		System.out.println("***********APARTMENTS*************");
		for(String [] apartment : apartmentList)
		{
				System.out.println("Address : "+apartment[0]);
				System.out.println("City : "+apartment[1]);
				System.out.println("State : "+apartment[2]);
				System.out.println("Postal Code : "+apartment[3]);
				System.out.println("Rent : "+apartment[4]);
				System.out.println("Apartment Number : "+apartment[5]);
//				System.out.println("Number of BedRooms : "+apartment[6]);
//				System.out.println("Number of BathRooms: "+apartment[7]);
//				System.out.println("Square Footage: "+apartment[8]);
		}

		System.out.println("***********CONDOS*************");
		for(String [] condo : condoList)
		{
				System.out.println("Address : "+condo[0]);
				System.out.println("City : "+condo[1]);
				System.out.println("State : "+condo[2]);
				System.out.println("Postal Code : "+condo[3]);
				System.out.println("Rent : "+condo[4]);
//				System.out.println("Street Number : "+condo[5]);
//				System.out.println("Unit Number : "+condo[6]);

		}
		
		System.out.println("***********HOUSES*************");
		for(String [] houses : houseList)
		{
				System.out.println("Address : "+houses[0]);
				System.out.println("City : "+houses[1]);
				System.out.println("State : "+houses[2]);
				System.out.println("Postal Code : "+houses[3]);
				System.out.println("Rent : "+houses[4]);
//				System.out.println("Street Number : "+houses[5]);
	
		}
	}
}
