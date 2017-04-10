package Simulation;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

public class SimMainStage extends Application {

	//Variables
	Button btn1;
	
	public static void main (String[] args){
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Food Court");
		
		btn1 = new Button("Test Btn");
		
		btn1.setOnAction(e -> {
			System.out.println("clicked");
		});
		
		
		StackPane layout = new StackPane();
		layout.getChildren().add(btn1);
		
		Scene scene1 = new Scene(layout, 400, 400);
		primaryStage.setScene(scene1);
		primaryStage.show();
	}




}
