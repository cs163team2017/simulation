package Simulation;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class SimMainStage extends Application {

	//Declaring Variables
	private Button startBtn;
	private Button stopBtn;
	private Button stepBtn;
	private Button settingBtn; //REMOVE shouldn't be a button
	
	private Label mainQLbl;
	private Label eaterLbl; //array for future?
	private Label checkoutLbl;
	private Label inMainQLbl;
	private Label inEaterLbl; //array for future?
	private Label inCheckoutLbl;
	
	private int pAtMainQ;
	private int pAtEatery;
	private int pAtCheckout;
	
	SimSettings settings;
	
	
	
	public static void main (String[] args){
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		//Initializing Variables
		primaryStage.setTitle("Food Court");
		
		BorderPane  mainPn = new BorderPane();
		mainPn.setTop(createButtonPn());
		mainPn.setBottom(createStatsPn());
		
		settings = new SimSettings();
		listen();
	
		Scene mainScene = new Scene(mainPn, 800, 600);
		primaryStage.setScene(mainScene);
		primaryStage.show();
	}
	
	
	/******************************************************************
	 * Creates a bottom Pane
	 * @return HBox Pane
	 ******************************************************************/
	private HBox createButtonPn(){
		HBox buttonPn = new HBox();
		startBtn = new Button("Start");
		stopBtn = new Button("Stop");
		stepBtn = new Button("Step");
		settingBtn = new Button("Settings");
		buttonPn.getChildren().addAll(startBtn, stopBtn, stepBtn, settingBtn);
		
		
		return buttonPn;
	}
	
	/******************************************************************
	 * 
	 * @return
	 ******************************************************************/
	private HBox createStatsPn(){
		HBox statsPn = new HBox();
		mainQLbl = new Label("In Q: ");
		eaterLbl = new Label("At Eatery: ");
		checkoutLbl = new Label("At checkout: ");
		inMainQLbl = new Label("" + pAtMainQ);
		inEaterLbl = new Label("" + pAtEatery);
		inCheckoutLbl = new Label("" + pAtCheckout);
		
		statsPn.getChildren().addAll(mainQLbl, inMainQLbl, eaterLbl, 
				inEaterLbl, checkoutLbl, inCheckoutLbl);
		
		
		return statsPn;
	}
	
	/******************************************************************
	 * "Action Listener" for the GUI
	 ******************************************************************/
	private void listen(){
		
		//Using lambda functions for action listeners
		startBtn.setOnAction(e -> {
			System.out.println("start clicked");
		});
		
		stopBtn.setOnAction(e -> {
			System.out.println("stop clicked");
		});
		
		stepBtn.setOnAction(e -> {
			System.out.println("step clicked");
		});
		
		settingBtn.setOnAction(e -> {
			settings.display();
		});
		
	}




}
