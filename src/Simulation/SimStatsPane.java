package Simulation;

import java.text.DecimalFormat;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;

/***************************************************************************
 * Pane Class that holds all the stats for the GUI
 * @author Richard Critchlow
 * @version April 2017
 ***************************************************************************/
public class SimStatsPane extends FlowPane{
	
	/**Holds the average time for a customer to walk in**/
	private Label inflowLbl;
	/**Holds the average time for a cashier to service**/
	private Label cashierTimeLbl;
	/**Holds the average time for a eatery to service**/
	private Label avgEateryTimeLbl;
	/**Holds the average time for a person to leave the line**/
	private Label quitTimeLbl;
	/**Shows the current step over the total steps**/
	private Label currStepLbl;
	/**Holds the total number of people through the sim**/
	private Label totalThruLbl;
	/**Holds the average time it takes for a person 
	 * to complete the simulation**/
    private Label avgTimeToSucceedLbl;
    /**Holds The Success percent rate (Succeeded/All)**/ 
    private Label successRateLbl;
    /**Holds the max number of people in the mainQ**/
    private Label maxMainQLineLbl;
    /**Holds the longest line ANY eatery has had**/
    private Label maxEateryLineLbl;
    /**Holds the current number of people in lines at the Eatery**/
    private Label inEaterLinesLbl;
    /**Holds the current number of people in line at the mainQ**/
    private Label inMainQLineLbl;
    /**Holds the current number of people in the checkout Lines**/
    private Label inCashierLineLbl;
    /**Holds the number of people lost**/
    private Label totalLostLbl;
    /**Holds the number of people lost from waiting at Eateries**/
    private Label totalLostAtEateryLbl;
    /**Holds the number of people lost from waiting in the mainQ**/
    private Label totalLostAtMainQLbl;
    
	/***********************************************************************
	 * Constructor that builds the pane to hold the stats
	 ***********************************************************************/
	public SimStatsPane(){
		
		setPadding(new Insets(10,10,10,10));
		setHgap(30);
		setVgap(10);
		setPrefWrapLength(170); //Supposedly allows for multiple columns
		
		inflowLbl = new Label();
		cashierTimeLbl = new Label();
		avgEateryTimeLbl = new Label();
		quitTimeLbl = new Label();
		currStepLbl = new Label();
		totalThruLbl = new Label();
		avgTimeToSucceedLbl = new Label();
		successRateLbl = new Label();
		maxMainQLineLbl = new Label();
		maxEateryLineLbl = new Label();
		inEaterLinesLbl = new Label();
		inMainQLineLbl = new Label();
		inCashierLineLbl = new Label();
		totalLostLbl = new Label();
		totalLostAtEateryLbl = new Label();
		totalLostAtMainQLbl = new Label();
		
		update();
		
		getChildren().addAll(inflowLbl, 
				cashierTimeLbl, 
				avgEateryTimeLbl,
				quitTimeLbl, 
				currStepLbl, 
				totalThruLbl,
				avgTimeToSucceedLbl,
				successRateLbl,
				maxMainQLineLbl,
				maxEateryLineLbl,
				inEaterLinesLbl,
				inMainQLineLbl,
				inCashierLineLbl,
				totalLostLbl,
				totalLostAtEateryLbl,
				totalLostAtMainQLbl);
	}
	
	/***********************************************************************
	 * Fills the Labels with the stats from the Stats Class
	 **********************************************************************/
	public void update(){
		Stats.updateAll();
		inflowLbl.setText("Seconds between customers: " + 
				Stats.inflow);
		cashierTimeLbl.setText("Cashier Time: " + 
				Stats.cashierTime);
		avgEateryTimeLbl.setText("Avg Time at Eatery: " + 
				Stats.avgEateryTime);
		quitTimeLbl.setText("Time before someone leaves: " + 
				Stats.quitTime);
		currStepLbl.setText("Current time: " + 
				Stats.currTime + "/" + Stats.runtime);
		totalThruLbl.setText("Total number of people through: " + 
				Stats.cashiersThru);
		avgTimeToSucceedLbl.setText("Average time to complete the sim: " +
				new DecimalFormat("#.##").format(Stats.avgTimeToSucceed));
		successRateLbl.setText("Success Rate: " + 
				new DecimalFormat("##").format(Stats.successRate) + "%");
		maxMainQLineLbl.setText("Largest Main Queue Length: " +
				Stats.maxMainQLine);
		maxEateryLineLbl.setText("Largest Eatery Queue Length: " +
				Stats.maxEateryLine);
		inEaterLinesLbl.setText("Current Number of people at Eateries: " +
				Stats.inEateryLines);
		inMainQLineLbl.setText("Current Number of people at Main Queue: " +
				Stats.pplAtMainQ);
		inCashierLineLbl.setText("Current Number of people at Cashiers: " +
				Stats.inCashierLines);
		totalLostLbl.setText("Total number of people Lost: " + 
				Stats.totalLost);
		totalLostAtEateryLbl.setText("Total lost at Eateries: " +
				Stats.totalLostAtEatery);
		totalLostAtMainQLbl.setText("Total lost at MainQ: " + 
				Stats.totalLostAtMainQ);
	}
}
