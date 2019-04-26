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
	
	static int panelX = Frame.frameX;
	static int panelY = Frame.frameY-100;
	
	GridBagConstraints c = new GridBagConstraints();	
	
	int popMin = 1000;
	int popMax = 10000;
	
	//Inputs/info
	JTextField dotYInput, dotXInput, popSizeInput;
	String setDotX, setDotY, setPopSize;
	
	boolean setX,setY,setPop;
	
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
	
		
		
	}

	boolean read(){
		
		System.out.println("reading");
		setDotX = dotXInput.getText();
		setDotY = dotYInput.getText();
		setPopSize = popSizeInput.getText();
		System.out.println("x: "+setDotX+" y: "+setDotY+" size: "+setPopSize);
		
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
		
		
		if(setX && setY && setPop) {
			return true;
		}else {
			Frame.label.setText("<html><font color='red'>Incorrect value input</font></html>");
			//Frame.label.setText("Incorrect value in");
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
	        Frame.window.add(evolutionPanel);
		}
	}	
}
