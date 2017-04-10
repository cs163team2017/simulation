package Simulation;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SimAnimationPane extends HBox {
	
	private Label[] mainQLbl;
	private Label[] eaterLbl; 
	private Label[] checkoutLbl;
	private Label[] inMainQLbl;
	private Label[] inEaterLbl; 
	private Label[] inCheckoutLbl;
	
	private int pAtMainQ;
	private int pAtEatery;
	private int pAtCheckout;

	public SimAnimationPane(){
		mainQLbl = new Label[1];
		eaterLbl = new Label[Stats.numEaterys]; 
		checkoutLbl = new Label[Stats.numCheckouts];
		inMainQLbl = new Label[1];
		inEaterLbl = new Label[Stats.numEaterys]; 
		inCheckoutLbl = new Label[Stats.numCheckouts];
		
		setSpacing(25);
		VBox leftPn = new VBox(25);
		HBox centerPn = new HBox(25);
		VBox rightPn = new VBox(25);
		
		for (int i = 0; i < Stats.numEaterys; i++)
			leftPn.getChildren().add(makeEateryRow(i));
		
		for (int i = 0; i < Stats.numCheckouts; i++)
			rightPn.getChildren().add(makeCheckoutRow(i));
		
		centerPn.getChildren().addAll(makeMainQRow(0));
		
		setAlignment(Pos.CENTER);
		getChildren().addAll(leftPn, centerPn, rightPn); 
		
	}

	private HBox makeEateryRow(int n){
		HBox row = new HBox(25);

		eaterLbl[n] = new Label("Num people at Eatery " + (n+1) + " :");
		inEaterLbl[n] = new Label("" + pAtEatery);
		row.getChildren().addAll(eaterLbl[n], inEaterLbl[n]);
		
		return row;
	}
	
	private HBox makeCheckoutRow(int n){
		HBox row = new HBox(25);
		checkoutLbl[n] = new Label("Num people at Checkout " + (n+1) + " :");
		inCheckoutLbl[n] = new Label("" + pAtCheckout);
		row.getChildren().addAll(checkoutLbl[n], inCheckoutLbl[n]);
		return row;
	}
	
	private HBox makeMainQRow (int n){
		HBox row = new HBox(25);
		mainQLbl[n] = new Label("Num people at MainQ:");
		inMainQLbl[n] = new Label("" + pAtMainQ);
		row.getChildren().addAll(mainQLbl[n], inMainQLbl[n]);
		return row;
	}
}
