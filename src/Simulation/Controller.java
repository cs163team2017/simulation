package Simulation;

//import java.time.Duration;
import java.util.Random;
import javafx.util.Duration;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;


public class Controller {
	
	/***********************************************************************
	 * Timeline is the javaFX version of a swingtimer
	 * Used to control the clock
	 **********************************************************************/
	private SimAnimationPane animePn;
	private SimStatsPane statsPn;
	private Timeline timer;
	private Clock clk;
	private Random rand;
	private MainQ mainQ;
	private Eateries eateries;
	private Cashiers cashiers;
	private PersonProducer producer;
	private final double SIM_SPEED = 0.5;  // 1 tick = this in Seconds
    
	private int numTicks2Person; 
	private int aveEateryTime;
	private int aveCashierTime;
	private int aveLeaveTime;
	
	private boolean isRunning;
	
	public Controller(SimAnimationPane animePn, SimStatsPane statsPn){
		this.animePn = animePn;
		this.statsPn = statsPn;
		numTicks2Person = Stats.inflow; 
	    aveEateryTime = Stats.avgEateryTime;
	    aveCashierTime = Stats.cashierTime;
	    aveLeaveTime = Stats.quitTime;
	    isRunning = false;
	    
		clk = new Clock();
		rand = new Random();
		mainQ = new MainQ();
		eateries = new Eateries(rand, mainQ);
		cashiers = new Cashiers();
		producer = new PersonProducer(	rand, 
										eateries, 
										numTicks2Person, 
										aveCashierTime,
										aveEateryTime,
										aveLeaveTime);	
        
	}
	
	public void setupSim(){
	    for (int i = 0; i < Stats.numEaterys; i++)
	    	eateries.add();
	    for (int i = 0; i < Stats.numCheckouts; i++)
	    	cashiers.add(new Cashier());
	    
	    mainQ.setCashiers(cashiers);
	    clk.add(producer);
	    clk.add(eateries);
	    clk.add(mainQ);
	    clk.add(cashiers);
	}
	
	public void startSim(){
	   timer = new Timeline(
	        new KeyFrame(
	            Duration.seconds(SIM_SPEED),
	            event -> {
 	    	    	clk.tock();
	    	    	statsPn.repaint();
	    	    	animePn.repaint();
	    	    	if (Stats.currTime == Stats.runtime)
	    		    	timer.stop();
	            } 
	        )
	    );
	    timer.setCycleCount(Animation.INDEFINITE);
	    timer.play();
	    isRunning = true;	    
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
	
}
