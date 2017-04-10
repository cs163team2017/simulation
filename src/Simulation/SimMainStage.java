package Simulation;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;

public class SimMainStage extends Application {


	public static void main (String[] args){
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		//Initializing Variables
		primaryStage.setTitle("Food Court");
		
		BorderPane  mainPn = new BorderPane();
		SimButtonPane buttonPn = new SimButtonPane();
		SimStatsPane statsPn = new SimStatsPane();
		SimAnimationPane animePn = new SimAnimationPane();
		
		mainPn.setTop(buttonPn);
		mainPn.setBottom(statsPn);
		mainPn.setCenter(animePn);
		
		Scene mainScene = new Scene(mainPn, 800, 600);
		primaryStage.setScene(mainScene);
		primaryStage.show();
		
		primaryStage.setOnCloseRequest(e -> {
	        Platform.exit();
	        System.exit(0);
	    });
	}
}
