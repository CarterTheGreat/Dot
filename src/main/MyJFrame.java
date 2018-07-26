package main;

import javax.swing.JFrame;

public class MyJFrame extends JFrame{

    private MyJPanel panel;

    public MyJFrame() {
        super();
        
        
        panel = new MyJPanel();
        add(panel);
        setSize(500,500);
        setResizable(false);
        repaint();
    }

    public static void main(String[] args) {
        MyJFrame window = new MyJFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        window.setTitle("Test");
        window.setLocationRelativeTo(null);
    }

    @Override
    public void repaint() {
        super.repaint();
    }

   
}