package Simulation;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/***************************************************************************
 * Pane Class that does all the animation for the GUI
 * @author Richard Critchlow
 * @version April 2017
 ***************************************************************************/
public class SimAnimationPane extends HBox {
	
	private Label[] mainQLbl;
	private Label[] eaterLbl; 
	private Label[] checkoutLbl;

	/***********************************************************************
	 * Constructor that adds all the pieces to the Pane
	 ***********************************************************************/
	public SimAnimationPane(){
		mainQLbl = new Label[1];
		eaterLbl = new Label[Stats.numEaterys]; 
		checkoutLbl = new Label[Stats.numCheckouts];
		
		setSpacing(25);
		VBox leftPn = new VBox(25);
		VBox centerPn = new VBox(25);
		VBox rightPn = new VBox(25);
		
		for (int i = 0; i < Stats.numEaterys; i++)
			leftPn.getChildren().add(makeEateryRow(i));
		
		for (int i = 0; i < Stats.numCheckouts; i++)
			rightPn.getChildren().add(makeCheckoutRow(i));
		
		centerPn.getChildren().add(makeMainQRow(0));
		
		setAlignment(Pos.CENTER);
		leftPn.setAlignment(Pos.CENTER);
		centerPn.setAlignment(Pos.CENTER);
		rightPn.setAlignment(Pos.CENTER);
		getChildren().addAll(leftPn, centerPn, rightPn); 
		repaint();
	}
	
	public void repaint(){
		for (int i = 0; i < Stats.numEaterys; i++)
			eaterLbl[i].setText("Num people at eatery " + (i+1) + " : " + Stats.pplAtEatery[i]);
		
		for (int i = 0; i < Stats.numCheckouts; i++)
			checkoutLbl[i].setText("Num people at checkout " + (i+1) + " : " + Stats.pplAtCheckout[i]);
		
		mainQLbl[0].setText("Num people at MainQ: " +  Stats.pplAtMainQ);
	}

	/***********************************************************************
	 * Makes a row of an Eatery based on the parameter 
	 * @param n The number of Eatery
	 * @return HBox Returns an HBox of Labels
	 ***********************************************************************/
	private HBox makeEateryRow(int n){
		HBox row = new HBox(25);

		eaterLbl[n] = new Label("Num people at Eatery " + (n+1) + " :");
		row.getChildren().add(eaterLbl[n]);
		
		return row;
	}
	
	/***********************************************************************
	 * Makes a row of a Checkout based on the parameter 
	 * @param n The current number of the checkout 
	 * @return HBox Returns an HBox of Labels
	 ***********************************************************************/
	private HBox makeCheckoutRow(int n){
		HBox row = new HBox(25);
		checkoutLbl[n] = new Label();
		row.getChildren().add(checkoutLbl[n]);
		return row;
	}
	
	/***********************************************************************
	 * Makes a row of a Main Queues based on the parameter  
	 * @param n The current number of the Queue 
	 * @return HBox Returns an HBox of Labels
	 ***********************************************************************/
	private HBox makeMainQRow (int n){
		HBox row = new HBox(25);
		mainQLbl[n] = new Label();
		row.getChildren().add(mainQLbl[n]);
		return row;
	}
}
