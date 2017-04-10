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
	
	private Stage window;

	public SimSettings(){
		
		col1 = new VBox(25);
		col2 = new VBox(25);

	}

	
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
		inflowTF = new TextField("20");
		cashierTimeTF = new TextField("10");
		avgEateryTimeTF = new TextField("60");
		quitTimeTF = new TextField("900");
		numEaterysTF = new TextField("2");
		numCheckoutsTF = new TextField("1");
		runtimeTF = new TextField("10000");
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
