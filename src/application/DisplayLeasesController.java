package application;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class DisplayLeasesController 
{
	@FXML public TableView<DisplayLeaseBean> leaseTable;
	@FXML public TableColumn<DisplayLeaseBean, String> name;
	@FXML public TableColumn<DisplayLeaseBean, String> email;
	@FXML public TableColumn<DisplayLeaseBean, String> address;
	@FXML public TableColumn<DisplayLeaseBean, String> startDate;
	@FXML public TableColumn<DisplayLeaseBean, String> endDate;
	@FXML public TableColumn<DisplayLeaseBean, String> rent;
	
	PropertyFieldsBean propertyFieldBean = new PropertyFieldsBean();
	
	
	public void refreshList()
	{
		Thread thread = new Thread(new Runnable()
		{
			public void run()
			{
				propertyFieldBean.list =  propertyFieldBean.controller.getAllLeasesController();
				printTable();
			}	
		});
		thread.start();
//		propertyFieldBean.list =  propertyFieldBean.controller.getAllLeasesController();
//		printTable();
	}
	
	public void printTable()
	{
		ObservableList<DisplayLeaseBean> leaseTableList = FXCollections.observableArrayList();
		if(propertyFieldBean.list.size() != 0)
		{
			for(String [] s : (ArrayList<String[]>)propertyFieldBean.list)
			{
				DisplayLeaseBean displayLeaseBean = new DisplayLeaseBean ();
				displayLeaseBean.setName(s[0]);
				displayLeaseBean.setEmail(s[1]);
				displayLeaseBean.setAddress(s[2]);
				displayLeaseBean.setStartDate(s[3]);
				displayLeaseBean.setEndDate(s[4]);
				displayLeaseBean.setRent(s[5]);
				
				leaseTableList.add(displayLeaseBean);
			}
			
			leaseTable.setItems(leaseTableList);

			name.setCellValueFactory(new PropertyValueFactory<DisplayLeaseBean,String>("name"));
			email.setCellValueFactory(new PropertyValueFactory<DisplayLeaseBean,String>("email"));
			address.setCellValueFactory(new PropertyValueFactory<DisplayLeaseBean,String>("address"));
			startDate.setCellValueFactory(new PropertyValueFactory<DisplayLeaseBean,String>("startDate"));
			endDate.setCellValueFactory(new PropertyValueFactory<DisplayLeaseBean,String>("endDate"));
			rent.setCellValueFactory(new PropertyValueFactory<DisplayLeaseBean,String>("rent"));
			
		}
	}
}
