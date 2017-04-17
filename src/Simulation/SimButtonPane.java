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
	
	/**Button that starts and restarts the simulation**/
	private Button startBtn;
	/**Button that pauses and resumes the simulation**/
	private Button stopBtn;
	/**Button that moves the simulation one tick forward**/
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
	 * Getter
	 * @return the startBtn
	 ***********************************************************************/
	public Button getStartBtn() {
		return startBtn;
	}

	/***********************************************************************
	 * Getter
	 * @return the stopBtn
	 ***********************************************************************/
	public Button getStopBtn() {
		return stopBtn;
	}

	/***********************************************************************
	 * Getter
	 * @return the stepBtn
	 **********************************************************************/
	public Button getStepBtn() {
		return stepBtn;
	}
}