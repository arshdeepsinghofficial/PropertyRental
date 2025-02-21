/** SOEN 6441 Winter 2023 section WW: Project Phase 1

© Name : Arshdeep Singh (40238619) and Khush Hardikkumar Jani (40230516)
Submitted To : Shubham Bhanderi.
Professor : Nagi Basha
**/

package application;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MainController implements Initializable
{
	@FXML
	private Label myMessage;
	@FXML
	public ComboBox combobox;
	ObservableList<String> list = FXCollections.observableArrayList("APARTMENT","CONDO","HOUSE");

//	public void generateRandome(ActionEvent ae)
//	{
//		Random rand = new Random();
//		int no = rand.nextInt(50)+1;
//		//System.out.println(no);
//		myMessage.setText(Integer.toString(no));
//		
//	}
	
	@FXML
	public void clickAddProperty() throws Exception
	{
		Parent root = FXMLLoader.load(getClass().getResource("AddProperty.fxml"));
		Stage primaryStage = new Stage();
		primaryStage.setTitle("Add Property");
		Scene scene = new Scene(root,600,500);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	@FXML
	public void clickAddTenant() throws Exception
	{
		Parent root = FXMLLoader.load(getClass().getResource("AddTenant.fxml"));
		Stage primaryStage = new Stage();
		primaryStage.setTitle("Add Tenant");
		Scene scene = new Scene(root,600,600);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	@FXML
	public void rentUnit() throws Exception
	{
		Parent root = FXMLLoader.load(getClass().getResource("RentUnit.fxml"));
		Stage primaryStage = new Stage();
		primaryStage.setTitle("Rent a Unit");
		Scene scene = new Scene(root,630,600);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	@FXML
	public void displayProperties() throws Exception
	{
		Parent root = FXMLLoader.load(getClass().getResource("DisplayProperties.fxml"));
		Stage primaryStage = new Stage();
		primaryStage.setTitle("Display Properties");
		primaryStage.setTitle("displayProperties");
		Scene scene = new Scene(root,780,650);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	@FXML
	public void displayTenants() throws Exception
	{
		Parent root = FXMLLoader.load(getClass().getResource("DisplayTenant.fxml"));
		Stage primaryStage = new Stage();
		primaryStage.setTitle("display Tenant List");
		Scene scene = new Scene(root,780,500);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	@FXML
	public void displayRentedUnits() throws Exception
	{
		Parent root = FXMLLoader.load(getClass().getResource("DisplayRentedProperties.fxml"));
		Stage primaryStage = new Stage();
		primaryStage.setTitle("Display Rented Properties");
		Scene scene = new Scene(root,780,650);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	@FXML
	public void displayVacentUnits() throws Exception
	{
		Parent root = FXMLLoader.load(getClass().getResource("DisplayVacantProperties.fxml"));
		Stage primaryStage = new Stage();primaryStage.setTitle("");
		primaryStage.setTitle("Display Vacent Properties");
		Scene scene = new Scene(root,780,650);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	@FXML
	public void displayAllLeases() throws Exception
	{
		Parent root = FXMLLoader.load(getClass().getResource("DisplayLeases.fxml"));
		Stage primaryStage = new Stage();primaryStage.setTitle("");
		primaryStage.setTitle("Display Leases");
		Scene scene = new Scene(root,780,650);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	@FXML
	public void notifyVacantProperties() throws Exception
	{
		Parent root = FXMLLoader.load(getClass().getResource("NotifyVacantProperties.fxml"));
		Stage primaryStage = new Stage();primaryStage.setTitle("");
		primaryStage.setTitle("Sent Email List");
		Scene scene = new Scene(root,760,600);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	@FXML
	public void updatePaymentStatus() throws Exception
	{
		Parent root = FXMLLoader.load(getClass().getResource("UpdatePaymentStatus.fxml"));
		Stage primaryStage = new Stage();primaryStage.setTitle("");
		primaryStage.setTitle("Update Payment Status");
		Scene scene = new Scene(root,600,500);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	@FXML
	public void displayNonPaidRentalList() throws Exception
	{
		Parent root = FXMLLoader.load(getClass().getResource("DisplayNonPaidRental.fxml"));
		Stage primaryStage = new Stage();primaryStage.setTitle("");
		primaryStage.setTitle("Display Non Paid Rental List");
		Scene scene = new Scene(root,760,600);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	@FXML
	public void exit(ActionEvent e) throws IOException
	{
		Node node = (Node)e.getSource();
		Stage stage = (Stage)node.getScene().getWindow();
		stage.close();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		combobox = new ComboBox<>();
		combobox.setItems(list);
		
	}
	
	
}
