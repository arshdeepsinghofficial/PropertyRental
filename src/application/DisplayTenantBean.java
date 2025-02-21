package application;

import javafx.beans.property.SimpleStringProperty;

public class DisplayTenantBean 
{
	public SimpleStringProperty name;
	public SimpleStringProperty email;
	
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
	
	
}
