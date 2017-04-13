package Simulation;

import java.time.Duration;
import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


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
    
	private int numTicks2Person; 
	private int aveEateryTime;
	private int aveCashierTime;
	private int aveLeaveTime;
	
	public Controller(SimAnimationPane animePn, SimStatsPane statsPn){
		this.animePn = animePn;
		this.statsPn = statsPn;
		numTicks2Person = Stats.inflow; 
	    aveEateryTime = Stats.avgEateryTime;
	    aveCashierTime = Stats.cashierTime;
	    aveLeaveTime = Stats.quitTime;
	    
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
	
	public void startSim(){
	  
	    for (int i = 0; i < Stats.numEaterys; i++)
	    	eateries.add();
//	    for (int i = 0; i < Stats.numCheckouts; i++)
//	    	cashiers.add();
	    
	    mainQ.setCashiers(cashiers);
	    clk.add(producer);
	    clk.add(eateries);
	    clk.add(mainQ);
	    clk.add(cashiers);
	    for (int i = 0; i < Stats.runtime; i++){
	    	clk.tock();
	    	statsPn.repaint();
	    	animePn.repaint();
	    }
	}
	
//	    private Timeline runLater(Duration delay, Runnable action) {
//	        Timeline timeline = new Timeline(new KeyFrame(delay, ae -> action.run()));
//	        timeline.play();
//	        return timeline;
//	}
	
	
}
