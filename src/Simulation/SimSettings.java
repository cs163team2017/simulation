package Simulation;

import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;

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
	
	private VBox col1;
	private VBox col2;
	
	private int inflow;
	private int cashierTime;
	private int avgEateryTime;
	private int quitTime;
	private int numEaterys;
	private int numCheckouts;
	private int runtime;

	
	public SimSettings(){
		
		//defaulted for debugging
		inflow = 20;
		cashierTime = 10;
		runtime = 1000;
		avgEateryTime = 60;
		quitTime = 900;
		numEaterys = 2;
		numCheckouts = 1;
		
		col1 = new VBox(25);
		col2 = new VBox(25);

	}

	
	public void display(){
		Stage window = new Stage();
		
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
		
		Scene settingScene = new Scene(horizontal);
		window.setScene(settingScene);
		window.showAndWait();
	}
	
	private void titleLabels(){
		inflowLbl = new Label("Average time until next person:");
		cashierTimeLbl = new Label("Average time per cashier");
		avgEateryTimeLbl = new Label("Average time until next person:");
		quitTimeLbl = new Label("Time until a person leaves");
		numEaterysLbl = new Label("Number of Eaterys");
		numCheckoutsLbl = new Label("Number of Checkout locations:");
		runtimeLbl = new Label("Total Time to run the simulation");
	}

	private void titleButtons(){
		saveBtn = new Button("Save");
		exitBtn = new Button("Exit");
	}
	
	private void fillTextFields(){
		inflowTF = new TextField();
		cashierTimeTF = new TextField();
		avgEateryTimeTF = new TextField();
		quitTimeTF = new TextField();
		numEaterysTF = new TextField();
		numCheckoutsTF = new TextField();
		runtimeTF = new TextField();
	}
	
	private void makeCol1(){
		col1.setAlignment(Pos.BASELINE_RIGHT);
		col1.getChildren().addAll(inflowLbl, cashierTimeLbl, 
				avgEateryTimeLbl, quitTimeLbl, numEaterysLbl, 
				numCheckoutsLbl, runtimeLbl, saveBtn);
		
	}
	
	private void makeCol2(){
		col2.getChildren().addAll(inflowTF, cashierTimeTF, 
				avgEateryTimeTF, quitTimeTF, numEaterysTF, 
				numCheckoutsTF, runtimeTF, exitBtn);
	}


	public int getInflow() {
		return inflow;
	}


	public void setInflow(int inflow) {
		this.inflow = inflow;
	}


	public int getCashierTime() {
		return cashierTime;
	}


	public void setCashierTime(int cashierTime) {
		this.cashierTime = cashierTime;
	}


	public int getAvgEateryTime() {
		return avgEateryTime;
	}


	public void setAvgEateryTime(int avgEateryTime) {
		this.avgEateryTime = avgEateryTime;
	}


	public int getQuitTime() {
		return quitTime;
	}


	public void setQuitTime(int quitTime) {
		this.quitTime = quitTime;
	}


	public int getNumEaterys() {
		return numEaterys;
	}


	public void setNumEaterys(int numEaterys) {
		this.numEaterys = numEaterys;
	}


	public int getNumCheckouts() {
		return numCheckouts;
	}


	public void setNumCheckouts(int numCheckouts) {
		this.numCheckouts = numCheckouts;
	}


	public int getRuntime() {
		return runtime;
	}


	public void setRuntime(int runtime) {
		this.runtime = runtime;
	}
}
