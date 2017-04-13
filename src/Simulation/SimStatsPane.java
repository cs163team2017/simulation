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
		
		paintStats();
		
		getChildren().addAll(inflowLbl, cashierTimeLbl, avgEateryTimeLbl,
				quitTimeLbl, currStepLbl);
	}
	
	public void paintStats(){
		inflowLbl.setText("Seconds between customers: " + Stats.inflow);
		cashierTimeLbl.setText("Cashier Time: " + Stats.cashierTime);
		avgEateryTimeLbl.setText("Avg Time at Eatery: " + Stats.avgEateryTime);
		quitTimeLbl.setText("Time before someone leaves: " + Stats.quitTime);
		currStepLbl.setText("Current time: " + Stats.currTime + "/" + Stats.runtime);
	}

	/**
	 * @return the inflowLbl
	 */
	public Label getInflowLbl() {
		return inflowLbl;
	}

	/**
	 * @param inflowLbl the inflowLbl to set
	 */
	public void setInflowLbl(Label inflowLbl) {
		this.inflowLbl = inflowLbl;
	}

	/**
	 * @return the cashierTimeLbl
	 */
	public Label getCashierTimeLbl() {
		return cashierTimeLbl;
	}

	/**
	 * @param cashierTimeLbl the cashierTimeLbl to set
	 */
	public void setCashierTimeLbl(Label cashierTimeLbl) {
		this.cashierTimeLbl = cashierTimeLbl;
	}

	/**
	 * @return the avgEateryTimeLbl
	 */
	public Label getAvgEateryTimeLbl() {
		return avgEateryTimeLbl;
	}

	/**
	 * @param avgEateryTimeLbl the avgEateryTimeLbl to set
	 */
	public void setAvgEateryTimeLbl(Label avgEateryTimeLbl) {
		this.avgEateryTimeLbl = avgEateryTimeLbl;
	}

	/**
	 * @return the quitTimeLbl
	 */
	public Label getQuitTimeLbl() {
		return quitTimeLbl;
	}

	/**
	 * @param quitTimeLbl the quitTimeLbl to set
	 */
	public void setQuitTimeLbl(Label quitTimeLbl) {
		this.quitTimeLbl = quitTimeLbl;
	}

	/**
	 * @return the currStepLbl
	 */
	public Label getCurrStepLbl() {
		return currStepLbl;
	}

	/**
	 * @param currStepLbl the currStepLbl to set
	 */
	public void setCurrStepLbl(Label currStepLbl) {
		this.currStepLbl = currStepLbl;
	}

}
