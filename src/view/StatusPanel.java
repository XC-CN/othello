package view;

import model.ChessPiece;

import javax.swing.*;
import java.awt.*;

public class  StatusPanel extends JPanel {
    private JLabel playerLabel;
    private JLabel scoreLabel;
    public static String BlackPlayer="";
    public static String WhitePlayer="";
    public StatusPanel(int width, int height,String blackPlayer,String whitePlayer) {
        BlackPlayer=blackPlayer;
        WhitePlayer=whitePlayer;
        this.setSize(width, height);
        this.setLayout(null);
        this.setVisible(true);
        this.playerLabel = new JLabel();
        this.playerLabel.setLocation(0, 10);
        this.playerLabel.setSize((int) (width * 0.4), height);
        this.playerLabel.setFont(new Font("Calibri", Font.BOLD, 30));

        this.setPlayerText(BlackPlayer);
        add(playerLabel);

        this.scoreLabel = new JLabel();
        this.scoreLabel.setLocation((int) (width * 0.4), 10);
        this.scoreLabel.setSize((int) (width * 0.5), height);
        this.scoreLabel.setFont(new Font("Calibri", Font.ITALIC, 25));
        this.setScoreText(2,2);
        add(scoreLabel);

    }
    public void setScoreText(int black, int white) {
        this.scoreLabel.setText(BlackPlayer+":"+black+"   "+WhitePlayer+":"+white);
    }

    public void setPlayerText(String playerText) {
        this.playerLabel.setText(playerText + "'s turn");
    }
    
}
