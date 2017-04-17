package Simulation;

import java.util.ArrayList;

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
	
	private VBox leftPn;
	private VBox centerPn;
	private VBox rightPn;
	
	private ArrayList<Label> mainQLbl;
	private ArrayList<Label> eateryLbl; 
	private ArrayList<Label> checkoutLbl;

	/***********************************************************************
	 * Constructor that adds all the pieces to the Pane
	 ***********************************************************************/
	public SimAnimationPane(){
		mainQLbl = new ArrayList<Label>();
		eateryLbl = new ArrayList<Label>();
		checkoutLbl = new ArrayList<Label>();
        
		leftPn = new VBox(25); 
		centerPn = new VBox(25);
		rightPn = new VBox(25);
        
		setSpacing(25);
		setAlignment(Pos.CENTER);
		repaint();
		update();
	}
	
	public void repaint(){
		//Clear the Arrays for the board
		eateryLbl.clear();
		checkoutLbl.clear();
		
		leftPn.getChildren().clear();
		centerPn.getChildren().clear();
		rightPn.getChildren().clear();
		
		//Make the Left, Center, and Right Column Labels
		for (int i = 0; i < Stats.numEaterys; i++)
			leftPn.getChildren().add(makeEateryRow(i));
	
		centerPn.getChildren().add(makeMainQRow(0));
		
		for (int i = 0; i < Stats.numCheckouts; i++)
			rightPn.getChildren().add(makeCheckoutRow(i));
		
		//Align Panes
		leftPn.setAlignment(Pos.CENTER);
		centerPn.setAlignment(Pos.CENTER);
		rightPn.setAlignment(Pos.CENTER);
		this.getChildren().addAll(leftPn, centerPn, rightPn);
	}
	
	public void update(){
		//Fill the Labels with information		
		for (int i = 0; i < Stats.numEaterys; i++)
			eateryLbl.get(i).setText("Num people at eatery " + (i+1) + 
					" : " + Stats.pplAtEatery[i]);
		
		mainQLbl.get(0).setText("Num people at MainQ: " +  Stats.pplAtMainQ);
		
		for (int i = 0; i < Stats.numCheckouts; i++)
			checkoutLbl.get(i).setText("Num people at checkout " + (i+1) + 
					" : " + Stats.pplAtCheckout[i]);
	}

	/***********************************************************************
	 * Makes a row of an Eatery based on the parameter 
	 * @param n The number of Eatery
	 * @return HBox Returns an HBox of Labels
	 ***********************************************************************/
	private HBox makeEateryRow(int n){
		HBox row = new HBox(25);
		eateryLbl.add(new Label("Num people at Eatery " + (n+1) + " :"));
		row.getChildren().add(eateryLbl.get(n));
		return row;
	}
	
	/***********************************************************************
	 * Makes a row of a Checkout based on the parameter 
	 * @param n The current number of the checkout 
	 * @return HBox Returns an HBox of Labels
	 ***********************************************************************/
	private HBox makeCheckoutRow(int n){
		HBox row = new HBox(25);
		checkoutLbl.add(new Label("Num people at Checkout " + (n+1) + " :"));
		row.getChildren().add(checkoutLbl.get(n));
		return row;
	}
	
	/***********************************************************************
	 * Makes a row of a Main Queues based on the parameter  
	 * @param n The current number of the Queue 
	 * @return HBox Returns an HBox of Labels
	 ***********************************************************************/
	private HBox makeMainQRow (int n){
		HBox row = new HBox(25);
		mainQLbl.add(new Label());
		row.getChildren().add(mainQLbl.get(n));
		return row;
	}
}
