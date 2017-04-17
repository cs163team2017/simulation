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
	
	private Label inflowLbl;
	private Label cashierTimeLbl;
	private Label avgEateryTimeLbl;
	private Label quitTimeLbl;
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
	
	public void update(){
		inflowLbl.setText("Seconds between customers: " + Stats.inflow);
		cashierTimeLbl.setText("Cashier Time: " + Stats.cashierTime);
		avgEateryTimeLbl.setText("Avg Time at Eatery: " + Stats.avgEateryTime);
		quitTimeLbl.setText("Time before someone leaves: " + Stats.quitTime);
		currStepLbl.setText("Current time: " + Stats.currTime + "/" + Stats.runtime);
	}



}
