package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

public class MyJPanel extends JPanel {
	
	
//---------------------------------------------------------------------------	
    public MyJPanel() {
       setPreferredSize(new Dimension(500,600));
       Population.build();
       
        
    }
//---------------------------------------------------------------------------
    @Override
    public void update(Graphics g) {
    	repaint();
    }
//---------------------------------------------------------------------------
    public void paintComponent(Graphics g) {
    	
    	//Paint panel, covers old dots from previous gen that did not reach goal
    	//and paints over analytics that pop up in top of panel - no idea why it happens
    	g.setColor(Color.white);
   		g.fillRect(0, 0, 500, 500);
    	
   		//Goal dot painted	
   		g.setColor(Color.black);
   		g.fillRect(250, 20, 5, 5);
   		
   		
   	/*Dots previous spot cleared - deprecated - can be used to add colorful trails
   	 * 
   		g.setColor(Color.white);
    	for(int i=0; i<Population.popSize;i++) {
    		if(Population.population[i].isAlive() && !Population.population[i].reachedGoal()) {
    		g.fillRect(Population.population[i].x,Population.population[i].y, 5, 5);
    		}
    	}
    */
    	
    	//Dots move position
        g.setColor(Color.red);
        for(int i=0; i<Population.popSize;i++) {
        	if(Population.population[i].isAlive() && !Population.population[i].reachedGoal()) {
		    	Population.population[i].movePro();
		        g.fillRect(Population.population[i].x,Population.population[i].y,5,5);
        	}
        	
        	//For death by Wall
        	//To change - comment out lines below and "For Death by wall" section in Dot
    		
        	if(!Population.population[i].isAlive()) {
        		g.fillRect(Population.population[i].x,Population.population[i].y,5,5);	
        	}
        	//End For Death by Wall
        }
        
        //Board reset for next gen/update
        if(Population.checkMoves()) {
        	//Reset board here
        	System.out.println("Resetting space");
        	g.setColor(Color.white);
        	for(int i=0; i<Population.popSize;i++) {
        		if(Population.population[i].isAlive() && !Population.population[i].reachedGoal()) {
        			g.fillRect(Population.population[i].x,Population.population[i].y, 5, 5);
        		}
        	}
        	
        	//Next gen reset
        	try {Thread.sleep(300,0);} catch (InterruptedException e) {e.printStackTrace();}
        	
        	System.out.println("Population reproducing");
        	Population.reproduce();
        	Population.deaths = 0;
        	update(g);
        	
        }
        else update(g);
    }
//---------------------------------------------------------------------------
    public void repaint() {
        super.repaint();
    }
    
}