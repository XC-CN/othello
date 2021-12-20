package view;


import javax.swing.*;
import java.awt.*;


public class bgPanel extends JPanel{
    public bgPanel(int width, int height) {
        this.setVisible(true);
        this.setFocusable(true);
        this.setLayout(null);
        this.setBackground(Color.WHITE);
//        int length = Math.min(width, height);
        this.setSize(width,height);
        repaint();
    }

//    @Override
//    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        ImageIcon imageGif = new ImageIcon("bg.jpg") ;
//        g.drawImage(imageGif.getImage(), 0, 0, getSize().width, getSize().height, this);
//
//    }
}
