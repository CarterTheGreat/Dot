package main;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class MyJFrame extends JFrame{

	private static final long serialVersionUID = 1;
	
	static JLabel label = new JLabel();;
	static MyJFrame window;
		static int frameX = 400;
		static int frameY = 600;
	
    private MyJPanel panel;
//---------------------------------------------------------------------------
    public MyJFrame() {
        super();
        
        
        label.setText("Dot Evolution | Generation 0");
        add(label, BorderLayout.SOUTH);
        panel = new MyJPanel();
        add(panel);
        setSize(frameX,frameY);
        setResizable(false);
        setBackground(Color.white);
       
    }
//---------------------------------------------------------------------------	
    public static void main(String[] args) {
        window = new MyJFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        window.setTitle("Dot Evolution");
        window.setLocationRelativeTo(null);
    }
//---------------------------------------------------------------------------
}