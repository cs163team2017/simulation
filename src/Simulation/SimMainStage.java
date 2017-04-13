package Simulation;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;

/***************************************************************************
 * The Main Stage that holds the GUI
 * @author Richard Critchlow
 * @version April 2017
 ***************************************************************************/
public class SimMainStage extends Application {

	/***********************************************************************
	 * The Main Method
	 * @param args The command line
	 ***********************************************************************/
	public static void main (String[] args){
		launch(args);
	}

	/***********************************************************************
	 * The necessary method to run a javafx GUI
	 * Puts the GUI together
	 ***********************************************************************/
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
		
		Controller c = new Controller(animePn, statsPn);
		c.startSim();
		
		primaryStage.setOnCloseRequest(e -> {
	        Platform.exit();
	        System.exit(0);
	    });
	}
}
