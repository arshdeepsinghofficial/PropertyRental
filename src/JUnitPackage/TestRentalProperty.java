package JUnitPackage;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.*;

import database.Database;
import rentals.*;

public class TestRentalProperty 
{
	@BeforeClass
	public static void addProperty()
	{
		RentalProperty rentalProperty = new RentalProperty();
		rentalProperty.createRentalProperty(1,"address1","city","state","postalcode",100,"1234","4","2","500");
		rentalProperty.createRentalProperty(2,"address2","city","state","postalcode",100,"2345","12");
		rentalProperty.createRentalProperty(3,"address3","city","state","postalcode",100,"2346");
		addTenant();
	}
	
	@BeforeClass
	public static void rentAUnit()
	{
		Tenant tenat = new Tenant();
		ArrayList <String[]> interestedProperties = new ArrayList<>();
		String s[]= {"1","city","state","postalCode","1234"};
		interestedProperties.add(s);
		System.out.println(tenat.addTenant("abc", "abc@gmail.com", interestedProperties));
		RentalProperty rentalProperty = new RentalProperty();
		System.out.println(((ArrayList<RentalProperty>)Database.mockObject.get(0)).size());
		System.out.println(rentalProperty.rentAUnit("abc", "abc@gmail.com", 1, "city", "state", "postalcode", "04/10/2023", "04/10/2023", "1234"));
	}
	
	
	public static void addTenant()
	{
		Tenant tenat = new Tenant();
		ArrayList <String[]> interestedProperties = new ArrayList<>();
		String s[]= {"2","city","state","postalcode","2345","12"};
		String s1[]= {"3","city","state","postalcode","2346"};
		interestedProperties.add(s);
		interestedProperties.add(s1);
		System.out.println("Interests added :"+interestedProperties.size() );
		System.out.println(tenat.addTenant("abc", "abc@gmail.com", interestedProperties));
	}
	
	
	@Test
	public void testCreateRentalProperty()
	{
		assertEquals(1, ((ArrayList<RentalProperty>)Database.mockObject.get(0)).size());	
	}
	
	@Test
	public void testRentAUnit()
	{
		//assertEquals("Vacant",((ArrayList<RentalProperty>)Database.mockObject.get(0)).get(0).rentalStatus);
		assertEquals("Rented",((ArrayList<RentalProperty>)Database.mockObject.get(0)).get(0).rentalStatus);
	}
	
	@Test
	public void testDisplayProperties()
	{
		int size = ((ArrayList<RentalProperty>)Database.mockObject.get(0)).size() + ((ArrayList<RentalProperty>)Database.mockObject.get(1)).size() + ((ArrayList<RentalProperty>)Database.mockObject.get(2)).size();
		assertEquals(3, size);
	}
	
	@Test
	public void testDisplayRentedUnits()
	{
		RentalProperty rentalProperty = new RentalProperty();
		List list = rentalProperty.getAllRentedUnits();

		ArrayList<String[]> apartmentList = (ArrayList<String[]>)list.get(0);
		ArrayList<String[]> condoList = (ArrayList<String[]>)list.get(1);
		ArrayList<String[]> houseList = (ArrayList<String[]>)list.get(2);
		
		int size = apartmentList.size() + condoList.size() + houseList.size();
		assertEquals(1, size);
	}
	
	@Test
	public void testDisplayVacantUnits()
	{

		RentalProperty rentalProperty = new RentalProperty();
		List list = rentalProperty.getAllVacantUnits();

		ArrayList<String[]> apartmentList = (ArrayList<String[]>)list.get(0);
		ArrayList<String[]> condoList = (ArrayList<String[]>)list.get(1);
		ArrayList<String[]> houseList = (ArrayList<String[]>)list.get(2);
		
		int size = apartmentList.size() + condoList.size() + houseList.size();
		assertEquals(2, size);
	}
	
	@Test
	public void testGetCustomerInterestPropertiesVacancy()
	{
		RentalProperty rentalProperty = new RentalProperty();
		assertEquals(2, ((ArrayList<String[]>)rentalProperty.getCustomerInterestPropertiesVacancy()).size());
	}
	
	@Test
	public void testUpdatePaymentStatus()
	{
		assertEquals("Unpaid", ((ArrayList<RentalProperty>)Database.mockObject.get(0)).get(0).paymentStatus);
		RentalProperty rentalProperty = new RentalProperty();
		System.out.println(rentalProperty.updatePaymentStatus("abc", "abc@gmail.com", "Paid", 1, "city", "state", "postalcode", "1234"));
		assertEquals("Paid", ((ArrayList<RentalProperty>)Database.mockObject.get(0)).get(0).paymentStatus);

	}
	
	@Test
	public void getNonPaidRental()
	{
		RentalProperty rentalProperty = new RentalProperty();
		List list = rentalProperty.getNonPaidRental();
		ArrayList<String[]> apartmentList = (ArrayList<String[]>)list.get(0);
		ArrayList<String[]> condoList = (ArrayList<String[]>)list.get(1);
		ArrayList<String[]> houseList = (ArrayList<String[]>)list.get(2);
		
		assertEquals(1, (apartmentList.size()+condoList.size()+houseList.size()));
	}
}
