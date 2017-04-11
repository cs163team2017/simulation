package Simulation;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

/***************************************************************************
 * Class that holds all the buttons for the GUI
 * @author Richard Critchlow
 * @version April 2017
 ***************************************************************************/
public class SimButtonPane extends HBox {

	private Button startBtn;
	private Button stopBtn;
	private Button stepBtn;
	private Button settingBtn; //REMOVE shouldn't be a button
	
	private SimSettings settings;
	
	/***********************************************************************
	 * Constructor that builds the Pane
	 ***********************************************************************/
	public SimButtonPane(){
		
		setSpacing(25);
		setAlignment(Pos.TOP_CENTER);
		
		settings = new SimSettings();
		startBtn = new Button("Start");
		stopBtn = new Button("Stop");
		stepBtn = new Button("Step");
		settingBtn = new Button("Settings");
		
		getChildren().addAll(startBtn, stopBtn, stepBtn, settingBtn);	
		listen();
	}
	
	/******************************************************************
	 * "Event Handler" for the GUI
	 ******************************************************************/
	private void listen(){
		
		//Using lambda functions for action listeners
		startBtn.setOnAction(e -> {
			System.out.println("start clicked");
		});
		
		stopBtn.setOnAction(e -> {
			System.out.println("stop clicked");
		});
		
		stepBtn.setOnAction(e -> {
			System.out.println("step clicked");
		});
		
		settingBtn.setOnAction(e -> {
			settings.display();
		});
		
	}
}
