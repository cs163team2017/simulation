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
	private Button saveBtn;
	private Button exitBtn;
	
	private TextField inflowTF;
	private TextField cashierTimeTF;
	private TextField avgEateryTimeTF;
	private TextField quitTimeTF;
	private TextField numEaterysTF;
	private TextField numCheckoutsTF;
	private TextField runtimeTF;
	
	private Label inflowLbl;
	private Label cashierTimeLbl;
	private Label avgEateryTimeLbl;
	private Label quitTimeLbl;
	private Label numEaterysLbl;
	private Label numCheckoutsLbl;
	private Label runtimeLbl;
	
	private VBox col1 = new VBox(25);
	private VBox col2 = new VBox(25);
	
	private Stage window;
	
	/***********************************************************************
	 * Displays the current Pane an does not allow the user to access other
	 * windows.
	 ***********************************************************************/
	public void display(){
		window = new Stage();
		
		//Only Focus this window
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Simulation Settings");
		window.setMinHeight(300);
		window.setMinWidth(500);
		
		titleLabels();
		titleButtons();
		fillTextFields();
		makeCol1();
		makeCol2();
		
		HBox horizontal = new HBox(100);
		horizontal.setAlignment(Pos.CENTER);
		horizontal.getChildren().addAll(col1, col2);
		
		listen();
		Scene settingScene = new Scene(horizontal);
		window.setScene(settingScene);
		window.showAndWait();
		
	}
	
	/***********************************************************************
	 * Gives a title to all the Labels
	 ***********************************************************************/
	private void titleLabels(){
		inflowLbl = new Label("Average time until next person:");
		cashierTimeLbl = new Label("Average time per cashier");
		avgEateryTimeLbl = new Label("Average time until next person:");
		quitTimeLbl = new Label("Time until a person leaves");
		numEaterysLbl = new Label("Number of Eaterys");
		numCheckoutsLbl = new Label("Number of Checkout locations:");
		runtimeLbl = new Label("Total Time to run the simulation");
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
		inflowTF = new TextField("20");
		cashierTimeTF = new TextField("10");
		avgEateryTimeTF = new TextField("60");
		quitTimeTF = new TextField("900");
		numEaterysTF = new TextField("2");
		numCheckoutsTF = new TextField("1");
		runtimeTF = new TextField("10000");
		
		inflowTF.setPromptText("20");
		cashierTimeTF.setPromptText("10");
		avgEateryTimeTF.setPromptText("60");
		quitTimeTF.setPromptText("900");
		numEaterysTF.setPromptText("2");
		numCheckoutsTF.setPromptText("1");
		runtimeTF.setPromptText("10000");
	}
	
	/***********************************************************************
	 *Makes a column of Labels 
	 ***********************************************************************/
	private void makeCol1(){
		col1.setAlignment(Pos.BASELINE_RIGHT);
		col1.getChildren().addAll(inflowLbl, cashierTimeLbl, 
				avgEateryTimeLbl, quitTimeLbl, numEaterysLbl, 
				numCheckoutsLbl, runtimeLbl, saveBtn);
	}
	
	/***********************************************************************
	 * Makes a column of Text Fields
	 ***********************************************************************/
	private void makeCol2(){
		col2.getChildren().addAll(inflowTF, cashierTimeTF, 
				avgEateryTimeTF, quitTimeTF, numEaterysTF, 
				numCheckoutsTF, runtimeTF, exitBtn);
	}
	
	/***********************************************************************
	 * "Event Handler" for the Settings GUI
	 ***********************************************************************/
	private void listen(){
		saveBtn.setOnAction(e -> {
			Stats.inflow = Integer.parseInt(inflowTF.getText());
			Stats.cashierTime = Integer.parseInt(cashierTimeTF.getText());
			Stats.avgEateryTime = Integer.parseInt(avgEateryTimeTF.getText());
			Stats.quitTime = Integer.parseInt(quitTimeTF.getText());
			Stats.numEaterys = Integer.parseInt(numEaterysTF.getText());
			Stats.numCheckouts = Integer.parseInt(numCheckoutsTF.getText());
			Stats.runtime = Integer.parseInt(runtimeTF.getText());
		});
		
		exitBtn.setOnAction(e -> {
			window.close();
		});
	}
}
