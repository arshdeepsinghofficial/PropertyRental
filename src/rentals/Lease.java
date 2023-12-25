package rentals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.UUID;

import beans.LeaseBuilder;
import database.Database;

// Lease class using Builder and Observer design patterns
public class Lease extends Observable {
	// Fields
	private Tenant tenant;
	private RentalProperty rentalProperty;
	private Date startDate;
	private Date endDate;
	private double rentAmount;
	private boolean isPaid;
	private String leaseId;
	private String leaseType;

	// Observers
	//private final List<Observer> observers;

	public Lease()
	{
		
	}
	
	// Constructor
	public Lease(LeaseBuilder builder) {
		this.tenant = builder.tenant;
		this.rentalProperty = builder.rentalProperty;
		this.startDate = builder.startDate;
		this.endDate = builder.endDate;
		this.rentAmount = builder.rentAmount;
		this.isPaid = false;
		this.leaseId = UUID.randomUUID().toString();

	}

	// Getters
	public Tenant getTenant() {
		return tenant;
	}


	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public double getRentAmount() {
		return rentAmount;
	}

	public boolean isPaid() {
		return isPaid;
	}

	public String getLeaseId() {
		return leaseId;
	}

	public String getLeaseType() {
		return leaseType;
	}

	// Setter
	public void setPaid(boolean isPaid) {
		this.isPaid = isPaid;
		notifyObservers();
	}

	// Methods
	public boolean isExpired() {
		Date currentDate = new Date();
		return currentDate.after(endDate);
	}

	
	public List getAllLeases()
	{
		
		ArrayList <Lease>list = ((ArrayList<Lease>)Database.mockObject.get(4));
		ArrayList<String[]> output = new ArrayList<>();
		
		for(Lease lease : list)
		{
			String [] data = new String[9];
			data[0] = lease.tenant.name;
			data[1] = lease.tenant.email;
			data[2] = lease.rentalProperty.address;
			data[3] = lease.startDate+"";
			data[4] = lease.endDate+"";
			data[5] = lease.rentAmount+"";
			data[6] = lease.isPaid+"";
			data[7] = lease.leaseId+"";
			data[8] = lease.leaseType+"";
			
			output.add(data);
					
		}
		return output;
	}
	
	public void updateLeaseList(Lease lease)
	{
		((List<Lease>)Database.mockObject.get(4)).add(lease);
	}

	
}



