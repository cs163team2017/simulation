package Simulation;

import java.util.ArrayList;

import com.sun.xml.internal.bind.v2.runtime.output.IndentingUTF8XmlOutput;

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
	
	/**VBox for the eateries**/
	private VBox leftPn;
	/**VBox for the MainQ**/
	private VBox centerPn;
	/**VBox for the checkouts**/
	private VBox rightPn;
	
	/**ArrayList of Labels to hold all the mainQ's (set up for expansion)**/
	private ArrayList<Label> mainQLbl;
	/**ArrayList of Labels to hold all the Eateries and their queues**/
	private ArrayList<Label> eateryLbl; 
	/**ArrayList of Labels to hold all the Checkouts and their lines**/
	private ArrayList<Label> checkoutLbl;
	
	/**Label for the eatery graphic**/
	private Label storeLbl;
	/**Label for the checkout graphic**/
	private Label registerLbl;

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
		
	}
	
	/***********************************************************************
	 * Repaints the Pane with the current stats
	 ***********************************************************************/
	public void repaint(){
		//Clear the Arrays for the board
		eateryLbl.clear();
		checkoutLbl.clear();
		
		leftPn.getChildren().clear();
		centerPn.getChildren().clear();
		rightPn.getChildren().clear();
		this.getChildren().clear();
		
		//Make the Left, Center, and Right Column Labels
		for (int i = 0; i < Stats.numEaterys; i++)
			leftPn.getChildren().add(makeEateryRow(i));
	
		centerPn.getChildren().add(makeMainQRow(0));
		
		for (int i = 0; i < Stats.numCheckouts; i++)
			rightPn.getChildren().add(makeCheckoutRow(i));
		
		//Align Panes
		leftPn.setAlignment(Pos.CENTER_LEFT);
		centerPn.setAlignment(Pos.CENTER);
		rightPn.setAlignment(Pos.CENTER_RIGHT);
		this.getChildren().addAll(leftPn, centerPn, rightPn);
	}
	
	/***********************************************************************
	 * Updates the lines using the ArrayLists given
	 * @param eateries ArrayList<ArrayList<Person>> of the eateries
	 * @param pplWaiting ArrayList<Person> of people in the mainQ
	 * @param pplPaying ArrayList<Person> of people in the checkout line
	 ***********************************************************************/
	public void update(ArrayList<ArrayList<Person>> eateries, 
						ArrayList<Person> pplWaiting, 
						ArrayList<Person> pplPaying){
		
		//Updates the eatery's lines
		int i = 0;
		if (eateries.isEmpty())
			for (Label L : eateryLbl)
				L.setText("Empty");
		else{
			try{
				for (ArrayList<Person> eatery : eateries){ 
					String str = "";
					for( Person p : eatery){
						if (p instanceof LimitedTimePerson){
							str += "L ";
							eateryLbl.get(i).setText(str);
						}
						else if (p instanceof SpecialNeedsPerson){
							str += "S ";
							eateryLbl.get(i).setText(str);
						}
						else {
							str += "R ";
							eateryLbl.get(i).setText(str);
						}
					}
				i++;
				}
			}
			catch(IndexOutOfBoundsException e){
				try{
					eateryLbl.get(i).setText("Empty");
				}
				catch(IndexOutOfBoundsException ex){
					eateryLbl.add(new Label("Empty"));
				}
			}
		}
		
		//Updates the mainQ line
		i=0;
		if(pplWaiting.isEmpty())
			mainQLbl.get(i).setText("Empty");
		else
			try{
				String str = "";
				for( Person p : pplWaiting){
					if (p instanceof LimitedTimePerson){
						str += "L ";
						mainQLbl.get(i).setText(str);
					}
					else if (p instanceof SpecialNeedsPerson){
						str += "S ";
						mainQLbl.get(i).setText(str);
					}
					else {
						str += "R ";
						mainQLbl.get(i).setText(str);
					}
				}
			}
			catch(IndexOutOfBoundsException e){
				mainQLbl.get(0).setText("Empty");
			}
		
		//Updates the checkout lines
		i=0;
		if(pplPaying.isEmpty())
			for (Label L : checkoutLbl)
				L.setText("Empty");
		else
			try{
				String str = "";
				for( Person p : pplPaying){
					if (p instanceof LimitedTimePerson){
						str += "L ";
						checkoutLbl.get(i).setText(str);
					}
					else if (p instanceof SpecialNeedsPerson){
						str += "S ";
						checkoutLbl.get(i).setText(str);
					}
					else {
						str += "R ";
						checkoutLbl.get(i).setText(str);
					}
				i++;
				}				
			}
			catch(IndexOutOfBoundsException e){
				mainQLbl.get(0).setText("Empty");
			}
			
	//}there is one bracket below	//FIXME delete line <---
			
			
				
//FIXME the old number GUI - delete me after above works
				//Fill the Labels with information		
//		for (int i = 0; i < Stats.numEaterys; i++){
//			try{
//				eateryLbl.get(i).setText("Num people at eatery " + (i+1) + 
//						" : " + Stats.pplAtEatery.get(i));
//			}
//			catch(IndexOutOfBoundsException e){
//				Stats.pplAtEatery.add(i,0);
//				eateryLbl.get(i).setText("Num people at eatery " + (i+1) + 
//						" : " + Stats.pplAtEatery.get(i));
//			}
//		}
//			mainQLbl.get(0).setText("Num people at MainQ: " +  Stats.pplAtMainQ);
//		}
//		for (int i = 0; i < Stats.numCheckouts; i++){
//			try{
//				checkoutLbl.get(i).setText("Num people at checkout " + (i+1) + 
//						" : " + Stats.pplAtCheckout.get(i));
//			}
//			catch(IndexOutOfBoundsException e){
//				Stats.pplAtCheckout.add(i,0);
//				checkoutLbl.get(i).setText("Num people at checkout " + (i+1) + 
//						" : " + Stats.pplAtCheckout.get(i));
//			}
//		}
	}
	

	/***********************************************************************
	 * Makes a row of an Eatery based on the parameter 
	 * @param n The number of Eatery
	 * @return HBox Returns an HBox of Labels
	 ***********************************************************************/
	private HBox makeEateryRow(int n){
		HBox row = new HBox(25);
		row.setAlignment(Pos.CENTER_LEFT);
		
		storeLbl = new Label();///FIXME put me up top
		storeLbl.setPrefSize(25, 75);
		storeLbl.setStyle("-fx-border-color: black;");

		
		eateryLbl.add(new Label());
		row.getChildren().addAll(storeLbl, eateryLbl.get(n));
		return row;
	}
	
	/***********************************************************************
	 * Makes a row of a Checkout based on the parameter 
	 * @param n The current number of the checkout 
	 * @return HBox Returns an HBox of Labels
	 ***********************************************************************/
	private HBox makeCheckoutRow(int n){
		HBox row = new HBox(5);
		row.setAlignment(Pos.CENTER_RIGHT);
		
		registerLbl = new Label();
		registerLbl.setPrefSize(25, 25);
		registerLbl.setStyle("-fx-border-color: black;");
		
		checkoutLbl.add(new Label());
		row.getChildren().addAll(checkoutLbl.get(n), registerLbl);
		return row;
	}
	
	/***********************************************************************
	 * Makes a row of a Main Queues based on the parameter  
	 * @param n The current number of the Queue 
	 * @return HBox Returns an HBox of Labels
	 ***********************************************************************/
	private HBox makeMainQRow (int n){
		HBox row = new HBox(25);
		mainQLbl.add(new Label("                    "));
		mainQLbl.get(n).setStyle("-fx-border-color: black;");
		row.getChildren().add(mainQLbl.get(n));
		return row;
	}
}
