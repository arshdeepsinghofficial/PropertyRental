package application;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class NotifyVacantPropertiesController 
{
	@FXML public TableView<DisplayNotifyBean> apartmentsTable;
	@FXML public TableView<DisplayNotifyBean> condosTable;
	@FXML public TableView<DisplayNotifyBean> housesTable;
	@FXML public TableColumn<DisplayNotifyBean, String> apartment_name;
	@FXML public TableColumn<DisplayNotifyBean, String> apartment_email;
	@FXML public TableColumn<DisplayNotifyBean, String> condo_name;
	@FXML public TableColumn<DisplayNotifyBean, String> condo_email;
	@FXML public TableColumn<DisplayNotifyBean, String> house_name;
	@FXML public TableColumn<DisplayNotifyBean, String> house_email;
	@FXML public TableColumn<DisplayNotifyBean, String> apartment_address;
	@FXML public TableColumn<DisplayNotifyBean, String> apartment_city;
	@FXML public TableColumn<DisplayNotifyBean, String> apartment_state;
	@FXML public TableColumn<DisplayNotifyBean, String> apartment_postalCode;
	@FXML public TableColumn<DisplayNotifyBean, String> apartment_rent;
	@FXML public TableColumn<DisplayNotifyBean, String> condo_address;
	@FXML public TableColumn<DisplayNotifyBean, String> condo_city;
	@FXML public TableColumn<DisplayNotifyBean, String> condo_state;
	@FXML public TableColumn<DisplayNotifyBean, String> condo_postalCode;
	@FXML public TableColumn<DisplayNotifyBean, String> condo_rent;
	@FXML public TableColumn<DisplayNotifyBean, String> house_address;
	@FXML public TableColumn<DisplayNotifyBean, String> house_city;
	@FXML public TableColumn<DisplayNotifyBean, String> house_state;
	@FXML public TableColumn<DisplayNotifyBean, String> house_postalCode;
	@FXML public TableColumn<DisplayNotifyBean, String> house_rent;
	@FXML public TableColumn<DisplayNotifyBean, String>  apartmentNumber;
	@FXML public TableColumn<DisplayNotifyBean, String>  numberOfBedrooms;
	@FXML public TableColumn<DisplayNotifyBean, String>  numberOfBathrooms;
	@FXML public TableColumn<DisplayNotifyBean, String>  squareFootage;
	@FXML public TableColumn<DisplayNotifyBean, String>  condoStreetNumber;
	@FXML public TableColumn<DisplayNotifyBean, String>  unitNumber;
	@FXML public TableColumn<DisplayNotifyBean, String>  houseStreetNumber;	
	
	PropertyFieldsBean propertyFieldBean = new PropertyFieldsBean();
	
	
	public void refreshList()
	{
		Thread thread = new Thread(new Runnable()
		{
			public void run()
			{
				propertyFieldBean.list =  propertyFieldBean.controller.getCustomerInterestPropertiesVacancyController();
				printTable();
			}	
		});
		thread.start();
//		propertyFieldBean.list =  propertyFieldBean.controller.getCustomerInterestPropertiesVacancyController();
//		printTable();
	}
	
	public void printTable()
	{
		ObservableList<DisplayNotifyBean> apartmentTableList = FXCollections.observableArrayList();
		ObservableList<DisplayNotifyBean> condoTableList = FXCollections.observableArrayList();
		ObservableList<DisplayNotifyBean> houseTableList = FXCollections.observableArrayList();

		if(propertyFieldBean.list.size() != 0)
		{
			for(String s[] : (ArrayList<String[]>)propertyFieldBean.list)
			{System.out.println("Entered Apartment Size : " + propertyFieldBean.list.size());
				if(s[0].equals("1"))
				{
					DisplayNotifyBean DisplayNotifyBean = new DisplayNotifyBean ();
					DisplayNotifyBean.setName(s[1]);
					DisplayNotifyBean.setEmail(s[2]);
					DisplayNotifyBean.setAddress(s[3]);
					DisplayNotifyBean.setCity(s[4]);
					DisplayNotifyBean.setState(s[5]);
					DisplayNotifyBean.setPostalCode(s[6]);
					DisplayNotifyBean.setRent(s[7]);
					DisplayNotifyBean.setApartmentNumber(s[8]);
					//DisplayNotifyBean.setNumberOfBedrooms(s[6]);
					//DisplayNotifyBean.setNumberOfBathrooms(s[7]);
					DisplayNotifyBean.setSquareFootage(s[8]);

					apartmentTableList.add(DisplayNotifyBean);

				}

				apartmentsTable.setItems(apartmentTableList);

			}

			apartment_name.setCellValueFactory(new PropertyValueFactory<DisplayNotifyBean,String>("name"));
			apartment_email.setCellValueFactory(new PropertyValueFactory<DisplayNotifyBean,String>("email"));
			apartment_address.setCellValueFactory(new PropertyValueFactory<DisplayNotifyBean,String>("address"));
			apartment_city.setCellValueFactory(new PropertyValueFactory<DisplayNotifyBean,String>("city"));
			apartment_state.setCellValueFactory(new PropertyValueFactory<DisplayNotifyBean,String>("state"));
			apartment_postalCode.setCellValueFactory(new PropertyValueFactory<DisplayNotifyBean,String>("postalCode"));
			apartment_rent.setCellValueFactory(new PropertyValueFactory<DisplayNotifyBean,String>("rent"));
			apartmentNumber.setCellValueFactory(new PropertyValueFactory<DisplayNotifyBean,String>("apartmentNumber"));
//			numberOfBedrooms.setCellValueFactory(new PropertyValueFactory<DisplayNotifyBean,String>("numberOfBedrooms"));
//			numberOfBathrooms.setCellValueFactory(new PropertyValueFactory<DisplayNotifyBean,String>("numberOfBathrooms"));
//			squareFootage.setCellValueFactory(new PropertyValueFactory<DisplayNotifyBean,String>("squareFootage"));
		}

		if(propertyFieldBean.list.size() != 0)
		{
			for(String s[] : (ArrayList<String[]>)propertyFieldBean.list)
			{//System.out.println("Entered Apartment Size : " + apartmentList.size());
				if(s[0].equals("2"))
				{
					DisplayNotifyBean DisplayNotifyBean = new DisplayNotifyBean ();
					DisplayNotifyBean.setName(s[1]);
					DisplayNotifyBean.setEmail(s[2]);
					DisplayNotifyBean.setAddress(s[3]);
					DisplayNotifyBean.setCity(s[4]);
					DisplayNotifyBean.setState(s[5]);
					DisplayNotifyBean.setPostalCode(s[6]);
					DisplayNotifyBean.setRent(s[7]);
					DisplayNotifyBean.setCondoStreetNumber(s[8]);
					DisplayNotifyBean.setUnitNumber(s[9]);

					condoTableList.add(DisplayNotifyBean);
				}

				condosTable.setItems(condoTableList);
			}

			condo_name.setCellValueFactory(new PropertyValueFactory<DisplayNotifyBean,String>("name"));
			condo_email.setCellValueFactory(new PropertyValueFactory<DisplayNotifyBean,String>("email"));
			condo_address.setCellValueFactory(new PropertyValueFactory<DisplayNotifyBean,String>("address"));
			condo_city.setCellValueFactory(new PropertyValueFactory<DisplayNotifyBean,String>("city"));
			condo_state.setCellValueFactory(new PropertyValueFactory<DisplayNotifyBean,String>("state"));
			condo_postalCode.setCellValueFactory(new PropertyValueFactory<DisplayNotifyBean,String>("postalCode"));
			condo_rent.setCellValueFactory(new PropertyValueFactory<DisplayNotifyBean,String>("rent"));
			condoStreetNumber.setCellValueFactory(new PropertyValueFactory<DisplayNotifyBean,String>("condoStreetNumber"));
			unitNumber.setCellValueFactory(new PropertyValueFactory<DisplayNotifyBean,String>("unitNumber"));
		}


		if(propertyFieldBean.list.size() != 0)
		{
			for(String s[] : (ArrayList<String[]>)propertyFieldBean.list)
			{//System.out.println("Entered Apartment Size : " + apartmentList.size());
				if(s[0].equals("3"))
				{
					DisplayNotifyBean DisplayNotifyBean = new DisplayNotifyBean ();
					DisplayNotifyBean.setName(s[1]);
					DisplayNotifyBean.setEmail(s[2]);
					DisplayNotifyBean.setAddress(s[3]);
					DisplayNotifyBean.setCity(s[4]);
					DisplayNotifyBean.setState(s[5]);
					DisplayNotifyBean.setPostalCode(s[6]);
					DisplayNotifyBean.setRent(s[7]);
					DisplayNotifyBean.setHouseStreetNumber(s[8]);

					houseTableList.add(DisplayNotifyBean);
					System.out.println("List size : "+ houseTableList.size());
					System.out.println(DisplayNotifyBean.address);
					System.out.println(DisplayNotifyBean.city);
					System.out.println(DisplayNotifyBean.state);
					System.out.println(DisplayNotifyBean.postalCode);
					System.out.println(DisplayNotifyBean.rent);
				}
				housesTable.setItems(houseTableList);
			}
			
			house_name.setCellValueFactory(new PropertyValueFactory<DisplayNotifyBean,String>("name"));
			house_email.setCellValueFactory(new PropertyValueFactory<DisplayNotifyBean,String>("email"));
			house_address.setCellValueFactory(new PropertyValueFactory<DisplayNotifyBean,String>("address"));
			house_city.setCellValueFactory(new PropertyValueFactory<DisplayNotifyBean,String>("city"));
			house_state.setCellValueFactory(new PropertyValueFactory<DisplayNotifyBean,String>("state"));
			house_postalCode.setCellValueFactory(new PropertyValueFactory<DisplayNotifyBean,String>("postalCode"));
			house_rent.setCellValueFactory(new PropertyValueFactory<DisplayNotifyBean,String>("rent"));
			houseStreetNumber.setCellValueFactory(new PropertyValueFactory<DisplayNotifyBean,String>("houseStreetNumber"));
		}
	}

	
	
	
}
