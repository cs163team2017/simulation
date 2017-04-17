package Simulation;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
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
		SimStatsPane statsPn = new SimStatsPane();
		SimAnimationPane animePn = new SimAnimationPane();
		SimButtonPane buttonPn = new SimButtonPane();
		SimSettings settings = new SimSettings();
		
		MenuBar menuBar = new MenuBar();
		Menu fileMenu = new Menu("File");
		MenuItem newItm = new MenuItem("New");
		
	    fileMenu.getItems().add(newItm);
	    menuBar.getMenus().add(fileMenu);
		mainPn.setTop(menuBar);
	
		
		mainPn.setRight(buttonPn);
		mainPn.setBottom(statsPn);
		mainPn.setCenter(animePn);
		
		Scene mainScene = new Scene(mainPn, 800, 600);
		primaryStage.setScene(mainScene);
		primaryStage.show();
		
		Controller c = new Controller(buttonPn, animePn, statsPn, 
									  settings, newItm);
		c.setupSim();
		
		
		primaryStage.setOnCloseRequest(e -> {
	        Platform.exit();
	        System.exit(0);
	    });
	}
}
