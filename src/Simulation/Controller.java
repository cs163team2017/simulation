package Simulation;

import java.util.Random;
import javafx.util.Duration;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.MenuItem;


public class Controller {
	
	/***********************************************************************
	 * Timeline is the javaFX version of a swingtimer
	 * Used to control the clock
	 **********************************************************************/
	private SimButtonPane buttonPn;
	private SimAnimationPane animePn;
	private SimStatsPane statsPn;
	private SimSettings settings;
	private MenuItem newItm;
	
	private Timeline timer;
	private Clock clk;
	private Random rand;
	private MainQ mainQ;
	private Eateries eateries;
	private Cashiers cashiers;
	private PersonProducer producer;
	private final double SIM_SPEED = 0.1;  // 1 tick = this in Seconds
    
	private int numTicks2Person; 
	private int aveEateryTime;
	private int aveCashierTime;
	private int aveLeaveTime;
	
	private boolean isRunning;
	
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
	
	public void setupSim(){
		mainQ = new MainQ();
		eateries = new Eateries(rand, mainQ);
		cashiers = new Cashiers();
		producer = new PersonProducer(	rand, 
										eateries, 
										numTicks2Person, 
										aveCashierTime,
										aveEateryTime,
										aveLeaveTime);	
		
		clk.clear();
	    for (int i = 0; i < Stats.numEaterys; i++)
	    	eateries.add();
	    for (int i = 0; i < Stats.numCheckouts; i++)
	    	cashiers.add(new Cashier());
	    
	    mainQ.setCashiers(cashiers);
	    clk.add(producer);
	    clk.add(eateries);
	    clk.add(mainQ);
	    clk.add(cashiers);
	    listen();
	    
	}
	
	public void startSim(){
		isRunning = true;
		timer = new Timeline(
			new KeyFrame(
				Duration.seconds(SIM_SPEED),
	            event -> {
	            	clk.tock();
	    	    	statsPn.update();
	    	    	animePn.update();
	    	    	if (Stats.currTime == Stats.runtime)
	    		    	timer.stop();
	            } 
			)
	    );
		timer.setCycleCount(Animation.INDEFINITE);
	    timer.play();	    
	}
	
	public void stopSim(){
		if (isRunning){
			timer.pause();
			isRunning = false;
		}
		else
			System.out.println("Controller - Should have pause sim and this was never reached");//FIXME
	}
	
	public void resumeSim(){
		if (!isRunning){
			timer.play();
			isRunning = true;
		}
		else
			System.out.println("Controller - Should have resumed sim and this was never reached");//FIXME
	}
	
	private void cleanBoard(){
		clk.clear();
		Stats.clear();
		animePn.repaint();
		animePn.update();
		statsPn.update();
	}
	
	
	/***********************************************************************
	 * "Event Handler" for the GUI
	 ***********************************************************************/
	private void listen(){
		//ButtonPane listeners
		buttonPn.getStartBtn().setOnAction(e -> {
			if (buttonPn.getStartBtn().getText().equals("Start")){
				startSim();
				buttonPn.getStartBtn().setText("Restart");
				System.out.println(Stats.pplAtEatery);
			}
			else{
				timer.stop();
				cleanBoard();
				setupSim();
				buttonPn.getStartBtn().setText("Start");
				
			}
		});
		
		buttonPn.getStopBtn().setOnAction(e -> {
			if (buttonPn.getStopBtn().getText().equals("Pause")){
				stopSim();
				buttonPn.getStopBtn().setText("Resume");
				System.out.println(Stats.pplAtEatery);
				System.out.println("" + Stats.pplAtEatery.size());
			}
			else{
				resumeSim();
				buttonPn.getStopBtn().setText("Pause");
			}
		});
		
		buttonPn.getStepBtn().setOnAction(e -> {
			System.out.println("step clicked");
		});
		
		newItm.setOnAction(e -> {
			settings.display();
		});
		
		settings.getExitBtn().setOnAction(e -> {
			settings.closeWindow();
		});
		
		settings.getSaveBtn().setOnAction(e -> {
			settings.saveSettings();
			if (settings.isValidInput()){	
				if (timer != null)
					timer.stop();
				settings.closeWindow();
				cleanBoard();
				setupSim();
				buttonPn.getStartBtn().setText("Start");
			}
			else 
				System.out.println("This is a negitive number error");//FIXME
		});
	}
	
}
