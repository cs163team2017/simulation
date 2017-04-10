package Simulation;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class SimStatsPane extends HBox{
	
	private Label inflowLbl;
	private Label cashierTimeLbl;
	private Label avgEateryTimeLbl;
	private Label quitTimeLbl;
	private Label currStepLbl;
	
	public SimStatsPane(){
		
		setSpacing(10);
		setAlignment(Pos.BOTTOM_CENTER);
		
		inflowLbl = new Label("Seconds between customers: " + Stats.inflow);
		cashierTimeLbl = new Label("Cashier Time: " + Stats.cashierTime);
		avgEateryTimeLbl = new Label("Avg Time at Eatery: " + Stats.avgEateryTime);
		quitTimeLbl = new Label("Time before someone leaves: " + Stats.quitTime);
		currStepLbl = new Label("Current time: " + Stats.currTime + "/" + Stats.runtime);
		
		getChildren().addAll(inflowLbl, cashierTimeLbl, avgEateryTimeLbl,
				quitTimeLbl, currStepLbl);
		
	}

}
