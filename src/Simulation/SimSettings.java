package Simulation;

//import javafx.application.Application;
import javafx.stage.Modality;
import javafx.stage.Stage;
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
	
	private HBox[] row;
	
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
		
		row = new HBox[8];
	}

	
	public void display(){
		Stage window = new Stage();
		
		//Only Focus this window
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Simulation Settings");
		window.setMinHeight(300);
		window.setMaxWidth(500);
		
		titleLabels();
		titleButtons();
		makeRows();
		
		VBox vertical = new VBox(25);
		for(int i = 0; i < 7; i++)
			vertical.getChildren().add(row[i]);
		
		vertical.getChildren().add(saveBtn);
		vertical.getChildren().add(exitBtn);
		
		Scene settingScene = new Scene(vertical);
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
	
	private void makeRows(){
		row[0].getChildren().addAll(inflowLbl, inflowTF);
		row[1].getChildren().addAll(cashierTimeLbl, cashierTimeTF);
		row[2].getChildren().addAll(avgEateryTimeLbl, avgEateryTimeTF);
		row[3].getChildren().addAll(quitTimeLbl, quitTimeTF);
		row[4].getChildren().addAll(numEaterysLbl, numEaterysTF);
		row[5].getChildren().addAll(numCheckoutsLbl, numCheckoutsTF);
		row[6].getChildren().addAll(runtimeLbl, runtimeTF);
		
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
