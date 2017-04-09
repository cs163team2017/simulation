package Simulation;



import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
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
	
	private Label mainQLbl;
	private Label eaterLbl; //array for future?
	private Label checkoutLbl;
	private Label inMainQLbl;
	private Label inEaterLbl; //array for future?
	private Label inCheckoutLbl;
	
	private int pAtMainQ;
	private int pAtEatery;
	private int pAtCheckout;
	
	
	private int inflow;
	private int cashierTime;
	private int avgEateryTime;
	private int quitTime;
	private int numEaterys;
	private int numCheckouts;
	private int runtime;
	
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
		mainPn.setCenter(createSimPn(numEaterys, numCheckouts));
		
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
		return statsPn;
	}
	
	private HBox createSimPn(int eaterys, int checkouts){
		HBox simPn = new HBox(25);
		
		VBox leftPn = new VBox(25);
		HBox centerPn = new HBox(25);
		VBox rightPn = new VBox(25);
		
		//for (int i = 0; i < eaterys; i++)
			leftPn.getChildren().add(makeEateryRow(2));
		
		//for (int i = 0; i < checkouts; i++)
			rightPn.getChildren().add(makeCheckoutRow(2));
		
		centerPn.getChildren().addAll(makeMainQRow(1));
		
		simPn.setAlignment(Pos.CENTER);
		simPn.getChildren().addAll(leftPn, centerPn, rightPn); 
		
		return simPn;
	}
	
	private HBox makeEateryRow(int n){
		HBox row = new HBox(25);
		inEaterLbl = new Label("" + pAtEatery);
		eaterLbl = new Label("Num people at Eatery " + n + " :");
		row.getChildren().addAll(eaterLbl, inEaterLbl);
		
		return row;
	}
	
	private HBox makeCheckoutRow(int n){
		HBox row = new HBox(25);
		checkoutLbl = new Label("Num people at Checkout " + n + " :");
		inCheckoutLbl = new Label("" + pAtCheckout);
		row.getChildren().addAll(checkoutLbl, inCheckoutLbl);
		return row;
	}
	
	private HBox makeMainQRow (int n){
		HBox row = new HBox(25);
		mainQLbl = new Label("Num people at MainQ " + n + " :");
		inMainQLbl = new Label("" + pAtMainQ);
		row.getChildren().addAll(mainQLbl, inMainQLbl);
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
			//copy settings of simulation over
			inflow = settings.getInflow();
			cashierTime = settings.getCashierTime();
			avgEateryTime = settings.getAvgEateryTime();
			quitTime = settings.getQuitTime();
			numEaterys = settings.getNumEaterys();
			numCheckouts = settings.getNumCheckouts();
			runtime = settings.getRuntime();
		});
		
	}

}
