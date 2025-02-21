package application;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class DisplayTenantController 
{
	@FXML public TableView<DisplayTenantBean> tenantTable;
	@FXML public TableColumn<DisplayTenantBean, String> name;
	@FXML public TableColumn<DisplayTenantBean, String> email;
	
	PropertyFieldsBean propertyFieldBean = new PropertyFieldsBean();
	
	
	public void refreshList()
	{
		Thread thread = new Thread(new Runnable()
		{
			public void run()
			{
				propertyFieldBean.list =  propertyFieldBean.controller.getAllTenantsController();
				printTable();
			}	
		});
		thread.start();
//		propertyFieldBean.list =  propertyFieldBean.controller.getAllTenantsController();
//		printTable();
	}
	
	public void printTable()
	{
		ObservableList<DisplayTenantBean> tenantTableList = FXCollections.observableArrayList();
		if(propertyFieldBean.list.size() != 0)
		{
			for(String [] s : (ArrayList<String[]>)propertyFieldBean.list)
			{
				DisplayTenantBean displayTenantBean = new DisplayTenantBean ();
				displayTenantBean.setName(s[0]);
				displayTenantBean.setEmail(s[1]);
				
				tenantTableList.add(displayTenantBean);
			}
			
			tenantTable.setItems(tenantTableList);

			name.setCellValueFactory(new PropertyValueFactory<DisplayTenantBean,String>("name"));
			email.setCellValueFactory(new PropertyValueFactory<DisplayTenantBean,String>("email"));
			
		}
	}
}
