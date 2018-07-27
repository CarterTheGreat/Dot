package main;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class MyJFrame extends JFrame{

	static JLabel label;
	static MyJFrame window;
    private MyJPanel panel;
//---------------------------------------------------------------------------
    public MyJFrame() {
        super();
        
        panel = new MyJPanel();
        add(panel);
        label = new JLabel();
        add(label, BorderLayout.SOUTH);
        //label.set
        setSize(500,600);
        setResizable(false);
        setBackground(Color.white);
        repaint();
    }
//---------------------------------------------------------------------------	
    public static void main(String[] args) {
        window = new MyJFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        window.setTitle("Test");
        window.setLocationRelativeTo(null);
    }
//---------------------------------------------------------------------------
    @Override
    public void repaint() {
        super.repaint();
    }

}