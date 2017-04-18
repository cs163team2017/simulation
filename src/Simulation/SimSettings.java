package Simulation;

import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;

/***************************************************************************
 * Pane Class that asks the user for the Simulation Data while in the GUI
 * @author Richard Critchlow
 * @version April 2017
 ***************************************************************************/
public class SimSettings {
	
	/**Button that saves the data to be used in the simulation**/
	private Button saveBtn;
	/**Button that exits the window with no changes**/
	private Button exitBtn;
	
	/**TextField the average time a customer is created**/
	private TextField inflowTF;
	/**TextField the average time a cashier takes to service**/
	private TextField cashierTimeTF;
	/**TextField the average time a eatery takes to service**/
	private TextField avgEateryTimeTF;
	/**TextField the average time a person will wait before leaving**/
	private TextField quitTimeTF;
	/**TextField the number of eaterys in the simulation**/
	private TextField numEaterysTF;
	/**TextField the number of checkouts in the simulation**/
	private TextField numCheckoutsTF;
	/**TextField the total length of the simulation**/
	private TextField runtimeTF;
	
	/**Label that labels the inflowTF**/
	private Label inflowLbl;
	/**Label that labels the cashierTimeTF**/
	private Label cashierTimeLbl;
	/**Label that labels the avgEateryTimeTF**/
	private Label avgEateryTimeLbl;
	/**Label that labels the quitTimeTF**/
	private Label quitTimeLbl;
	/**Label that labels the numEaterysLblTF**/
	private Label numEaterysLbl;
	/**Label that labels the numCheckoutsLbl**/
	private Label numCheckoutsLbl;
	/**Label that labels the runtimeTF**/
	private Label runtimeLbl;
	
	/**VBox that holds the Labels**/
	private VBox col1 = new VBox(33);
	/**VBox that holds the TextFields**/
	private VBox col2 = new VBox(25);
	
	/**Stage for the Objects to be placed on**/
	private Stage window;
	
	/***********************************************************************
	 * Constructor that sets the stage and creates all the elements
	 * Ensures control of the program
	 ***********************************************************************/
	public SimSettings(){
		window = new Stage();
		
		//Only Focus this window
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Simulation Settings");
		window.setMinHeight(300);
		window.setMinWidth(500);
		
		//Add components
		titleLabels();
		titleButtons();
		fillTextFields();
		makeCol1();
		makeCol2();
		
		//sort components
		HBox horizontal = new HBox(10);
		horizontal.setAlignment(Pos.CENTER);
		horizontal.getChildren().addAll(col1, col2);

		//place components
		Scene settingScene = new Scene(horizontal);
		window.setScene(settingScene);
		window.hide();	
	}

	/***********************************************************************
	 * Displays the current Pane an does not allow the user to access other
	 * windows.
	 ***********************************************************************/
	public void display(){
		window.showAndWait();
	}
	
	/***********************************************************************
	 * Ensures the user placed valid inputs within the Pane
	 * @return True if all TextFields are greater than 0 and an integer
	 **********************************************************************/
	public boolean isValidInput(){
		boolean valid = true;
		try{
			Stats.inflow = Integer.parseInt(inflowTF.getText());
			Stats.cashierTime = Integer.parseInt(cashierTimeTF.getText());
			Stats.avgEateryTime = Integer.parseInt(avgEateryTimeTF.getText());
			Stats.quitTime = Integer.parseInt(quitTimeTF.getText());
			Stats.numEaterys = Integer.parseInt(numEaterysTF.getText());
			Stats.numCheckouts = Integer.parseInt(numCheckoutsTF.getText());
			Stats.runtime = Integer.parseInt(runtimeTF.getText());
		}
		catch(Exception ex){
			valid = false;
		}
		if(		Stats.inflow <= 0 		||
				Stats.cashierTime <= 0	||
				Stats.avgEateryTime <=0 ||
				Stats.quitTime <= 0		|| 
				Stats.numEaterys <= 0	||
				Stats.numCheckouts <= 0	||
				Stats.runtime <= 0	
				)
			valid = false;
		return valid;
	}

	/***********************************************************************
	 * Hides the window in the background
	 ***********************************************************************/
	public void closeWindow(){
		window.hide();
	}
	
	/***********************************************************************
	 * Getter
	 * @return the saveBtn
	 ***********************************************************************/
	public Button getSaveBtn() {
		return saveBtn;
	}
	
	/***********************************************************************
	 * Getter
	 * @return the exitBtn
	 ***********************************************************************/
	public Button getExitBtn() {
		return exitBtn;
	}
	
	/***********************************************************************
	 * Gives a title to all the Labels
	 ***********************************************************************/
	private void titleLabels(){
		inflowLbl = new Label("Average time until next person:");
		cashierTimeLbl = new Label("Average time per cashier:");
		avgEateryTimeLbl = new Label("Average time until next person:");
		quitTimeLbl = new Label("Time until a person leaves:");
		numEaterysLbl = new Label("Number of Eaterys:");
		numCheckoutsLbl = new Label("Number of Checkout locations:");
		runtimeLbl = new Label("Total Time to run the simulation:");
	}

	/***********************************************************************
	 * Gives a title to all the buttons
	 ***********************************************************************/
	private void titleButtons(){
		saveBtn = new Button("Save");
		exitBtn = new Button("Exit");
	}
	
	/***********************************************************************
	 * Gives all the text fields a prompt text
	 ***********************************************************************/
	private void fillTextFields(){
		//First default values //FIXME remove?
		inflowTF = new TextField("" + Stats.inflow);
		cashierTimeTF = new TextField("" + Stats.cashierTime);
		avgEateryTimeTF = new TextField("" + Stats.avgEateryTime);
		quitTimeTF = new TextField("" + Stats.quitTime);
		numEaterysTF = new TextField("" + Stats.numEaterys);
		numCheckoutsTF = new TextField("" + Stats.numCheckouts);
		runtimeTF = new TextField("" + Stats.runtime);
		
		//Prompt texts
		inflowTF.setPromptText("" + Stats.inflow);
		cashierTimeTF.setPromptText("" + Stats.cashierTime);
		avgEateryTimeTF.setPromptText("" + Stats.avgEateryTime);
		quitTimeTF.setPromptText("" + Stats.quitTime);
		numEaterysTF.setPromptText("" + Stats.numEaterys);
		numCheckoutsTF.setPromptText("" + Stats.numCheckouts);
		runtimeTF.setPromptText("" + Stats.runtime);
	}
	
	/***********************************************************************
	 *Makes a column of Labels 
	 ***********************************************************************/
	private void makeCol1(){
		col1.setAlignment(Pos.BOTTOM_RIGHT);
		col1.getChildren().addAll(inflowLbl, cashierTimeLbl, 
				avgEateryTimeLbl, quitTimeLbl, numEaterysLbl, 
				numCheckoutsLbl, runtimeLbl, saveBtn);
	}
	
	/***********************************************************************
	 * Makes a column of Text Fields
	 ***********************************************************************/
	private void makeCol2(){
		col2.setAlignment(Pos.BOTTOM_LEFT);
		col2.getChildren().addAll(inflowTF, cashierTimeTF, 
				avgEateryTimeTF, quitTimeTF, numEaterysTF, 
				numCheckoutsTF, runtimeTF, exitBtn);
	}	
}