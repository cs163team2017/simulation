package Simulation;



import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
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
	
	private Label[] mainQLbl;
	private Label[] eaterLbl; 
	private Label[] checkoutLbl;
	private Label[] inMainQLbl;
	private Label[] inEaterLbl; 
	private Label[] inCheckoutLbl;
	
	private int pAtMainQ;
	private int pAtEatery;
	private int pAtCheckout;
	
	private Label inflowLbl;
	private Label cashierTimeLbl;
	private Label avgEateryTimeLbl;
	private Label quitTimeLbl;
	private Label currStepLbl;
	
	private SimSettings settings;
	
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
		mainPn.setCenter(createSimPn(Stats.numEaterys, Stats.numCheckouts));
		
		settings = new SimSettings();
		listen();
	
		Scene mainScene = new Scene(mainPn, 800, 600);
		primaryStage.setScene(mainScene);
		primaryStage.show();
		
		primaryStage.setOnCloseRequest(e -> {
	        Platform.exit();
	        System.exit(0);
	    });
	}
	
	/******************************************************************
	 * Creates a bottom Pane
	 * @return HBox Pane
	 ******************************************************************/
	private HBox createButtonPn(){
		HBox buttonPn = new HBox(50);
		buttonPn.setAlignment(Pos.TOP_CENTER);
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
		HBox statsPn = new HBox(25);
		statsPn.setAlignment(Pos.BOTTOM_CENTER);
		
		inflowLbl = new Label("Seconds between customers: " + Stats.inflow);
		cashierTimeLbl = new Label("Cashier Time: " + Stats.cashierTime);
		avgEateryTimeLbl = new Label("Avg Time at Eatery: " + Stats.avgEateryTime);
		quitTimeLbl = new Label("Time before someone leaves: " + Stats.quitTime);
		currStepLbl = new Label("Current time: " + "xxxxx/" + Stats.runtime);
		
		statsPn.getChildren().addAll(inflowLbl, cashierTimeLbl, avgEateryTimeLbl,
				quitTimeLbl, currStepLbl);
		
		return statsPn;
	}
	
	private HBox createSimPn(int eaterys, int checkouts){
		mainQLbl = new Label[1];
		eaterLbl = new Label[eaterys]; 
		checkoutLbl = new Label[checkouts];
		inMainQLbl = new Label[1];
		inEaterLbl = new Label[eaterys]; 
		inCheckoutLbl = new Label[checkouts];
		
		HBox simPn = new HBox(25);
		
		VBox leftPn = new VBox(25);
		HBox centerPn = new HBox(25);
		VBox rightPn = new VBox(25);
		
		for (int i = 0; i < eaterys; i++)
			leftPn.getChildren().add(makeEateryRow(i));
		
		for (int i = 0; i < checkouts; i++)
			rightPn.getChildren().add(makeCheckoutRow(i));
		
		centerPn.getChildren().addAll(makeMainQRow(0));
		
		simPn.setAlignment(Pos.CENTER);
		simPn.getChildren().addAll(leftPn, centerPn, rightPn); 
		
		return simPn;
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
