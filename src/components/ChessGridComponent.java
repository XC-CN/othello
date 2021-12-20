package components;

import model.*;
import rankinglist.Player;
import rankinglist.RankingSystem;
import view.GameFrame;
import view.StatusPanel;
import view.TimerTest;

import javax.swing.*;
import java.awt.*;

public class ChessGridComponent extends BasicComponent {
    public static int chessSize;
    public static int gridSize;
    public static Color gridColor = new Color(255, 150, 50);
    public ChessPiece chessPiece;
    private int row;
    private int col;
    public ChessGridComponent(int row, int col) {
        this.setSize(gridSize, gridSize);

        this.row = row;
        this.col = col;
    }

    @Override
    public void onMouseClicked() throws InterruptedException {
        System.out.printf("%s clicked (%d, %d)\n", GameFrame.controller.getCurrentPlayer(), row, col);
        //todo: complete mouse click method
        if (GameFrame.a == 0) {
            if (this.chessPiece == null || this.chessPiece == ChessPiece.PINK) {
                for(int i=0; i<8; i++){
                    for (int j=0; j<8; j++) {
                        if (GameFrame.controller.getGamePanel().chessGrids[i][j].getChessPiece() != null) {//避免空指针
                            GameFrame.controller.getGamePanel().chessRecords[i][j].setChessPiece(GameFrame.controller.getGamePanel().chessGrids[i][j].getChessPiece());
                        }
                        else{
                            GameFrame.controller.getGamePanel().chessRecords[i][j].setChessPiece(null);
                        }
                    }
                }//存储棋盘数据
                if (GameFrame.controller.canClick(row, col)) {//判断并翻转
                    this.chessPiece = GameFrame.controller.getCurrentPlayer();//在当前位置落子

                    reTips();//把上一个人的提示方块恢复回空白

                    //PVP模式 胜利条件
                    if (checkWin()) {
                        int countblack = 0;
                        int countwhite = 0;
                        for (int i = 0; i < 8; i++) {
                            for (int j = 0; j < 8; j++) {
                                if (GameFrame.controller.getGamePanel().chessGrids[i][j].getChessPiece() == ChessPiece.BLACK) {
                                    countblack++;
                                }
                                if (GameFrame.controller.getGamePanel().chessGrids[i][j].getChessPiece() == ChessPiece.WHITE) {
                                    countwhite++;
                                }
                            }
                        }
                        if (countwhite > countblack) {
                            JFrame a = new JFrame();
                            a.setLocationRelativeTo(null);
                            a.setSize(200, 100);
                            JLabel b = new JLabel();
                            b.setLocation(30, 20);
                            b.setText("            "+StatusPanel.WhitePlayer+" wins!");
                            a.add(b);
                            a.setVisible(true);
                            //rankinglist添加
                            RankingSystem.addWinGames(StatusPanel.WhitePlayer);
                            RankingSystem.addLoseGames(StatusPanel.BlackPlayer);
                            RankingSystem.calcWinRate(StatusPanel.WhitePlayer);
                            RankingSystem.calcWinRate(StatusPanel.BlackPlayer);
                            //
                        } else if (countwhite < countblack) {
                            JFrame a = new JFrame();
                            a.setLocationRelativeTo(null);
                            a.setSize(200, 100);
                            JLabel b = new JLabel();
                            b.setLocation(30, 20);
                            b.setText("           "+StatusPanel.BlackPlayer+" wins!");
                            a.add(b);
                            a.setVisible(true);
                            //rankinglist添加
                            RankingSystem.addWinGames(StatusPanel.BlackPlayer);
                            RankingSystem.addLoseGames(StatusPanel.WhitePlayer);
                            RankingSystem.calcWinRate(StatusPanel.WhitePlayer);
                            RankingSystem.calcWinRate(StatusPanel.BlackPlayer);
                            //
                        } else {
                            JFrame a = new JFrame();
                            a.setLocationRelativeTo(null);
                            a.setSize(200, 100);
                            JLabel b = new JLabel();
                            b.setLocation(30, 20);
                            b.setText("         Nobody win!");
                            a.add(b);
                            a.setVisible(true);
                        }


                    }//检查下一个人是否能继续下子 判断胜负
                    GameFrame.controller.swapPlayer();//下一回合

                    tips();//显示上一个人的提示方块
                    TimerTest.time=510;
                }
                repaint();//显示落子图案

            }
        }//双人玩家模式

        if (GameFrame.a == 1) {
            if (this.chessPiece == null || this.chessPiece == ChessPiece.PINK) {

                if (GameFrame.controller.canClick(row, col)) {
                    this.chessPiece = GameFrame.controller.getCurrentPlayer();
                    repaint();
                    reTips();
                    GameFrame.controller.swapPlayer();

                    boolean check = false;


                    boolean bb=false;
                    for (int i = 0; i <8; i++) {
                        for (int j = 0; j < 8; j++) {

                            if (GameFrame.controller.canClick(i, j)) {
                                GameFrame.controller.getGamePanel().chessGrids[i][j].setChessPiece(GameFrame.controller.getCurrentPlayer());
                                GameFrame.controller.getGamePanel().chessGrids[i][j].repaint();//机器下子
                                check = true;
                                break;
                            }

                        }
                       if(check){
                           break;
                       }
                    }
                    if (checkWin()) {//检测是否胜利
                        int countblack = 0;
                        int countwhite = 0;
                        for (int k = 0; k < 8; k++) {
                            for (int l = 0; l < 8; l++) {
                                if (GameFrame.controller.getGamePanel().chessGrids[k][l].getChessPiece() == ChessPiece.BLACK) {
                                    countblack++;
                                }
                                if (GameFrame.controller.getGamePanel().chessGrids[k][l].getChessPiece() == ChessPiece.WHITE) {
                                    countwhite++;
                                }
                            }
                        }
                        if (countwhite > countblack) {
                            JFrame a = new JFrame();
                            a.setLocationRelativeTo(null);
                            a.setSize(200, 100);
                            JLabel b = new JLabel();
                            b.setLocation(30, 20);
                            b.setText("            "+StatusPanel.WhitePlayer+" wins!");
                            a.add(b);
                            a.setVisible(true);


                        } else if (countwhite < countblack) {
                            JFrame a = new JFrame();
                            a.setLocationRelativeTo(null);
                            a.setSize(200, 100);
                            JLabel b = new JLabel();
                            b.setLocation(30, 20);
                            b.setText("           "+StatusPanel.BlackPlayer+" wins!");
                            a.add(b);
                            a.setVisible(true);
                        } else {
                            JFrame a = new JFrame();
                            a.setLocationRelativeTo(null);
                            a.setSize(200, 100);
                            JLabel b = new JLabel();
                            b.setLocation(30, 20);
                            b.setText("     Nobody win!");
                            a.add(b);
                            a.setVisible(true);
                        }

                    }
                    GameFrame.controller.swapPlayer();
                    tips();
                }
            }
        }//人机模式EASY

        if(GameFrame.a ==2 ){
            if (this.chessPiece == null || this.chessPiece == ChessPiece.PINK) {

                if (GameFrame.controller.canClick(row, col)) {
                    this.chessPiece = GameFrame.controller.getCurrentPlayer();
                    repaint();
                    reTips();
                    GameFrame.controller.swapPlayer();

                    boolean check = false;


//                    boolean bb=false;
                    int count  = 0;
                    for (int i = 2; i < 6; i++) {
                        for (int j = 0; j < 8; j++) {

                            if (GameFrame.controller.canClick(i, j)) {
                                GameFrame.controller.getGamePanel().chessGrids[i][j].setChessPiece(GameFrame.controller.getCurrentPlayer());
                                GameFrame.controller.getGamePanel().chessGrids[i][j].repaint();//机器下子
                                count ++;
                                check = true;
                                break;
                            }

                        }
                        if(check){
                            break;
                        }
                    }//从中间四行开始遍历
                    for(int i=0; i<2; i++){
                        for (int j=0; j<8; j++){
                            if (GameFrame.controller.canClick(i, j) && count==0) {
                                GameFrame.controller.getGamePanel().chessGrids[i][j].setChessPiece(GameFrame.controller.getCurrentPlayer());
                                GameFrame.controller.getGamePanel().chessGrids[i][j].repaint();//机器下子
                                check = true;
                                count ++;
                                break;
                            }
                        }
                        if(check) {
                            break;
                        }
                    }//遍历开头两行
                    for(int i=6; i<8; i++){
                     for(int j=0; j<8; j++){
                         if (GameFrame.controller.canClick(i, j) && count==0) {
                             GameFrame.controller.getGamePanel().chessGrids[i][j].setChessPiece(GameFrame.controller.getCurrentPlayer());
                             GameFrame.controller.getGamePanel().chessGrids[i][j].repaint();//机器下子
                             check = true;
                             break;
                         }
                     }
                        if(check) {
                            break;
                     }
                    }//遍历最后两行







                    if (checkWin()) {//检测是否胜利
                        int countblack = 0;
                        int countwhite = 0;
                        for (int k = 0; k < 8; k++) {
                            for (int l = 0; l < 8; l++) {
                                if (GameFrame.controller.getGamePanel().chessGrids[k][l].getChessPiece() == ChessPiece.BLACK) {
                                    countblack++;
                                }
                                if (GameFrame.controller.getGamePanel().chessGrids[k][l].getChessPiece() == ChessPiece.WHITE) {
                                    countwhite++;
                                }
                            }
                        }
                        if (countwhite > countblack) {
                            JFrame a = new JFrame();
                            a.setLocationRelativeTo(null);
                            a.setSize(200, 100);
                            JLabel b = new JLabel();
                            b.setLocation(30, 20);
                            b.setText("            "+StatusPanel.WhitePlayer+" wins!");
                            a.add(b);
                            a.setVisible(true);


                        } else if (countwhite < countblack) {
                            JFrame a = new JFrame();
                            a.setLocationRelativeTo(null);
                            a.setSize(200, 100);
                            JLabel b = new JLabel();
                            b.setLocation(30, 20);
                            b.setText("           "+StatusPanel.BlackPlayer+" wins!");
                            a.add(b);
                            a.setVisible(true);
                        } else {
                            JFrame a = new JFrame();
                            a.setLocationRelativeTo(null);
                            a.setSize(200, 100);
                            JLabel b = new JLabel();
                            b.setLocation(30, 20);
                            b.setText("     Nobody win!");
                            a.add(b);
                            a.setVisible(true);
                        }

                    }
                    GameFrame.controller.swapPlayer();
                    tips();
                }
            }

        }//人机模式HARD


        if (GameFrame.a == 3) {
            GameFrame.controller.getGamePanel().chessGrids[row][col].setChessPiece(GameFrame.controller.getCurrentPlayer());
            GameFrame.a = 0;
            repaint();
            reTips();;
            GameFrame.controller.swapPlayer();
            tips();
        }//作弊模式

    }

    //返回下一个玩家
    public ChessPiece NextPlayer() {
        if (GameFrame.controller.getCurrentPlayer() == ChessPiece.WHITE) {
            ChessPiece nextplayer = ChessPiece.BLACK;
            return nextplayer;
        } else {
            ChessPiece nextplayer = ChessPiece.WHITE;
            return nextplayer;
        }
    }

    //判断胜利
    public boolean checkWin() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (GameFrame.controller.getGamePanel().chessGrids[i][j].getChessPiece() == null
                        || GameFrame.controller.getGamePanel().chessGrids[i][j].getChessPiece() == ChessPiece.PINK) {
                    if (GameFrame.controller.getGamePanel().canClickGrid(i, j, NextPlayer())) {
                        return false;
                    }
                }
            }
        };
        return true;
    }



    //显示提示棋子
    public void tips() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (GameFrame.controller.getGamePanel().chessGrids[i][j].getChessPiece() == null) {
                    if (GameFrame.controller.checkClick(i, j)) {
                        GameFrame.controller.getGamePanel().chessGrids[i][j].setChessPiece(ChessPiece.PINK);
                        GameFrame.controller.getGamePanel().chessGrids[i][j].repaint();
                    }
                }
            }
        }
    }

    //取消提示棋子
    public void reTips() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (GameFrame.controller.getGamePanel().chessGrids[i][j].getChessPiece() == ChessPiece.PINK) {
                    GameFrame.controller.getGamePanel().chessGrids[i][j].setChessPiece(null);
                    GameFrame.controller.getGamePanel().chessGrids[i][j].repaint();
                }
            }
        }
    }


    public ChessPiece getChessPiece() {
        return chessPiece;
    }

    public void setChessPiece(ChessPiece chessPiece) {
        this.chessPiece = chessPiece;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void drawPiece(Graphics g) {

        g.setColor(Color.BLACK);
        g.drawRect(0, 0, this.getWidth(), this.getHeight());
          if(GameFrame.cc==0) {
            if (this.chessPiece != null) {
                g.setColor(chessPiece.getColor());
                g.fillOval((gridSize - chessSize) / 2, (gridSize - chessSize) / 2, chessSize, chessSize);
                if (chessPiece == ChessPiece.PINK) {
                    ImageIcon imageGif = new ImageIcon("tips.jpg");
                    g.drawImage(imageGif.getImage(), 1, 1, getSize().width - 1,
                            getSize().height - 1, this);
                }
            }
        }//主题一
          if(GameFrame.cc==1){
        ImageIcon imageGif1 = new ImageIcon("black1.jpg");
        ImageIcon imageGif2 = new ImageIcon("white1.jpg");
        ImageIcon imageGif3 = new ImageIcon("cute.GIF");
        if (this.chessPiece != null) {

            if (chessPiece == ChessPiece.BLACK) {
                g.drawImage(imageGif1.getImage(), 1, 1, getSize().width - 1,
                        getSize().height - 1, this);
            } else if (chessPiece == ChessPiece.WHITE) {
                g.drawImage(imageGif2.getImage(), 1, 1, getSize().width - 1,
                        getSize().height - 1, this);
            } else {
                g.drawImage(imageGif3.getImage(), 1, 1, getSize().width - 1,
                        getSize().height - 1, this);
            }
        }
        }//主题二
          if(GameFrame.cc==2){
              g.setColor(Color.WHITE);
              g.drawRect(0, 0, this.getWidth(), this.getHeight());
              ImageIcon imageGif1 = new ImageIcon("earth.jpg");
              ImageIcon imageGif2 = new ImageIcon("moon.jpg");
              ImageIcon imageGif3 = new ImageIcon("star.jpg");
              if (this.chessPiece != null) {

                  if (chessPiece == ChessPiece.BLACK) {
                      g.drawImage(imageGif1.getImage(), 1, 1, getSize().width - 1,
                              getSize().height - 1, this);
                  } else if (chessPiece == ChessPiece.WHITE) {
                      g.drawImage(imageGif2.getImage(), 1, 1, getSize().width - 1,
                              getSize().height - 1, this);
                  } else {
                      g.drawImage(imageGif3.getImage(), 1, 1, getSize().width - 1,
                              getSize().height - 1, this);
                  }
              }

          }//主题三

        if(GameFrame.cc==3){
            ImageIcon imageGif1 = new ImageIcon("bean1.jpg");
            ImageIcon imageGif2 = new ImageIcon("bean2.jpg");
            ImageIcon imageGif3 = new ImageIcon("btip.jpg");
            if (this.chessPiece != null) {

                if (chessPiece == ChessPiece.BLACK) {
                    g.drawImage(imageGif1.getImage(), 1, 1, getSize().width - 1,
                            getSize().height - 1, this);
                } else if (chessPiece == ChessPiece.WHITE) {
                    g.drawImage(imageGif2.getImage(), 1, 1, getSize().width - 1,
                            getSize().height - 1, this);
                } else {
                    g.drawImage(imageGif3.getImage(), 1, 1, getSize().width - 1,
                            getSize().height - 1, this);
                }
            }

        }//主题四

    }


    @Override
    public void paintComponent(Graphics g) {
        super.printComponents(g);
        drawPiece(g);
    }


}
