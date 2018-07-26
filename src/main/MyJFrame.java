package main;

import java.awt.Color;

import javax.swing.JFrame;

public class MyJFrame extends JFrame{

	static MyJFrame window;
    private MyJPanel panel;
//---------------------------------------------------------------------------
    public MyJFrame() {
        super();
        
        panel = new MyJPanel();
        add(panel);
        setSize(500,500);
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