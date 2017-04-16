package Simulation;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

/***************************************************************************
 * Class that holds all the buttons for the GUI
 * @author Richard Critchlow
 * @version April 2017
 ***************************************************************************/
public class SimButtonPane extends VBox {
	
	private SimAnimationPane animePn;
	private SimStatsPane statsPn;
	
	private Controller c;

	private Button startBtn;
	private Button stopBtn;
	private Button stepBtn;
	
	
	/***********************************************************************
	 * Constructor that builds the Pane
	 ***********************************************************************/
	public SimButtonPane(SimAnimationPane animePn, SimStatsPane statsPn){
		this.animePn = animePn;
		this.statsPn = statsPn;
		
		
		setPadding(new Insets(0, 25, 0, 0));
		setSpacing(25);
		setAlignment(Pos.CENTER_RIGHT);
		
		startBtn = new Button("Start");
		stopBtn = new Button("Stop");
		stepBtn = new Button("Step");
		
		getChildren().addAll(startBtn, stopBtn, stepBtn);	
		c = new Controller(this.animePn, this.statsPn);
		listen();
	}
	
	/******************************************************************
	 * "Event Handler" for the GUI
	 ******************************************************************/
	private void listen(){
		
		//Using lambda functions for action listeners
		startBtn.setOnAction(e -> {
			c.setupSim();
			c.startSim();
		});
		
		stopBtn.setOnAction(e -> {
			if (stopBtn.getText().equals("Stop")){
					c.stopSim();
					stopBtn.setText("Resume");
			}
			else{
				c.resumeSim();
				stopBtn.setText("Stop");
			}
		});
		
		stepBtn.setOnAction(e -> {
			System.out.println("step clicked");
		});
		
	}
}
