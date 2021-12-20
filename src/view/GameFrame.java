package view;


import controller.GameController;
import model.ChessPiece;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;

public class GameFrame extends JFrame {

    public static GameController controller;
    private ChessBoardPanel chessBoardPanel;
    private StatusPanel statusPanel;
    public static int a=0;  //作弊情况判定
    public static int cc;//切换不同的主题

    public StatusPanel getStatusPanel(){
        return statusPanel;
    }

    public GameFrame(int frameSize,String sb1,String sb2) {
        StatusPanel.BlackPlayer = sb1;
        StatusPanel.WhitePlayer = sb2;
        this.setTitle("2021F CS102A Project Reversi");
        this.setLayout(null);

        //获取窗口边框的长度，将这些值加到主窗口大小上，这能使窗口大小和预期相符
        Insets inset = this.getInsets();
        this.setSize(frameSize + inset.left + inset.right, frameSize + inset.top + inset.bottom);

        this.setLocationRelativeTo(null);


        chessBoardPanel = new ChessBoardPanel((int) (this.getWidth() * 0.8), (int) (this.getHeight() * 0.7));
        chessBoardPanel.setLocation((this.getWidth() - chessBoardPanel.getWidth()) / 2, (this.getHeight() - chessBoardPanel.getHeight()) / 3);

        statusPanel = new StatusPanel((int) (this.getWidth() * 0.8), (int) (this.getHeight() * 0.1),sb1,sb2);
        statusPanel.setLocation((this.getWidth() - chessBoardPanel.getWidth()) / 2, 0);
        controller = new GameController(chessBoardPanel, statusPanel);
        controller.setGamePanel(chessBoardPanel);


        this.add(chessBoardPanel);
        this.add(statusPanel);


        JButton restartBtn = new JButton("Restart");
        restartBtn.setSize(120, 50);
        restartBtn.setLocation(150, (this.getHeight() + chessBoardPanel.getHeight()) / 2);
        add(restartBtn);


        //单击监听
        restartBtn.addActionListener(e -> {

            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    controller.getGamePanel().chessGrids[i][j].setChessPiece(null);
                }
            }
            controller.getGamePanel().chessGrids[3][3].setChessPiece(ChessPiece.BLACK);
            controller.getGamePanel().chessGrids[3][4].setChessPiece(ChessPiece.WHITE);
            controller.getGamePanel().chessGrids[4][3].setChessPiece(ChessPiece.WHITE);
            controller.getGamePanel().chessGrids[4][4].setChessPiece(ChessPiece.BLACK);
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    controller.getGamePanel().chessGrids[i][j].repaint();
                }
            }
            controller.setBlackScore(0);
            controller.setWhiteScore(0);
            controller.setCurrentPlayer(ChessPiece.BLACK);
            statusPanel.setPlayerText(controller.getCurrentPlayer().name());
            statusPanel.setScoreText(controller.getBlackScore(), controller.getWhiteScore());
            System.out.println("click restart Btn");
        });

        JButton saveGameBtn = new JButton("Save");
        saveGameBtn.setSize(120, 50);
        saveGameBtn.setLocation(restartBtn.getX() + restartBtn.getWidth() + 30, restartBtn.getY());
        add(saveGameBtn);
        saveGameBtn.addActionListener(e -> {

            System.out.println("clicked Save Btn");
            String filePath = JOptionPane.showInputDialog(this, "input the path here");
            controller.writeDataToFile(filePath);
        });

        //Save Btn

        JButton cheatGameBtn = new JButton("Cheat");

        cheatGameBtn.setSize(120, 50);
        cheatGameBtn.setLocation(680, 200);
        add(cheatGameBtn);
        cheatGameBtn.addActionListener(e -> {
            a=3;
            System.out.println("clicked Cheat Btn");

        });

        //Cheat Btn

        JButton undoBtn = new JButton("Undo");
        undoBtn.setSize(120, 50);
        undoBtn.setLocation(cheatGameBtn.getX(), cheatGameBtn.getY()+cheatGameBtn.getHeight()+20);
        add(undoBtn);
        undoBtn.addActionListener(e -> {
            for(int i=0; i<8; i++){
                for (int j=0; j<8; j++){
                    if(GameFrame.controller.getGamePanel().chessGrids[i][j].getChessPiece()!=null){
                    GameFrame.controller.getGamePanel().chessGrids[i][j].setChessPiece(GameFrame.controller.getGamePanel().chessRecords[i][j].getChessPiece());
                    GameFrame.controller.getGamePanel().chessGrids[i][j].repaint();
                }
                else{
                        GameFrame.controller.getGamePanel().chessGrids[i][j].setChessPiece(null);
                        GameFrame.controller.getGamePanel().chessGrids[i][j].repaint();
                    }}
            }
            controller.swapPlayer();
            System.out.println("clicked Undo Btn");

        });//悔棋模式


        JButton returnBtn = new JButton("Return");
        returnBtn.setSize(120, 50);
        returnBtn.setLocation(saveGameBtn.getX()+restartBtn.getWidth()+30, saveGameBtn.getY());
        add(returnBtn);
        returnBtn.addActionListener(e ->{
            dispose();
           ThirdFrame.timerTest.aa=false;
           new OriginalFrame();

        });



        this.setVisible(true);

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


    }
}
