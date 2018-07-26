package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

public class MyJPanel extends JPanel {
	int popSize = 50;
	
	Dot[] population = new Dot[popSize];
	
	void build(){
		for(int i=0; i<popSize;i++) {
			population[i] = new Dot(250,250);
			
		}
	}
	
    public MyJPanel() {
        setPreferredSize(new Dimension(200,200));
        build();
    }

    @Override
    public void update(Graphics g) {
    	
    	
    	repaint();
    }

    @Override
    public void paint(Graphics g) {
    	
    	g.setColor(Color.black);
    	g.fillRect(250, 20, 5, 5);
    	
    	g.setColor(Color.white);
    	for(int i=0; i<popSize;i++) {
    		g.fillRect(population[i].x,population[i].y, 5, 5);
    	}
    	
        g.setColor(Color.red);
        
        for(int i=0; i<popSize;i++) {
	    	population[i].movePro();
	        g.fillRect(population[i].x,population[i].y,5,5);
        }

        update(g);
    }

    public void repaint() {
        super.repaint();
    }
    

}