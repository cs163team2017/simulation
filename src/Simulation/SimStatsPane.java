package Simulation;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/***************************************************************************
 * Pane Class that holds all the stats for the GUI
 * @author Richard Critchlow
 * @version April 2017
 ***************************************************************************/
public class SimStatsPane extends HBox{
	
	/**Label that holds the average time for a customer to walk in**/
	private Label inflowLbl;
	/**Label that holds the average time for a cashier to service**/
	private Label cashierTimeLbl;
	/**Labal that holds the average time for a eatery to service**/
	private Label avgEateryTimeLbl;
	/**Label the holds the average time for a person to leave the line**/
	private Label quitTimeLbl;
	/**Label that shows the current step vs the total steps**/
	private Label currStepLbl;
	
	/***********************************************************************
	 * Constructor that builds the pane to hold the stats
	 ***********************************************************************/
	public SimStatsPane(){
		
		setSpacing(10);
		setAlignment(Pos.BOTTOM_CENTER);
		
		inflowLbl = new Label();
		cashierTimeLbl = new Label();
		avgEateryTimeLbl = new Label();
		quitTimeLbl = new Label();
		currStepLbl = new Label();
		
		update();
		
		getChildren().addAll(inflowLbl, cashierTimeLbl, avgEateryTimeLbl,
				quitTimeLbl, currStepLbl);
	}
	
	/***********************************************************************
	 * Fills the Labels with the stats from the Stats Class
	 **********************************************************************/
	public void update(){
		inflowLbl.setText("Seconds between customers: " + Stats.inflow);
		cashierTimeLbl.setText("Cashier Time: " + Stats.cashierTime);
		avgEateryTimeLbl.setText("Avg Time at Eatery: " + Stats.avgEateryTime);
		quitTimeLbl.setText("Time before someone leaves: " + Stats.quitTime);
		currStepLbl.setText("Current time: " + Stats.currTime + "/" + Stats.runtime);
	}
}
