package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class DisplayVacantPropertiesController implements Initializable
{
	@FXML public TableView<DisplayPropertyBean> apartmentsTable;
	@FXML public TableView<DisplayPropertyBean> condosTable;
	@FXML public TableView<DisplayPropertyBean> housesTable;
	@FXML public TableColumn<DisplayPropertyBean, String> apartment_address;
	@FXML public TableColumn<DisplayPropertyBean, String> apartment_city;
	@FXML public TableColumn<DisplayPropertyBean, String> apartment_state;
	@FXML public TableColumn<DisplayPropertyBean, String> apartment_postalCode;
	@FXML public TableColumn<DisplayPropertyBean, String> apartment_rent;
	@FXML public TableColumn<DisplayPropertyBean, String> condo_address;
	@FXML public TableColumn<DisplayPropertyBean, String> condo_city;
	@FXML public TableColumn<DisplayPropertyBean, String> condo_state;
	@FXML public TableColumn<DisplayPropertyBean, String> condo_postalCode;
	@FXML public TableColumn<DisplayPropertyBean, String> condo_rent;
	@FXML public TableColumn<DisplayPropertyBean, String> house_address;
	@FXML public TableColumn<DisplayPropertyBean, String> house_city;
	@FXML public TableColumn<DisplayPropertyBean, String> house_state;
	@FXML public TableColumn<DisplayPropertyBean, String> house_postalCode;
	@FXML public TableColumn<DisplayPropertyBean, String> house_rent;
	@FXML public TableColumn<DisplayPropertyBean, String>  apartmentNumber;
	@FXML public TableColumn<DisplayPropertyBean, String>  numberOfBedrooms;
	@FXML public TableColumn<DisplayPropertyBean, String>  numberOfBathrooms;
	@FXML public TableColumn<DisplayPropertyBean, String>  squareFootage;
	@FXML public TableColumn<DisplayPropertyBean, String>  condoStreetNumber;
	@FXML public TableColumn<DisplayPropertyBean, String>  unitNumber;
	@FXML public TableColumn<DisplayPropertyBean, String>  houseStreetNumber;	
	
	PropertyFieldsBean propertyFieldBean = new PropertyFieldsBean();
	
	
	public void refreshList()
	{

		Thread thread = new Thread(new Runnable()
		{
			public void run()
			{
				propertyFieldBean.list =  propertyFieldBean.controller.getAllVacantUnitsController();
				printTable();
			}	
		});
		thread.start();
		//		propertyFieldBean.list =  propertyFieldBean.controller.getAllVacantUnitsController();
		//		printTable();
	}
	
	public void printTable()
	{
		ArrayList<String[]> apartmentList = (ArrayList<String[]>)propertyFieldBean.list.get(0);
		ArrayList<String[]> condoList = (ArrayList<String[]>)propertyFieldBean.list.get(1);
		ArrayList<String[]> houseList = (ArrayList<String[]>)propertyFieldBean.list.get(2);

		ObservableList<DisplayPropertyBean> apartmentTableList = FXCollections.observableArrayList();
		ObservableList<DisplayPropertyBean> condoTableList = FXCollections.observableArrayList();
		ObservableList<DisplayPropertyBean> houseTableList = FXCollections.observableArrayList();
		if(apartmentList.size() != 0)
		{
			for(String s[] : apartmentList)
			{System.out.println("Entered Apartment Size : " + apartmentList.size());
				DisplayPropertyBean displayPropertyBean = new DisplayPropertyBean ();
				displayPropertyBean.setAddress(s[0]);
				displayPropertyBean.setCity(s[1]);
				displayPropertyBean.setState(s[2]);
				displayPropertyBean.setPostalCode(s[3]);
				displayPropertyBean.setRent(s[4]);
				displayPropertyBean.setApartmentNumber(s[5]);
				displayPropertyBean.setNumberOfBedrooms(s[6]);
				displayPropertyBean.setNumberOfBathrooms(s[7]);
				displayPropertyBean.setSquareFootage(s[8]);

				apartmentTableList.add(displayPropertyBean);

			}

			apartmentsTable.setItems(apartmentTableList);

			apartment_address.setCellValueFactory(new PropertyValueFactory<DisplayPropertyBean,String>("address"));
			apartment_city.setCellValueFactory(new PropertyValueFactory<DisplayPropertyBean,String>("city"));
			apartment_state.setCellValueFactory(new PropertyValueFactory<DisplayPropertyBean,String>("state"));
			apartment_postalCode.setCellValueFactory(new PropertyValueFactory<DisplayPropertyBean,String>("postalCode"));
			apartment_rent.setCellValueFactory(new PropertyValueFactory<DisplayPropertyBean,String>("rent"));
			apartmentNumber.setCellValueFactory(new PropertyValueFactory<DisplayPropertyBean,String>("apartmentNumber"));
			numberOfBedrooms.setCellValueFactory(new PropertyValueFactory<DisplayPropertyBean,String>("numberOfBedrooms"));
			numberOfBathrooms.setCellValueFactory(new PropertyValueFactory<DisplayPropertyBean,String>("numberOfBathrooms"));
			squareFootage.setCellValueFactory(new PropertyValueFactory<DisplayPropertyBean,String>("squareFootage"));
		}

		if(condoList.size() != 0)
		{System.out.println("Entered Condo Size : " + condoList.size());
			for(String s[] : condoList)
			{
				DisplayPropertyBean displayPropertyBean = new DisplayPropertyBean ();
				displayPropertyBean.setAddress(s[0]);
				displayPropertyBean.setCity(s[1]);
				displayPropertyBean.setState(s[2]);
				displayPropertyBean.setPostalCode(s[3]);
				displayPropertyBean.setRent(s[4]);
				displayPropertyBean.setCondoStreetNumber(s[5]);
				displayPropertyBean.setUnitNumber(s[6]);

				condoTableList.add(displayPropertyBean);
			}

			condosTable.setItems(condoTableList);

			condo_address.setCellValueFactory(new PropertyValueFactory<DisplayPropertyBean,String>("address"));
			condo_city.setCellValueFactory(new PropertyValueFactory<DisplayPropertyBean,String>("city"));
			condo_state.setCellValueFactory(new PropertyValueFactory<DisplayPropertyBean,String>("state"));
			condo_postalCode.setCellValueFactory(new PropertyValueFactory<DisplayPropertyBean,String>("postalCode"));
			condo_rent.setCellValueFactory(new PropertyValueFactory<DisplayPropertyBean,String>("rent"));
			condoStreetNumber.setCellValueFactory(new PropertyValueFactory<DisplayPropertyBean,String>("condoStreetNumber"));
			unitNumber.setCellValueFactory(new PropertyValueFactory<DisplayPropertyBean,String>("unitNumber"));
		}

		if(houseList.size() != 0)
		{System.out.println("Entered house Size : " + houseList.size());
			for(String s[] : houseList)
			{System.out.println("Entered Loop");
				DisplayPropertyBean displayPropertyBean = new DisplayPropertyBean ();
				displayPropertyBean.setAddress(s[0]);
				displayPropertyBean.setCity(s[1]);
				displayPropertyBean.setState(s[2]);
				displayPropertyBean.setPostalCode(s[3]);
				displayPropertyBean.setRent(s[4]);
				displayPropertyBean.setHouseStreetNumber(s[5]);

				houseTableList.add(displayPropertyBean);
				System.out.println("List size : "+ houseTableList.size());
				System.out.println(displayPropertyBean.address);
				System.out.println(displayPropertyBean.city);
				System.out.println(displayPropertyBean.state);
				System.out.println(displayPropertyBean.postalCode);
				System.out.println(displayPropertyBean.rent);
			}
			housesTable.setItems(houseTableList);

			house_address.setCellValueFactory(new PropertyValueFactory<DisplayPropertyBean,String>("address"));
			house_city.setCellValueFactory(new PropertyValueFactory<DisplayPropertyBean,String>("city"));
			house_state.setCellValueFactory(new PropertyValueFactory<DisplayPropertyBean,String>("state"));
			house_postalCode.setCellValueFactory(new PropertyValueFactory<DisplayPropertyBean,String>("postalCode"));
			house_rent.setCellValueFactory(new PropertyValueFactory<DisplayPropertyBean,String>("rent"));
			houseStreetNumber.setCellValueFactory(new PropertyValueFactory<DisplayPropertyBean,String>("houseStreetNumber"));
		}
	}

	
	
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		
//		ObservableList<DisplayPropertyBean> taList = FXCollections.observableArrayList(
//				
//				);

		
		
	}
}
