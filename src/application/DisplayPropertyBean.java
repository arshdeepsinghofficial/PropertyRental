package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;

public class DisplayPropertyBean 
{
	public SimpleStringProperty  address;
	public SimpleStringProperty  city;
	public SimpleStringProperty  state;
	public SimpleStringProperty  postalCode;
	public SimpleStringProperty  rent;
	public SimpleStringProperty  apartmentNumber;
	public SimpleStringProperty  numberOfBedrooms;
	public SimpleStringProperty  numberOfBathrooms;
	public SimpleStringProperty  squareFootage;
	public SimpleStringProperty  condoStreetNumber;
	public SimpleStringProperty  unitNumber;
	public SimpleStringProperty  houseStreetNumber;
	
	public String getAddress() {
		return address.get();
	}
	public void setAddress(String address) {
		this.address = new SimpleStringProperty(address);
	}
	public String getCity() {
		return city.get();
	}
	public void setCity(String city) {
		this.city = new SimpleStringProperty(city);
	}
	public String getState() {
		return state.get();
	}
	public void setState(String state) {
		this.state = new SimpleStringProperty(state);
	}
	public String getPostalCode() {
		return postalCode.get();
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = new SimpleStringProperty(postalCode);
	}
	public String getRent() {
		return rent.get();
	}
	public void setRent(String rent) {
		this.rent = new SimpleStringProperty(rent);
	}
	public String getApartmentNumber() {
		return apartmentNumber.get();
	}
	public void setApartmentNumber(String apartmentNumber) {
		this.apartmentNumber = new SimpleStringProperty(apartmentNumber);
	}
	public String getNumberOfBedrooms() {
		return numberOfBedrooms.get();
	}
	public void setNumberOfBedrooms(String numberOfBedrooms) {
		this.numberOfBedrooms = new SimpleStringProperty(numberOfBedrooms);
	}
	public String getNumberOfBathrooms() {
		return numberOfBathrooms.get();
	}
	public void setNumberOfBathrooms(String numberOfBathrooms) {
		this.numberOfBathrooms = new SimpleStringProperty(numberOfBathrooms);
	}
	public String getSquareFootage() {
		return squareFootage.get();
	}
	public void setSquareFootage(String squareFootage) {
		this.squareFootage = new SimpleStringProperty(squareFootage);
	}
	public String getCondoStreetNumber() {
		return condoStreetNumber.get();
	}
	public void setCondoStreetNumber(String condoStreetNumber) {
		this.condoStreetNumber = new SimpleStringProperty(condoStreetNumber);
	}
	public String getUnitNumber() {
		return unitNumber.get();
	}
	public void setUnitNumber(String unitNumber) {
		this.unitNumber = new SimpleStringProperty(unitNumber);
	}
	public String getHouseStreetNumber() {
		return houseStreetNumber.get();
	}
	public void setHouseStreetNumber(String houseStreetNumber) {
		this.houseStreetNumber = new SimpleStringProperty(houseStreetNumber);
	}
	
	
}
