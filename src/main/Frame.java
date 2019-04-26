package main;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Frame extends JFrame{

	private static final long serialVersionUID = 2;
	
	static JLabel label = new JLabel();;
	static Frame window;
	static int frameX = 600;
	static int frameY = 700;
	
	private OptionsPanel optionsPanel;
    
//---------------------------------------------------------------------------
   public Frame() {
        super();
        
        
        label.setText("Dot Evolution");
        add(label, BorderLayout.SOUTH);
        optionsPanel = new OptionsPanel();
        add(optionsPanel);
        setSize(frameX,frameY);
        setResizable(false);
        setBackground(Color.white);
       
    }
//---------------------------------------------------------------------------	
    public static void main(String[] args) {
        window = new Frame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        window.setTitle("Dot Evolution");
        window.setLocationRelativeTo(null);
    }
//---------------------------------------------------------------------------
}