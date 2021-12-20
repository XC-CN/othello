package view;

import components.ChessGridComponent;
import model.ChessPiece;

import javax.swing.*;
import java.awt.*;

public class ChessBoardPanel extends JPanel {
    private final int CHESS_COUNT = 8;
    public ChessGridComponent[][] chessGrids;
    public ChessGridComponent[][] chessRecords=new ChessGridComponent[8][8];

    public ChessBoardPanel(int width, int height) {
        this.setVisible(true);
        this.setFocusable(true);
        this.setLayout(null);
        this.setBackground(Color.WHITE);
        int length = Math.min(width, height);
        this.setSize(length, length);
        ChessGridComponent.gridSize = length / CHESS_COUNT;
        ChessGridComponent.chessSize = (int) (ChessGridComponent.gridSize * 0.8);
        System.out.printf("width = %d height = %d gridSize = %d chessSize = %d\n",
                width, height, ChessGridComponent.gridSize, ChessGridComponent.chessSize);

        initialChessGrids();//return empty chessboard
        initialGame();//add initial four chess

        repaint();
    }

    /**
     * set an empty chessboard
     */
    public void initialChessGrids() {
        chessGrids = new ChessGridComponent[CHESS_COUNT][CHESS_COUNT];

        //draw all chess grids
        for (int i = 0; i < CHESS_COUNT; i++) {
            for (int j = 0; j < CHESS_COUNT; j++) {
                ChessGridComponent gridComponent = new ChessGridComponent(i, j);
                gridComponent.setLocation(j * ChessGridComponent.gridSize, i * ChessGridComponent.gridSize);
                chessGrids[i][j] = gridComponent;
                this.add(chessGrids[i][j]);
            }
        }
        for (int i = 0; i < CHESS_COUNT; i++) {
            for (int j = 0; j < CHESS_COUNT; j++) {
                ChessGridComponent gridComponent = new ChessGridComponent(i, j);
                chessRecords[i][j] = gridComponent;

            }
        }
    }

    /**
     * initial origin four chess
     */
    public void initialGame() {
        chessGrids[3][3].setChessPiece(ChessPiece.BLACK);
        chessGrids[3][4].setChessPiece(ChessPiece.WHITE);
        chessGrids[4][3].setChessPiece(ChessPiece.WHITE);
        chessGrids[4][4].setChessPiece(ChessPiece.BLACK);

    }


    @Override
    protected void paintComponent(Graphics g) {

        if (GameFrame.cc==0) {
            super.paintComponent(g);
            ImageIcon imageGif = new ImageIcon("marble.jpg");

            g.drawImage(imageGif.getImage(), 0, 0, getSize().width, getSize().height, this);
        }
        if (GameFrame.cc==1) {
            super.paintComponent(g);
            ImageIcon imageGif = new ImageIcon("cutebg.jpg");

            g.drawImage(imageGif.getImage(), 0, 0, getSize().width, getSize().height, this);
        }

        if(GameFrame.cc==2){
            super.paintComponent(g);
            ImageIcon imageGif = new ImageIcon("galaxy.jpg") ;

            g.drawImage(imageGif.getImage(), 0, 0, getSize().width, getSize().height, this);
        }
        if(GameFrame.cc==3){
            super.paintComponent(g);
            ImageIcon imageGif = new ImageIcon("beanbg.jpg") ;

            g.drawImage(imageGif.getImage(), 0, 0, getSize().width, getSize().height, this);
        }
    }
    public boolean canClickGrid(int row, int col, ChessPiece currentPlayer) {
        boolean place = false;
        //下方
        ChessPiece a;
        if (currentPlayer == ChessPiece.WHITE) {
            a = ChessPiece.BLACK;

        } else {
            a = ChessPiece.WHITE;
        }
        if (row + 1 < 8) {
            if (chessGrids[row + 1][col].getChessPiece() != null) {

                if (chessGrids[row + 1][col].getChessPiece() == a) {
                    for (int f = 1; f < 8 - row; f++) {
                        if (chessGrids[row + f][col].getChessPiece() == null
                                || chessGrids[row + f][col].getChessPiece() == ChessPiece.PINK
                        ) {
                            break;
                        }
                        if (chessGrids[row + f][col].getChessPiece() == currentPlayer) {

                            place = true;

                            break;
                        }

                    }
                }
            }
        }


        //上方
        if (row - 1 >= 0) {
            if (chessGrids[row - 1][col].getChessPiece() != null) {
                if (chessGrids[row - 1][col].getChessPiece() == a) {
                    for (int f = 1; row - f >= 0; f++) {
                        if (chessGrids[row - f][col].getChessPiece() == null
                                || chessGrids[row - f][col].getChessPiece() == ChessPiece.PINK) {
                            break;
                        }
                        if (chessGrids[row - f][col].getChessPiece() == currentPlayer) {

                            place = true;
                            break;
                        }

                    }
                }
            }
        }

        //右方
        if (col + 1 < 8) {
            if (chessGrids[row][col + 1].getChessPiece() != null) {
                if (chessGrids[row][col + 1].getChessPiece() == a) {
                    for (int f = 1; f < 8 - col; f++) {
                        if (chessGrids[row][col + f].getChessPiece() == null
                                || chessGrids[row][col + f].getChessPiece() == ChessPiece.PINK) {
                            break;
                        }
                        if (chessGrids[row][col + f].getChessPiece() == currentPlayer) {

                            place = true;
                            break;
                        }

                    }
                }
            }
        }
        //左方
        if (col - 1 >= 0) {
            if (chessGrids[row][col - 1].getChessPiece() != null)
                if (chessGrids[row][col - 1].getChessPiece() == a) {
                    for (int f = 1; col - f >= 0; f++) {
                        if (chessGrids[row][col - f].getChessPiece() == null
                                || chessGrids[row][col - f].getChessPiece() == ChessPiece.PINK) {
                            break;
                        }
                        if (chessGrids[row][col - f].getChessPiece() == currentPlayer) {

                            place = true;
                            break;
                        }

                    }
                }
        }

        if (row + 1 < 8 && col + 1 < 8) {
            if (chessGrids[row + 1][col + 1].getChessPiece() != null) {
                if (chessGrids[row + 1][col + 1].getChessPiece() == a) {
                    //右上
                    for (int f = 1; f < 8 - row && f < 8 - col; f++) {
                        if (chessGrids[row + f][col + f].getChessPiece() == null
                                || chessGrids[row + f][col + f].getChessPiece() == ChessPiece.PINK) {
                            break;
                        }
                        if (chessGrids[row + f][col + f].getChessPiece() == currentPlayer) {

                            place = true;
                            break;
                        }
                    }
                }
            }
        }

        //左下
        if (row - 1 >= 0 && col - 1 >= 0) {
            if (chessGrids[row - 1][col - 1].getChessPiece() != null) {
                if (chessGrids[row - 1][col - 1].getChessPiece() == a) {
                    for (int f = 1; row - f >= 0 && col - f >= 0; f++) {
                        if (chessGrids[row - f][col - f].getChessPiece() == null
                                || chessGrids[row - f][col - f].getChessPiece() == ChessPiece.PINK) {
                            break;
                        }
                        if (chessGrids[row - f][col - f].getChessPiece() == currentPlayer) {

                            place = true;
                            break;
                        }

                    }
                }
            }
        }

        //
        if (row + 1 < 8 && col - 1 >= 0) {
            if (chessGrids[row + 1][col - 1].getChessPiece() != null) {
                if (chessGrids[row + 1][col - 1].getChessPiece() == a) {
                    for (int f = 1; col - f >= 0 && row + f < 8; f++) {
                        if (chessGrids[row + f][col - f].getChessPiece() == null
                                || chessGrids[row + f][col - f].getChessPiece() == ChessPiece.PINK) {
                            break;
                        }
                        if (chessGrids[row + f][col - f].getChessPiece() == currentPlayer) {

                            place = true;
                            break;
                        }

                    }
                }
            }
        }
        //
        if (row - 1 >= 0 && col + 1 < 8) {
            if (chessGrids[row - 1][col + 1].getChessPiece() != null) {
                if (chessGrids[row - 1][col + 1].getChessPiece() == a) {
                    for (int f = 1; row - f >= 0 && col + f < 8; f++) {
                        if (chessGrids[row - f][col + f].getChessPiece() == null
                                || chessGrids[row - f][col + f].getChessPiece() == ChessPiece.PINK) {
                            break;
                        }
                        if (chessGrids[row - f][col + f].getChessPiece() == currentPlayer) {

                            place = true;
                            break;
                        }

                    }
                }
            }
        }

        return place;

    }


}
