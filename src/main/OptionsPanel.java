package main;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class OptionsPanel extends JPanel implements ActionListener{
	
	private EvolutionPanel evolutionPanel;
	
	static int panelX = MyJFrame.frameX;
	static int panelY = MyJFrame.frameY-100;
	
	GridBagConstraints c = new GridBagConstraints();	
	
	int popMin = 1000;
	int popMax = 10000;
	
	//Inputs/info
	JTextField dotYInput, dotXInput, popSizeInput, obstacleCountInput;
	String setDotX, setDotY, setPopSize, setObstacleCount;
	
	boolean setX,setY,setPop,setOb;
	
	//Obstacles--------
	
	
	public OptionsPanel() {
		setPreferredSize(new Dimension(panelX,panelY));
		setLayout(new GridBagLayout());
		
		build();
	}
	
	void build(){
		
		JButton run = new JButton("Run");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = .5;
		c.gridx = 2;
		c.gridy = 4;
		run.addActionListener(this);
		add(run, c);
	//Pop Size-------------------------------------
		JTextArea popSizeText = new JTextArea();
		popSizeText.setText("Population Size Between "+ popMin+ " - "+popMax+" :");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = .5;
		c.gridx = 0;
		c.gridy = 0;
		add(popSizeText, c);
		
		popSizeInput = new JTextField();
		popSizeInput.setText("1500");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = .5;
		c.gridx = 2;
		c.gridy = 0;
		add(popSizeInput, c);
	//Dot x,y--------------------------------------
		JTextArea dotSpawnText = new JTextArea();
		dotSpawnText.setText("Dot Spawn X,Y Between "+ 0+" - "+EvolutionPanel.panelX+" :");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = .5;
		c.gridx = 0;
		c.gridy = 1;
		add(dotSpawnText, c);
		
		dotXInput = new JTextField();
		dotXInput.setText("300");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = .5;
		c.gridx = 1;
		c.gridy = 1;
		add(dotXInput, c);
		
		dotYInput = new JTextField();
		dotYInput.setText("450");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = .5;
		c.gridx = 2;
		c.gridy = 1;
		add(dotYInput, c);
	//Obstacles-------------------------------------
		JTextArea obstacleCountText = new JTextArea();
		obstacleCountText.setText("Obstacle Count Between 0 - 10 :");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = .5;
		c.gridx = 0;
		c.gridy = 2;
		add(obstacleCountText, c);
		
		obstacleCountInput = new JTextField();
		obstacleCountInput.setText("1");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = .5;
		c.gridx = 2;
		c.gridy = 2;
		add(obstacleCountInput, c);
		
		
	}

	boolean read(){
		
		System.out.println("reading");
		setDotX = dotXInput.getText();
		setDotY = dotYInput.getText();
		setPopSize = popSizeInput.getText();
		setObstacleCount = obstacleCountInput.getText();
		System.out.println("x: "+setDotX+" y: "+setDotY+" size: "+setPopSize);
		
		try {	
			if(Integer.parseInt(setObstacleCount) >= 0 && Integer.parseInt(setObstacleCount) <= 10) {
				EvolutionPanel.obstacleCount = Integer.parseInt(setObstacleCount);   
			 	setOb = true;
			}
		} catch (NumberFormatException e) {
	        System.out.println("obstacle count out of bounds");;
	    }
		try {	
			if(Integer.parseInt(setDotX) >-1 && Integer.parseInt(setDotX) < EvolutionPanel.panelX+1) {
				Population.x = Integer.parseInt(setDotX);
				setX = true;
			}
		} catch (NumberFormatException e) {
	        System.out.println("setDotX out of bounds");;
	    }
		
		
		try {
			if(Integer.parseInt(setDotY) >-1 && Integer.parseInt(setDotY) < EvolutionPanel.panelY+1) {
				Population.y = Integer.parseInt(setDotY);
				setY = true;
			}
		} catch (NumberFormatException e) {
	        System.out.println("setDotY out of bounds");;
	    }
		
		
		try {	
			if(Integer.parseInt(setPopSize) >popMin-1 && Integer.parseInt(setPopSize) < popMax+1) {
				Population.popSize = Integer.parseInt(setPopSize);
				setPop = true;
			}
		} catch (NumberFormatException e) {
	        System.out.println("setDotY out of bounds");;
	    }
		
		
		if(setX && setY && setPop && setOb) {
			return true;
		}else {
			MyJFrame.label.setText("<html><font color='red'>Incorrect value input</font></html>");
			//MyJFrame.label.setText("Incorrect value in");
			return false;
		}
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Action heard");
		if(read()) {
			removeAll();
			setVisible(false);					
			evolutionPanel = new EvolutionPanel();
	        MyJFrame.window.add(evolutionPanel);
		}
	}	
}
