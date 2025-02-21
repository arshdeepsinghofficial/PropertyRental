package application;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.Animation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class AddPropertyController implements Initializable
{
	@FXML RadioButton apartment;
	@FXML RadioButton condo;
	@FXML RadioButton house;
	@FXML TextField address;
	@FXML TextField city;
	@FXML TextField state;
	@FXML TextField postalCode;
	@FXML TextField rent;
	@FXML TextField apartmentNumber;
	@FXML TextField numberOfBedrooms;
	@FXML TextField numberOfBathrooms;
	@FXML TextField squareFootage;
	@FXML TextField condoStreetNumber;
	@FXML TextField unitNumber;
	@FXML TextField houseStreetNumber;
	@FXML Button button_addProperty;
	@FXML Label label_error;
	@FXML Pane pane_apartment;
	@FXML Pane pane_condo;
	@FXML Pane pane_house;
	
	static PropertyFieldsBean propertyFieldBean = new PropertyFieldsBean();
	
	public void getSelectedPropertyType(ActionEvent ae)
	{
		
		if(apartment.isSelected())
		{
			pane_apartment.setVisible(true);
			pane_condo.setVisible(false);
			pane_house.setVisible(false);
			
			button_addProperty.setDisable(false);
			button_addProperty.setLayoutY(320);
			button_addProperty.setVisible(true);
			propertyFieldBean.propertyType = 1;
		}
		else if(condo.isSelected())
		{
			pane_condo.setVisible(true);
			pane_apartment.setVisible(false);
			pane_house.setVisible(false);
			
			pane_condo.setLayoutY(225);
			button_addProperty.setDisable(false);
			button_addProperty.setLayoutY(320);
			button_addProperty.setVisible(true);
			propertyFieldBean.propertyType = 2;
		}
		else if(house.isSelected())
		{
			pane_house.setVisible(true);
			pane_apartment.setVisible(false);
			pane_condo.setVisible(false);
			
			pane_house.setLayoutY(225);
			button_addProperty.setDisable(false);
			button_addProperty.setLayoutY(320);
			button_addProperty.setVisible(true);
			propertyFieldBean.propertyType = 3;
		}
	}
	
	public void addProperty(ActionEvent ae)
	{
		label_error.setVisible(false);
		System.out.println("addPropertyCalled");
		if(propertyFieldBean.propertyType == 0)
		{
			button_addProperty.setDisable(true);
		}
		if(propertyFieldBean.propertyType == 1)
		{
			if(validateData() == 1)
			{
				label_error.setTextFill(Color.color(1, 0, 0));
				label_error.setVisible(true);
				return;
			}
			label_error.setVisible(false);
			//resetStyle();
			propertyFieldBean.address = address.getText();
			propertyFieldBean.city = city.getText();
			propertyFieldBean.state = state.getText();
			propertyFieldBean.postalCode = postalCode.getText();
			propertyFieldBean.rent = Double.parseDouble(rent.getText());
			propertyFieldBean.apartmentNumber =apartmentNumber.getText();
			propertyFieldBean.numberOfBedrooms = numberOfBedrooms.getText();
			propertyFieldBean.numberOfBathrooms = numberOfBathrooms.getText();
			propertyFieldBean.squareFootage = squareFootage.getText();
			
			Thread thread = new Thread(new Runnable()
			{
				public void run()
				{
					propertyFieldBean.serverResponse = propertyFieldBean.controller.createRentalPropertyController(propertyFieldBean.propertyType, propertyFieldBean.address, propertyFieldBean.city, propertyFieldBean.state, propertyFieldBean.postalCode, propertyFieldBean.rent,propertyFieldBean.apartmentNumber,propertyFieldBean.numberOfBedrooms+"",propertyFieldBean.numberOfBathrooms+"",propertyFieldBean.squareFootage+"");
					System.out.println(propertyFieldBean.serverResponse);
					
				}	
			});
			thread.start();
			try
			{
			Thread.sleep(100);
			}
			catch(Exception e)
			{
				
			}
			label_error.setText(propertyFieldBean.serverResponse);
			label_error.setTextFill(Color.color(0, 0, 1));
			label_error.setVisible(true);
			System.out.println(propertyFieldBean.serverResponse);
			
			clearFields();
			
		}
		else if(propertyFieldBean.propertyType == 2)
		{
			if(validateData() == 1)
			{
				label_error.setTextFill(Color.color(1, 0, 0));
				label_error.setVisible(true);
				return;
			}
			label_error.setVisible(false);
			propertyFieldBean.address = address.getText();
			propertyFieldBean.city = city.getText();
			propertyFieldBean.state = state.getText();
			propertyFieldBean.postalCode = postalCode.getText();
			propertyFieldBean.rent = Double.parseDouble(rent.getText());
			propertyFieldBean.condoStreetNumber =condoStreetNumber.getText();
			propertyFieldBean.unitNumber = unitNumber.getText();
			
			Thread thread = new Thread(new Runnable()
			{
				public void run()
				{
					propertyFieldBean.serverResponse = propertyFieldBean.controller.createRentalPropertyController(propertyFieldBean.propertyType, propertyFieldBean.address, propertyFieldBean.city, propertyFieldBean.state, propertyFieldBean.postalCode, propertyFieldBean.rent,propertyFieldBean.condoStreetNumber,propertyFieldBean.unitNumber+"");
					System.out.println(propertyFieldBean.serverResponse);
					
				}	
			});
			thread.start();
			try
			{
			Thread.sleep(100);
			}
			catch(Exception e)
			{
				
			}
			label_error.setText(propertyFieldBean.serverResponse);
			label_error.setTextFill(Color.color(0, 0, 1));
			label_error.setVisible(true);
			
			clearFields();
		}
		else if(propertyFieldBean.propertyType == 3)
		{
			if(validateData() == 1)
			{
				label_error.setTextFill(Color.color(1, 0, 0));
				label_error.setVisible(true);
				return;
			}
			label_error.setVisible(false);
			propertyFieldBean.address = address.getText();
			propertyFieldBean.city = city.getText();
			propertyFieldBean.state = state.getText();
			propertyFieldBean.postalCode = postalCode.getText();
			propertyFieldBean.rent = Double.parseDouble(rent.getText());
			propertyFieldBean.houseStreetNumber =houseStreetNumber.getText();
			
			Thread thread = new Thread(new Runnable()
			{
				public void run()
				{
					propertyFieldBean.serverResponse = propertyFieldBean.controller.createRentalPropertyController(propertyFieldBean.propertyType, propertyFieldBean.address, propertyFieldBean.city, propertyFieldBean.state, propertyFieldBean.postalCode, propertyFieldBean.rent,propertyFieldBean.houseStreetNumber);
					System.out.println(propertyFieldBean.serverResponse);
				}	
			});
			thread.start();
			try
			{
			Thread.sleep(100);
			}
			catch(Exception e)
			{
				
			}
			label_error.setText(propertyFieldBean.serverResponse);
			label_error.setTextFill(Color.color(0, 0, 1));
			label_error.setVisible(true);
			
			clearFields();
		}
		
		
	}
	
//	public int validateData()
//	{
//		if(address.getText().trim().equals(""))
//		{
//			label_error.setText("Please Fill The Address Field");
//			return 1;
//		}
//		if(city.getText().trim().equals(""))
//		{
//			label_error.setText("Please Fill The City Field");
//			return 1;
//		}
//		if(state.getText().trim().equals(""))
//		{
//			label_error.setText("Please Fill The State Field");
//			return 1;
//		}
//		if(postalCode.getText().trim().equals(""))
//		{
//			label_error.setText("Please Fill The PostalCode Field");
//			return 1;
//		}
//		if(rent.getText().trim().equals(""))
//		{
//			label_error.setText("Please Fill The Rent Field");
//			return 1;
//		}
//		
//		if(propertyFieldBean.propertyType == 1)
//		{
//			if(apartmentNumber.getText().trim().equals(""))
//			{
//				label_error.setText("Please Fill The Apartment No. Field");
//				return 1;
//			}
//			if(numberOfBedrooms.getText().trim().equals(""))
//			{
//				label_error.setText("Please Fill The No. Of Bedrooms Field");
//				return 1;
//			}
//			if(numberOfBathrooms.getText().trim().equals(""))
//			{
//				label_error.setText("Please Fill The No. Of Bathrooms Field");
//				return 1;
//			}
//			if(squareFootage.getText().trim().equals(""))
//			{
//				label_error.setText("Please Fill The Square Footage Field");
//				return 1;
//			}
//		}
//		else if(propertyFieldBean.propertyType == 2)
//		{
//			if(condoStreetNumber.getText().trim().equals(""))
//			{
//				label_error.setText("Please Fill The Street Number Field");
//				return 1;
//			}
//			if(unitNumber.getText().trim().equals(""))
//			{
//				label_error.setText("Please Fill The Unit Number Field");
//				return 1;
//			}
//		}
//		else if(propertyFieldBean.propertyType == 3)
//		{
//			if(houseStreetNumber.getText().trim().equals(""))
//			{
//				label_error.setText("Please Fill The Street Number Field");
//				return 1;
//			}
//		}
//		
//		if(rent.getText().trim().matches("[a-z]*") || rent.getText().trim().matches("[A-Z]*"))
//		{
//			label_error.setText("Please Fill The Address Field");
//			rent.setStyle("-fx-border-color : red;-fx-border-width : 2px");
//			return 1;
//		}
//		
//		label_error.setText("");
//		return 0;
//	}
	
	public int validateData()
	{
		if(address.getText().trim().equals(""))
		{
			label_error.setText("Please Fill All The Fields");
			return 1;
		}
		if(city.getText().trim().equals(""))
		{
			label_error.setText("Please Fill All The Fields");
			return 1;
		}
		if(state.getText().trim().equals(""))
		{
			label_error.setText("Please Fill All The Fields");
			return 1;
		}
		if(postalCode.getText().trim().equals(""))
		{
			label_error.setText("Please Fill All The Fields");
			return 1;
		}
		if(rent.getText().trim().equals(""))
		{
			label_error.setText("Please Fill All The Fields");
			return 1;
		}
		
		if(propertyFieldBean.propertyType == 1)
		{
			if(apartmentNumber.getText().trim().equals(""))
			{
				label_error.setText("Please Fill All The Fields");
				return 1;
			}
			if(numberOfBedrooms.getText().trim().equals(""))
			{
				label_error.setText("Please Fill All The Fields");
				return 1;
			}
			if(numberOfBathrooms.getText().trim().equals(""))
			{
				label_error.setText("Please Fill All The Fields");
				return 1;
			}
			if(squareFootage.getText().trim().equals(""))
			{
				label_error.setText("Please Fill All The Fields");
				return 1;
			}
		}
		else if(propertyFieldBean.propertyType == 2)
		{
			if(condoStreetNumber.getText().trim().equals(""))
			{
				label_error.setText("Please Fill All The Fields");
				return 1;
			}
			if(unitNumber.getText().trim().equals(""))
			{
				label_error.setText("Please Fill All The Fields");
				return 1;
			}
		}
		else if(propertyFieldBean.propertyType == 3)
		{
			if(houseStreetNumber.getText().trim().equals(""))
			{
				label_error.setText("Please Fill All The Fields");
				return 1;
			}
		}
		
		if(rent.getText().trim().matches("[a-z]*") || rent.getText().trim().matches("[A-Z]*"))
		{
//			rent.setStyle("-fx-border-color : red;-fx-border-width : 2px");
			label_error.setText("Only Numbers Allowed In Fields : Rent, Apartment Number, Number Of Bedrooms,\n Number Of Bathrooms, Square Footage, Street Number, Unit Number");
			return 1;
		}
		
		if(propertyFieldBean.propertyType == 1)
		{
			if(apartmentNumber.getText().trim().matches("[a-z]*") || apartmentNumber.getText().trim().matches("[A-Z]*"))
			{
				//			rent.setStyle("-fx-border-color : red;-fx-border-width : 2px");
				label_error.setText("Only Numbers Allowed In Fields : Rent, Apartment Number, Number Of Bedrooms, Number Of Bathrooms, Square Footage, Street Number, Unit Number");
				return 1;
			}

			if(numberOfBedrooms.getText().trim().matches("[a-z]*") || numberOfBedrooms.getText().trim().matches("[A-Z]*"))
			{
				//			rent.setStyle("-fx-border-color : red;-fx-border-width : 2px");
				label_error.setText("Only Numbers Allowed In Fields : Rent, Apartment Number, Number Of Bedrooms, Number Of Bathrooms, Square Footage, Street Number, Unit Number");
				return 1;
			}

			if(numberOfBathrooms.getText().trim().matches("[a-z]*") || numberOfBathrooms.getText().trim().matches("[A-Z]*"))
			{
				//			rent.setStyle("-fx-border-color : red;-fx-border-width : 2px");
				label_error.setText("Only Numbers Allowed In Fields : Rent, Apartment Number, Number Of Bedrooms, Number Of Bathrooms, Square Footage, Street Number, Unit Number");
				return 1;
			}

			if(squareFootage.getText().trim().matches("[a-z]*") || squareFootage.getText().trim().matches("[A-Z]*"))
			{
				//			rent.setStyle("-fx-border-color : red;-fx-border-width : 2px");
				label_error.setText("Only Numbers Allowed In Fields : Rent, Apartment Number, Number Of Bedrooms, Number Of Bathrooms, Square Footage, Street Number, Unit Number");
				return 1;
			}
		}
		else if(propertyFieldBean.propertyType == 2)
		{
			if(condoStreetNumber.getText().trim().matches("[a-z]*") || condoStreetNumber.getText().trim().matches("[A-Z]*"))
			{
				//			rent.setStyle("-fx-border-color : red;-fx-border-width : 2px");
				label_error.setText("Only Numbers Allowed In Fields : Rent, Apartment Number, Number Of Bedrooms, Number Of Bathrooms, Square Footage, Street Number, Unit Number");
				return 1;
			}

			if(unitNumber.getText().trim().matches("[a-z]*") || unitNumber.getText().trim().matches("[A-Z]*"))
			{
				//			rent.setStyle("-fx-border-color : red;-fx-border-width : 2px");
				label_error.setText("Only Numbers Allowed In Fields : Rent, Apartment Number, Number Of Bedrooms, Number Of Bathrooms, Square Footage, Street Number, Unit Number");
				return 1;
			}

		}
		else if(propertyFieldBean.propertyType == 3)
		{
			if(houseStreetNumber.getText().trim().matches("[a-z]*") || houseStreetNumber.getText().trim().matches("[A-Z]*"))
			{
				//			rent.setStyle("-fx-border-color : red;-fx-border-width : 2px");
				label_error.setText("Only Numbers Allowed In Fields : Rent, Apartment Number, Number Of Bedrooms, Number Of Bathrooms, Square Footage, Street Number, Unit Number");
				return 1;
			}
		}
		
		label_error.setText("");
		return 0;
	}
	public void clearFields()
	{
		address.clear();
		city.clear();
		state.clear();
		postalCode.clear();
		rent.clear();
		apartmentNumber.clear();
		numberOfBathrooms.clear();
		numberOfBedrooms.clear();
		squareFootage.clear();
		condoStreetNumber.clear();
		unitNumber.clear();
		houseStreetNumber.clear();
	}
	
	public void resetStyle()
	{
		address.setStyle(null);
		city.setStyle(null);
		state.setStyle(null);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		// TODO Auto-generated method stub
		pane_apartment.setVisible(false);
		pane_condo.setVisible(false);
		pane_house.setVisible(false);
		button_addProperty.setVisible(false);
	}
}
