package rentals;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import database.Database;

public class Tenant implements Observer {
    protected String name;
    protected String email;
    protected List<RentalProperty> interests;
    ////private List<RentalProperty> rented;
    protected List<Lease> leases;

    public Tenant()
    {
    	
    }
    
    public Tenant(String name, String email, List<RentalProperty> interests) {
        this.name = name;
        this.email = email;
        this.interests = interests;
        this.leases = new ArrayList<>();
    }
    
    public Tenant(String name, String email) {
        this.name = name;
        this.email = email;
        this.interests = new ArrayList<>();
        this.leases = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void addInterest(RentalProperty property) {
        interests.add(property);
       // property.addObserver(this);
    }

    public void removeInterest(RentalProperty property) {
        interests.remove(property);
        //property.removeObserver(this);
    }

    public void addLease(Lease lease) {
        leases.add(lease);
    }

    public void removeLease(Lease lease) {
        leases.remove(lease);
    }

    public void displayLeases() {
        System.out.println("Leases for " + name + ":");
        for (Lease lease : leases) {
            System.out.println("- " + lease.toString());
        }
    }

    public void displayInterests() {
        System.out.println("Interests for " + name + ":");
        for (RentalProperty property : interests) {
            System.out.println("- " + property.toString());
        }
    }

    @Override
    public void update(Observable o, Object arg) {
       // RentalProperty property = (RentalProperty) o;
        String message = (String) arg;
       // System.out.println("Dear " + name + ",\n\nThe property you were interested in is now available:\n" + property.toString() + "\nPlease contact us if you are still interested.\n\nBest regards,\nThe Real Estate Company");
        System.out.println("Email sent to " + email);
    }
    
    
	public String addTenant(String name, String email, List<String[]> interestedProperties)
	{
    	Tenant tenant = new Tenant(name, email); 
    	RentalProperty property = new RentalProperty();
    	for(Tenant t : (List<Tenant>)Database.mockObject.get(3))
    	{
    		if(t.name.equals(name) && t.email.equals(email))
    		{
    			return "Tenant already present in system";
    		}
    	}
    	for(String [] s : interestedProperties)
    	{
    		if(s[0].equals("1"))
    		{
    			property = RentalProperty.getPropertyDetails(Integer.parseInt(s[0]),s[1],s[2],s[3],s[4]);
    		}
    		else if (s[0].equals("2"))
    		{
    			property = RentalProperty.getPropertyDetails(Integer.parseInt(s[0]),s[1],s[2],s[3],s[4],s[5]);
    		}
    		else
    		{
    			property = RentalProperty.getPropertyDetails(Integer.parseInt(s[0]),s[1],s[2],s[3],s[4]);
    		}
    		
    		if(property != null)
    		{
    			tenant.interests.add(property);
    		}
    	}
    	
    	((List<Tenant>)Database.mockObject.get(3)).add(new Tenant(name,email,tenant.interests));
    	for(Tenant t:(ArrayList<Tenant>)Database.mockObject.get(3))
		{
			if(t.name.equals(name)&&t.email.equals(email))
			{
				for(RentalProperty r : t.interests)
				{
					//System.out.println("Address : "+ r.address);
				}
			}
		}
    	
    	return "Tenant Added Successfully";
    }

    
    public boolean isTenantRegistered(String name, String email)
    {
    	boolean exists = false;
    	Tenant tenant = new Tenant(name, email); 
    	for(Tenant t : (List<Tenant>)Database.mockObject.get(3))
    	{
    		if(t.name.equals(name) && t.email.equals(email))
    		{
    			exists = true;
    		}
    	}
    	
    	return exists;
    }
    
    public void updateTenantRentalList(Lease lease)
    {
    	for(Tenant tnt : ((List<Tenant>)Database.mockObject.get(3)))
		{
			if(tnt.getName().equals(name) && tnt.getEmail().equals(email))
			{
				tnt.leases.add(lease);
			}
		}
    }
    
    public List getAllTenants()
    {
    	ArrayList <Tenant>list = ((ArrayList<Tenant>)Database.mockObject.get(3));
		ArrayList<String[]> output = new ArrayList<>();
		
		for(Tenant tenant : list)
		{
			String [] data = new String[2];
			data[0] = tenant.name;
			data[1] = tenant.email;
		
			output.add(data);
		}
		return output;
    }
}
