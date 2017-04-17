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
	
	private Button startBtn;
	private Button stopBtn;
	private Button stepBtn;
	
	
	/***********************************************************************
	 * Constructor that builds the Pane
	 ***********************************************************************/
	public SimButtonPane(){

		setPadding(new Insets(0, 25, 0, 0));
		setSpacing(25);
		setAlignment(Pos.CENTER_RIGHT);
		
		startBtn = new Button("Start");
		stopBtn = new Button("Pause");
		stepBtn = new Button("Step");
		
		getChildren().addAll(startBtn, stopBtn, stepBtn);	
	}	


	/***********************************************************************
	 * @return the startBtn
	 ***********************************************************************/
	public Button getStartBtn() {
		return startBtn;
	}


	/***********************************************************************
	 * @return the stopBtn
	 ***********************************************************************/
	public Button getStopBtn() {
		return stopBtn;
	}


	/***********************************************************************
	 * @return the stepBtn
	 **********************************************************************/
	public Button getStepBtn() {
		return stepBtn;
	}


}
