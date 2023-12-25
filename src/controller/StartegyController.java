package controller;

import java.util.ArrayList;
import java.util.List;

import rentals.Lease;
import rentals.RentalProperty;
import rentals.Tenant;

public class StartegyController 
{
	RentalProperty rentalProperty;
	Tenant tenant;
	Lease lease;
	
	public StartegyController() 
	{
		// TODO Auto-generated constructor stub
		
		rentalProperty = new RentalProperty() ;
		tenant = new Tenant();
		lease = new Lease();
	}
	
	public String createRentalPropertyController(int propertyType, String address, String city, String state, String postalCode, double rent, String... args)
	{
		return rentalProperty.createRentalProperty(propertyType, address, city, state, postalCode, rent, args);
	}
	
	public String addTenantController(String name, String email, List<String[]> interestedProperties)
	{
		return tenant.addTenant(name, email, interestedProperties);
	}
	
	public String rentAUnitController(String name,String email, int propertyType,String city,String state,String postalCode,String startDate,String endDate, String... args)
	{
		return rentalProperty.rentAUnit(name, email, propertyType, city, state, postalCode, startDate, endDate, args);
	}
	
	public List getAllPropertiesController()
	{
		return rentalProperty.getAllProperties();
	}
	
	public List getAllTenantsController()
	{
		return tenant.getAllTenants();
	}
	
	public List getAllRentedUnitsController()
	{
		return rentalProperty.getAllRentedUnits();
	}
	
	public List getAllVacantUnitsController()
	{
		return rentalProperty.getAllVacantUnits();
	}
	
	public List getAllLeasesController()
	{
		return lease.getAllLeases();
	}
	
	public List getCustomerInterestPropertiesVacancyController()
	{
		return rentalProperty.getCustomerInterestPropertiesVacancy();
	}
	
	public String updatePaymentStatusController(String name, String email, String paymentStatus,  int propertyType, String city, String state, String postalCode, String... args)
	{
		return rentalProperty.updatePaymentStatus(name, email, paymentStatus, propertyType, city, state, postalCode, args);
	}
	
	public List getNonPaidRentalController()
	{
		return rentalProperty.getNonPaidRental();
	}
}
