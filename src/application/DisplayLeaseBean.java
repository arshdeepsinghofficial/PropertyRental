package application;

import javafx.beans.property.SimpleStringProperty;

public class DisplayLeaseBean 
{
	public SimpleStringProperty name;
	public SimpleStringProperty email;
	public SimpleStringProperty address;
	public SimpleStringProperty startDate;
	public SimpleStringProperty endDate;
	public SimpleStringProperty rent;
	
	public String getName() {
		return name.get();
	}
	public void setName(String name) {
		this.name = new SimpleStringProperty(name);
	}
	public String getEmail() {
		return email.get();
	}
	public void setEmail(String email) {
		this.email = new SimpleStringProperty(email);
	}
	public String getAddress() {
		return address.get();
	}
	public void setAddress(String address) {
		this.address = new SimpleStringProperty(address);
	}
	public String getStartDate() {
		return startDate.get();
	}
	public void setStartDate(String startDate) {
		this.startDate = new SimpleStringProperty(startDate);
	}
	public String getEndDate() {
		return endDate.get();
	}
	public void setEndDate(String endDate) {
		this.endDate = new SimpleStringProperty(endDate);
	}
	public String getRent() {
		return rent.get();
	}
	public void setRent(String rent) {
		this.rent = new SimpleStringProperty(rent);
	}
	
	
}
