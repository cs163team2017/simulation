package Simulation;

import java.util.ArrayList;
import java.util.Random;
import javafx.util.Duration;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;

/***************************************************************************
 * Controller Class that links the Model to the View
 * @author Richard Critchlow
 * @version April 2017
 **************************************************************************/
public class Controller {
	
	/**An Instance of the SimButtonPane**/
	private SimButtonPane buttonPn;
	/**An Instance of the SimAnimationPane**/
	private SimAnimationPane animePn;
	/**An Instance of the SimStatsPane**/
	private SimStatsPane statsPn;
	/**An Instance of the SimSettings**/
	private SimSettings settings;
	/**An instance MenuItem**/
	private MenuItem newItm;

	/**Timer that runs slows the simulation**/
	private Timeline timer;
	/**The clock that controls the simulation**/
	private Clock clk;
	/**A random instance that needs to be passed to the PersonProducer**/
	private Random rand;
	/**The Main Queue**/
	private MainQ mainQ;
	/**A collection of eateries**/
	private Eateries eateries;
	/**A collection of cashiers**/
	private Cashiers cashiers;
	/**Produces persons for the simulation**/
	private PersonProducer producer;
	
	/**The speed of the simulation (1 tick/SIM_SPEED (in seconds))**/
	private final double SIM_SPEED = 0.20;
    
	/**Average number of ticks to produce a person**/
	private int numTicks2Person; 
	/**Average number of ticks a person is at an eatery**/
	private int aveEateryTime;
	/**Average number of ticks a person is at a checkout**/
	private int aveCashierTime;
	/**Average number of ticks before a person leaves**/
	private int aveLeaveTime;
	
	/**True if the simulation is currently running**/
	private boolean isRunning;
	
	/***********************************************************************
	 * Constructor for the controller that takes in all the elements from 
	 * the model and the view
	 * @param buttonPn Button Panel
	 * @param animePn Animation Panel
	 * @param statsPn Stats Panel
	 * @param settings Settings Window
	 * @param newItm NewItem MenuItem
	 ***********************************************************************/
	public Controller(SimButtonPane buttonPn, SimAnimationPane animePn, 
					  SimStatsPane statsPn, SimSettings settings, 
					  MenuItem newItm){
		
		this.buttonPn = buttonPn;
		this.animePn = animePn;
		this.statsPn = statsPn;
		this.settings = settings;
		this.newItm = newItm;
		
		numTicks2Person = Stats.inflow; 
	    aveEateryTime = Stats.avgEateryTime;
	    aveCashierTime = Stats.cashierTime;
	    aveLeaveTime = Stats.quitTime;
	    isRunning = false;
	    
		clk = new Clock();
		rand = new Random();        
	}
	
	/***********************************************************************
	 * Sets up the simulation with values and sizes necessary to run
	 ***********************************************************************/
	public void setupSim(){
		//Instantiate simulation elements
		mainQ = new MainQ();
		eateries = new Eateries(rand, mainQ);
		cashiers = new Cashiers();
		producer = new PersonProducer(	rand, 
										eateries, 
										numTicks2Person, 
										aveCashierTime,
										aveEateryTime,
										aveLeaveTime);	
		//clear the clock
		clk.clear();
		//fill the clock
	    for (int i = 0; i < Stats.numEaterys; i++)
	    	eateries.add();
	    for (int i = 0; i < Stats.numCheckouts; i++)
	    	cashiers.add(new Cashier());
	    mainQ.setCashiers(cashiers);
	    clk.add(producer);
	    clk.add(eateries);
	    clk.add(mainQ);
	    clk.add(cashiers);
	    
	    //"ButtonListener"
	    listen();
	    
	}
	
	/***********************************************************************
	 * Starts the simulation with current values stored
	 ***********************************************************************/
	public void startSim(){
		isRunning = true;
		timer = new Timeline(
			new KeyFrame( Duration.seconds(SIM_SPEED),
	            event -> {
	            	clk.tock();
	    	    	statsPn.update();
	    	    	animePn.update(clk.getEateryPeople(), 
	    	    				   clk.getMainQPeople(),
	    	    				   clk.getCashierPeople());
	    	    	if (Stats.currTime == Stats.runtime)
	    		    	timer.stop();
	            } 
			)
	    );
		timer.setCycleCount(Animation.INDEFINITE);
	    timer.play();	    
	}
	
	/***********************************************************************
	 * Pauses the simulation
	 * @throws RuntimeException if simulation is already stopped
	 ***********************************************************************/
	public void stopSim(){
		if (isRunning){
			timer.pause();
			isRunning = false;
		}
		else
			throw new RuntimeException("Can't stop: Already Stopped");
	}
	
	/***********************************************************************
	 * Resumes the simulation after a pause
	 * @throws RuntimeException if simulation is already running
	 ***********************************************************************/
	public void resumeSim(){
		if (!isRunning){
			timer.play();
			isRunning = true;
		}
		else
			throw new RuntimeException("Can't Start: Already Running");
	}
	
	/***********************************************************************
	 * Clears all elements of the simulation to allow for a new simulation
	 ***********************************************************************/
	private void cleanBoard(){
		clk.clear();
		Stats.clear();
		animePn.repaint();
		try {
			animePn.update(clk.getEateryPeople(), 
				   clk.getMainQPeople(),
				   clk.getCashierPeople());
		}
		catch(RuntimeException rte){
			animePn.update(new ArrayList<ArrayList<Person>>(),
						   new ArrayList<Person>(),
						   new ArrayList<Person>());
			
		}
		statsPn.update();
	}
	
	
	/***********************************************************************
	 * "Event Handler" for the GUI
	 ***********************************************************************/
	private void listen(){
		//Start/Restart Button
		buttonPn.getStartBtn().setOnAction(e -> {
			if (buttonPn.getStartBtn().getText().equals("Start")){
				startSim();
				buttonPn.getStartBtn().setText("Restart");
			}
			else{
				timer.stop();
				cleanBoard();
				setupSim();
				buttonPn.getStartBtn().setText("Start");
				
			}
		});
		
		//Pause/Resume Button
		buttonPn.getStopBtn().setOnAction(e -> {
			if (buttonPn.getStopBtn().getText().equals("Pause")){
				stopSim();
				buttonPn.getStopBtn().setText("Resume");
			}
			else{
				resumeSim();
				buttonPn.getStopBtn().setText("Pause");
			}
		});
		
		//Step Button
		buttonPn.getStepBtn().setOnAction(e -> {
			System.out.println("step clicked");			
		});
		
		//New Menu Item
		newItm.setOnAction(e -> {
			settings.display();
		});
		
		//Exit Button (In Settings Menu)
		settings.getExitBtn().setOnAction(e -> {
			settings.closeWindow();
		});
		
		//Save Button (In Settings Menu)
		settings.getSaveBtn().setOnAction(e -> {
			if (settings.isValidInput()){	
				if (timer != null)
					timer.stop();
				settings.closeWindow();
				cleanBoard();
				setupSim();
				buttonPn.getStartBtn().setText("Start");
			}
			else {
				//Shows an alert (JavaFx version of a Common dialog box)
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Input Error");
				alert.setHeaderText(null);
				alert.setContentText("Enter positive integer values");
				alert.showAndWait();
			}
		});
	}
}
