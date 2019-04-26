package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

public class EvolutionPanel extends JPanel {
	
	private static final long serialVersionUID = 1;
	
	
	static int panelX = Frame.frameX;
	static int panelY = Frame.frameY-100;
	
	static int goalX = panelX/2;
	static int goalY = 20;	
	
//---------------------------------------------------------------------------	
    public EvolutionPanel() {
       System.out.println("Evolution panel built");
       setPreferredSize(new Dimension(panelX,panelY));
       setVisible(true);
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
    	g.fillRect(0, 0, 1600, 700);
    	
   		//Goal dot painted	
   		g.setColor(Color.black);
   		g.fillRect(goalX, goalY, 5, 5);
   		
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
		        g.fillOval(Population.population[i].x,Population.population[i].y,5,5);
        	}
        	
        	//For death by Wall
        	//To change - comment out lines below and "For Death by wall" section in Dot
    		/*
        	if(!Population.population[i].isAlive()) {
        		g.fillRect(Population.population[i].x,Population.population[i].y,5,5);
        		Population.population[i].fitMoves = Dot.movesMax;
        	}
        	//End For Death by Wall
        	*/
        }
        
        //Board reset for next gen/update
        if(Population.checkMoves()) {
        	//Reset board here
        	
        	System.out.println("Resetting space");
        	g.setColor(Color.white);
        	for(int i=0; i<Population.popSize;i++) {
        		if(Population.population[i].isAlive() && !Population.population[i].reachedGoal()) {
        			g.fillOval(Population.population[i].x,Population.population[i].y, 5, 5);
        		}
        	}
        	
        	//Next gen reset
        	try {Thread.sleep(300,0);} catch (InterruptedException e) {e.printStackTrace();}
        	
        	System.out.println("Population reproducing");
        	Population.reproduce();
        	Population.totalMoves = 0;
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