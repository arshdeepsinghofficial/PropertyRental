/** SOEN 6441 Winter 2023 section WW: Project Phase 1

© Name : Arshdeep Singh (40238619) and Khush Hardikkumar Jani (40230516)
Submitted To : Shubham Bhanderi.
Professor : Nagi Basha
**/


package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) 
	{
		try 
		{
			//BorderPane root = new BorderPane();
			Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
			Scene scene = new Scene(root,500,600);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}

	public static void main(String[] args) 
	{
		//Initiating Separate thread to launch Graphic User Interface
		
		Thread thread = new Thread(new Runnable()
		{
			public void run()
			{
				launchApplication(args);
			}
		});
		thread.start();

	}
	
	public static void launchApplication(String[] args)
	{
		launch(args);
	}
}
