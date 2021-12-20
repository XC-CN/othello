package view;

import javax.swing.*;
import java.awt.*;

public class bgPanel2 extends JPanel{
    public bgPanel2(int width, int height) {
        this.setVisible(true);
        this.setFocusable(true);
        this.setLayout(null);
        this.setSize(width,height);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon imageGif = new ImageIcon("bg3.jpg") ;
        g.drawImage(imageGif.getImage(), 0, 0, getSize().width, getSize().height, this);

    }
}
