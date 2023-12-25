package beans;

import java.util.Date;

import rentals.Lease;
import rentals.RentalProperty;
import rentals.Tenant;

//import rentals.Lease.LeaseBuilder;

//Builder pattern
	public class LeaseBuilder 
	{
		// Required fields
		public Tenant tenant;
		public RentalProperty rentalProperty;
		public Date startDate;
		public Date endDate;
		public double rentAmount;

		// Optional fields
		public String leaseType;

		public LeaseBuilder(RentalProperty property)
		{
			this.tenant = new Tenant();
			this.rentalProperty = property;
			this.startDate = new Date();
			this.endDate = new Date();
			this.rentAmount = 0;
		}
		
		public LeaseBuilder(Tenant tenant, RentalProperty rentalProperty, Date startDate, Date endDate, double rentAmount) {
			this.tenant = tenant;
			this.startDate = startDate;
			this.endDate = endDate;
			this.rentAmount = rentAmount;
		}

		public LeaseBuilder leaseType(String leaseType) {
			this.leaseType = leaseType;
			return this;
		}

		public Lease build() {
			return new Lease(this);
		}
	}
