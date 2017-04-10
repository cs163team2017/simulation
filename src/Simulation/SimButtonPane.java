package Simulation;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class SimButtonPane extends HBox {
	//Declaring Variables
	private Button startBtn;
	private Button stopBtn;
	private Button stepBtn;
	private Button settingBtn; //REMOVE shouldn't be a button
	
	private SimSettings settings;
	
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
	 * "Action Listener" for the GUI
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
