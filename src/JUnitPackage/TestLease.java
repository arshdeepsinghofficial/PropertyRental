package JUnitPackage;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import beans.LeaseBuilder;
import database.Database;
import rentals.Lease;
import rentals.RentalProperty;
import rentals.Tenant;

public class TestLease 
{
	Lease lease = new Lease();
	@Before
	public void addLease()
	{
		Tenant tenant1 = new Tenant("abc","abc@gmail.com");
		Tenant tenant2 = new Tenant("def","abc@gmail.com");
		RentalProperty rentalProperty  =  new RentalProperty();
		rentalProperty.address = "address1";
		rentalProperty.city = "city";
		LeaseBuilder leaseBuilder1 = null;
		LeaseBuilder leaseBuilder2 = null;

		try
		{
			leaseBuilder1 = new LeaseBuilder(tenant1, rentalProperty,new SimpleDateFormat("dd/MM/yyyy").parse( "01/04/2023"), new SimpleDateFormat("dd/MM/yyyy").parse("01/05/2023"), 100);
			leaseBuilder2 = new LeaseBuilder(tenant2, rentalProperty,new SimpleDateFormat("dd/MM/yyyy").parse( "01/04/2023"), new SimpleDateFormat("dd/MM/yyyy").parse("01/05/2023"), 100);

		}
		catch(Exception e)
		{
			
		}
		lease.updateLeaseList(new Lease(leaseBuilder1));
		lease.updateLeaseList(new Lease(leaseBuilder2));
	}
	
	@Test
	public void TestGetAllLeases()
	{
		assertEquals(2,((ArrayList<Lease>)Database.mockObject.get(4)).size());
	}
}
