package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

public class MyJPanel extends JPanel {
	
//---------------------------------------------------------------------------	
    public MyJPanel() {
       setPreferredSize(new Dimension(500,500));
       Population.build();
        
    }
//---------------------------------------------------------------------------
    @Override
    public void update(Graphics g) {
    	
    	repaint();
    	
    }
//---------------------------------------------------------------------------
    public void paint(Graphics g) {
    	
   		//Goal dot painted	
   		g.setColor(Color.black);
   		g.fillRect(250, 20, 5, 5);
   		
   		
   		//Dots previous spot cleared
    	g.setColor(Color.white);
    	for(int i=0; i<Population.popSize;i++) {
    		if(Population.population[i].isAlive() && !Population.population[i].reachedGoal()) {
    		g.fillRect(Population.population[i].x,Population.population[i].y, 5, 5);
    		}
    	}
    	
    	//Dots move
        g.setColor(Color.red);
        for(int i=0; i<Population.popSize;i++) {
        	if(Population.population[i].isAlive() && !Population.population[i].reachedGoal()) {
	    	Population.population[i].movePro();
	        g.fillRect(Population.population[i].x,Population.population[i].y,5,5);
        	}
        }
        
        //Board reset/update
        if(Population.checkMoves()) {
        	//Reset board here
        	System.out.println("Resetting space");
        	Population.deaths = 0;
        	Population.goals = 0;
        	MyJFrame.window.setForeground(Color.white); System.out.println("Resetting foreground");
        	
        	
        	
        	
        	try {Thread.sleep(300,0);} catch (InterruptedException e) {e.printStackTrace();}
        	
        	//Population.purge();
        	Population.reproduce();
        	
        	update(g);
        }
        else update(g);
    }
//---------------------------------------------------------------------------
    public void repaint() {
        super.repaint();
    }
    
}