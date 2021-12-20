package controller;

import model.ChessPiece;
import view.*;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class GameController {


    private ChessBoardPanel gamePanel;
    private StatusPanel statusPanel;
    private ChessPiece currentPlayer;
    private int blackScore;
    private int whiteScore;

    public GameController(ChessBoardPanel gamePanel, StatusPanel statusPanel) {
        this.gamePanel = gamePanel;
        this.statusPanel = statusPanel;
        this.currentPlayer = ChessPiece.BLACK;
        blackScore = 0;
        whiteScore = 0;
    }

    public void swapPlayer() {
        countScore();
        currentPlayer = (currentPlayer == ChessPiece.BLACK) ? ChessPiece.WHITE : ChessPiece.BLACK;
        if(currentPlayer==ChessPiece.BLACK){
            statusPanel.setPlayerText(StatusPanel.BlackPlayer);
        }
      else {
            statusPanel.setPlayerText(StatusPanel.WhitePlayer);
        }
        statusPanel.setScoreText(blackScore,whiteScore);
    }


    public void countScore() {
        //todo: modify the countScore method
        blackScore=0; whiteScore=0;
        for (int i=0; i<8;i++){
            for(int j=0; j<8; j++){
                if(gamePanel.chessGrids[i][j].getChessPiece()==ChessPiece.BLACK) {
                    blackScore++;
                }
                else if(gamePanel.chessGrids[i][j].getChessPiece()==ChessPiece.WHITE){
                    whiteScore++;
                }

            }
        }
    }


    public ChessPiece getCurrentPlayer() {
        return currentPlayer;
    }

    public ChessBoardPanel getGamePanel() {
        return gamePanel;
    }


    public void setGamePanel(ChessBoardPanel gamePanel) {
        this.gamePanel = gamePanel;
    }


    public void readFileData(String fileName) {
        //todo: read date from file
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader
                    ("Files\\" + fileName + ".txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("不存在该存档，请检查是否输入错误");
        }
        System.out.println("开始读档： ");
        try{
            GameFrame.cc = Integer.parseInt(reader.readLine());
        }catch (IOException e){
            e.printStackTrace();
        }
        try {
            GameFrame.a = Integer.parseInt(reader.readLine());
        }catch (IOException e){
            e.printStackTrace();
        }//读取玩家名字
        try{
            switch(GameFrame.a){
                case 0 :{
                    StatusPanel.BlackPlayer = reader.readLine();
                    StatusPanel.WhitePlayer = reader.readLine();
                    GameFrame.controller.statusPanel.setPlayerText(StatusPanel.BlackPlayer);
                    System.out.println("黑方玩家名字为"+StatusPanel.BlackPlayer);
                    System.out.println("白方玩家名字为"+StatusPanel.WhitePlayer);
                    TimerTest timerTest = new TimerTest(500);
                    timerTest.start();
                    break;
                }
                case 1:{
                    StatusPanel.BlackPlayer = reader.readLine();
                    StatusPanel.WhitePlayer = "Computer";
                    System.out.println("人类名字为"+StatusPanel.BlackPlayer);
                    break;
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        //读取棋盘
        for (int i = 0; i < 8; i++) {
            char[] charChessGird = new char[8];
            String s = null;
            try {
                s = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String[] ss = s.split("");
            for (int j = 0; j < 8; j++) {
                if (ss[j].equals("1"))
                    gamePanel.chessGrids[i][j].setChessPiece(ChessPiece.WHITE);
                if (ss[j].equals("2"))
                    gamePanel.chessGrids[i][j].setChessPiece(ChessPiece.BLACK);
                if (ss[j].equals("0"))
                    gamePanel.chessGrids[i][j].setChessPiece(null);
                if (ss[j].equals("3"))
                    gamePanel.chessGrids[i][j].setChessPiece(ChessPiece.PINK);
                gamePanel.chessGrids[i][j].repaint();
            }
        }
        try {
            if (reader.readLine().equals("4"))
                currentPlayer = ChessPiece.WHITE;
            else
                currentPlayer = ChessPiece.BLACK;
            System.out.println("Success to load the file ("+fileName+")");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeDataToFile(String fileName) {
        //todo: write data into file
        try {
            File file = new File("Files\\" + fileName + ".txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(
                    "Files\\" + fileName + ".txt"));
            writer.write(GameFrame.cc +"\n");
            writer.write(GameFrame.a+"\n");
            switch(GameFrame.a){
                case 0:{
                    writer.write(StatusPanel.BlackPlayer+"\n");
                    writer.write(StatusPanel.WhitePlayer+"\n");
                    break;
                }
                case 1:{
                    writer.write(StatusPanel.BlackPlayer+"\n");
                    break;
                }
            }
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (gamePanel.chessGrids[i][j].getChessPiece() == ChessPiece.WHITE)
                        writer.write("1");//储存白棋
                    if (gamePanel.chessGrids[i][j].getChessPiece() == ChessPiece.BLACK)
                        writer.write("2");//储存黑棋
                    if (gamePanel.chessGrids[i][j].getChessPiece() == null)
                        writer.write("0");//储存空格
                    if (gamePanel.chessGrids[i][j].getChessPiece() == ChessPiece.PINK)
                        writer.write("3");//储存可下棋点
                }
                writer.write("\n");
            }
            if (currentPlayer == ChessPiece.WHITE)
                writer.write("4");//4是白方
            else
                writer.write("5");//5是黑方
            writer.close();
            System.out.println("Success to save the chessgrid");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public boolean canClick(int row, int col) {
        boolean place = false;
        //下方
        ChessPiece a;
        if (currentPlayer == ChessPiece.WHITE) {
            a = ChessPiece.BLACK;

        } else {
            a = ChessPiece.WHITE;
        }
        if (row + 1 < 8) {
            if (gamePanel.chessGrids[row + 1][col].getChessPiece() != null) {

                if (gamePanel.chessGrids[row + 1][col].getChessPiece() == a) {
                    for (int f = 1; f < 8 - row; f++) {
                        if (gamePanel.chessGrids[row + f][col].getChessPiece() == null
                                || gamePanel.chessGrids[row + f][col].getChessPiece() == ChessPiece.PINK
                        ) {
                            break;
                        }
                        if (gamePanel.chessGrids[row + f][col].getChessPiece() == currentPlayer) {
                            for (int q = 1; q < f; q++) {
                                gamePanel.chessGrids[row + q][col].setChessPiece(currentPlayer);
                            }
                            place = true;

                            break;
                        }

                    }
                }
            }
        }


        //上方
        if (row - 1 >= 0) {
            if (gamePanel.chessGrids[row - 1][col].getChessPiece() != null) {
                if (gamePanel.chessGrids[row - 1][col].getChessPiece() == a) {
                    for (int f = 1; row - f >= 0; f++) {
                        if (gamePanel.chessGrids[row - f][col].getChessPiece() == null
                                || gamePanel.chessGrids[row - f][col].getChessPiece() == ChessPiece.PINK) {
                            break;
                        }
                        if (gamePanel.chessGrids[row - f][col].getChessPiece() == currentPlayer) {
                            for (int q = 1; q < f; q++) {
                                gamePanel.chessGrids[row - q][col].setChessPiece(currentPlayer);
                            }
                            place = true;
                            break;
                        }

                    }
                }
            }
        }

        //右方
        if (col + 1 < 8) {
            if (gamePanel.chessGrids[row][col + 1].getChessPiece() != null) {
                if (gamePanel.chessGrids[row][col + 1].getChessPiece() == a) {
                    for (int f = 1; f < 8 - col; f++) {
                        if (gamePanel.chessGrids[row][col + f].getChessPiece() == null
                                || gamePanel.chessGrids[row][col + f].getChessPiece() == ChessPiece.PINK) {
                            break;
                        }
                        if (gamePanel.chessGrids[row][col + f].getChessPiece() == currentPlayer) {
                            for (int q = 1; q < f; q++) {
                                gamePanel.chessGrids[row][col + q].setChessPiece(currentPlayer);
                            }
                            place = true;
                            break;
                        }

                    }
                }
            }
        }
        //左方
        if (col - 1 >= 0) {
            if (gamePanel.chessGrids[row][col - 1].getChessPiece() != null)
                if (gamePanel.chessGrids[row][col - 1].getChessPiece() == a) {
                    for (int f = 1; col - f >= 0; f++) {
                        if (gamePanel.chessGrids[row][col - f].getChessPiece() == null
                                || gamePanel.chessGrids[row][col - f].getChessPiece() == ChessPiece.PINK) {
                            break;
                        }
                        if (gamePanel.chessGrids[row][col - f].getChessPiece() == currentPlayer) {
                            for (int q = 1; q < f; q++) {
                                gamePanel.chessGrids[row][col - q].setChessPiece(currentPlayer);
                            }
                            place = true;
                            break;
                        }

                    }
                }
        }

        if (row + 1 < 8 && col + 1 < 8) {
            if (gamePanel.chessGrids[row + 1][col + 1].getChessPiece() != null) {
                if (gamePanel.chessGrids[row + 1][col + 1].getChessPiece() == a) {
                    //右上
                    for (int f = 1; f < 8 - row && f < 8 - col; f++) {
                        if (gamePanel.chessGrids[row + f][col + f].getChessPiece() == null
                                || gamePanel.chessGrids[row + f][col + f].getChessPiece() == ChessPiece.PINK) {
                            break;
                        }
                        if (gamePanel.chessGrids[row + f][col + f].getChessPiece() == currentPlayer) {
                            for (int q = 1; q < f; q++) {
                                gamePanel.chessGrids[row + q][col + q].setChessPiece(currentPlayer);
                            }
                            place = true;
                            break;
                        }
                    }
                }
            }
        }

        //左下
        if (row - 1 >= 0 && col - 1 >= 0) {
            if (gamePanel.chessGrids[row - 1][col - 1].getChessPiece() != null) {
                if (gamePanel.chessGrids[row - 1][col - 1].getChessPiece() == a) {
                    for (int f = 1; row - f >= 0 && col - f >= 0; f++) {
                        if (gamePanel.chessGrids[row - f][col - f].getChessPiece() == null
                                || gamePanel.chessGrids[row - f][col - f].getChessPiece() == ChessPiece.PINK) {
                            break;
                        }
                        if (gamePanel.chessGrids[row - f][col - f].getChessPiece() == currentPlayer) {
                            for (int q = 1; q < f; q++) {
                                gamePanel.chessGrids[row - q][col - q].setChessPiece(currentPlayer);
                            }
                            place = true;
                            break;
                        }

                    }
                }
            }
        }

        //
        if (row + 1 < 8 && col - 1 >= 0) {
            if (gamePanel.chessGrids[row + 1][col - 1].getChessPiece() != null) {
                if (gamePanel.chessGrids[row + 1][col - 1].getChessPiece() == a) {
                    for (int f = 1; col - f >= 0 && row + f < 8; f++) {
                        if (gamePanel.chessGrids[row + f][col - f].getChessPiece() == null
                                || gamePanel.chessGrids[row + f][col - f].getChessPiece() == ChessPiece.PINK) {
                            break;
                        }
                        if (gamePanel.chessGrids[row + f][col - f].getChessPiece() == currentPlayer) {
                            for (int q = 1; q < f; q++) {
                                gamePanel.chessGrids[row + q][col - q].setChessPiece(currentPlayer);
                            }
                            place = true;
                            break;
                        }

                    }
                }
            }
        }
        //
        if (row - 1 >= 0 && col + 1 < 8) {
            if (gamePanel.chessGrids[row - 1][col + 1].getChessPiece() != null) {
                if (gamePanel.chessGrids[row - 1][col + 1].getChessPiece() == a) {
                    for (int f = 1; row - f >= 0 && col + f < 8; f++) {
                        if (gamePanel.chessGrids[row - f][col + f].getChessPiece() == null
                                || gamePanel.chessGrids[row - f][col + f].getChessPiece() == ChessPiece.PINK) {
                            break;
                        }
                        if (gamePanel.chessGrids[row - f][col + f].getChessPiece() == currentPlayer) {
                            for (int q = 1; q < f; q++) {
                                gamePanel.chessGrids[row - q][col + q].setChessPiece(currentPlayer);
                            }
                            place = true;
                            break;
                        }

                    }
                }
            }
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                gamePanel.chessGrids[i][j].repaint();
            }
        }
        return place;

    }

//    public boolean canClickwin(int row, int col) {
//        boolean place = false;
//        //下方
//        ChessPiece a=currentPlayer;
//        ChessPiece b;
//        if (currentPlayer == ChessPiece.WHITE) {
//            b = ChessPiece.BLACK;
//
//        } else {
//             b= ChessPiece.WHITE;
//        }
//        if (row + 1 < 8) {
//            if (gamePanel.chessGrids[row + 1][col].getChessPiece() != null) {
//
//                if (gamePanel.chessGrids[row + 1][col].getChessPiece() == a) {
//                    for (int f = 1; f < 8 - row; f++) {
//                        if (gamePanel.chessGrids[row + f][col].getChessPiece() == null
//                                || gamePanel.chessGrids[row + f][col].getChessPiece() == ChessPiece.PINK
//                        ) {
//                            break;
//                        }
//                        if (gamePanel.chessGrids[row + f][col].getChessPiece() == b) {
//
//                            place = true;
//
//                            break;
//                        }
//
//                    }
//                }
//            }
//        }
//
//
//        //上方
//        if (row - 1 >= 0) {
//            if (gamePanel.chessGrids[row - 1][col].getChessPiece() != null) {
//                if (gamePanel.chessGrids[row - 1][col].getChessPiece() == a) {
//                    for (int f = 1; row - f >= 0; f++) {
//                        if (gamePanel.chessGrids[row - f][col].getChessPiece() == null
//                                || gamePanel.chessGrids[row - f][col].getChessPiece() == ChessPiece.PINK) {
//                            break;
//                        }
//                        if (gamePanel.chessGrids[row - f][col].getChessPiece() == b) {
//
//                            place = true;
//                            break;
//                        }
//
//                    }
//                }
//            }
//        }
//
//        //右方
//        if (col + 1 < 8) {
//            if (gamePanel.chessGrids[row][col + 1].getChessPiece() != null) {
//                if (gamePanel.chessGrids[row][col + 1].getChessPiece() == a) {
//                    for (int f = 1; f < 8 - col; f++) {
//                        if (gamePanel.chessGrids[row][col + f].getChessPiece() == null
//                                || gamePanel.chessGrids[row][col + f].getChessPiece() == ChessPiece.PINK) {
//                            break;
//                        }
//                        if (gamePanel.chessGrids[row][col + f].getChessPiece() == b) {
//
//                            place = true;
//                            break;
//                        }
//
//                    }
//                }
//            }
//        }
//        //左方
//        if (col - 1 >= 0) {
//            if (gamePanel.chessGrids[row][col - 1].getChessPiece() != null)
//                if (gamePanel.chessGrids[row][col - 1].getChessPiece() == a) {
//                    for (int f = 1; col - f >= 0; f++) {
//                        if (gamePanel.chessGrids[row][col - f].getChessPiece() == null
//                                || gamePanel.chessGrids[row][col - f].getChessPiece() == ChessPiece.PINK) {
//                            break;
//                        }
//                        if (gamePanel.chessGrids[row][col - f].getChessPiece() == b) {
//                            place = true;
//                            break;
//                        }
//
//                    }
//                }
//        }
//
//        if (row + 1 < 8 && col + 1 < 8) {
//            if (gamePanel.chessGrids[row + 1][col + 1].getChessPiece() != null) {
//                if (gamePanel.chessGrids[row + 1][col + 1].getChessPiece() == a) {
//                    //右上
//                    for (int f = 1; f < 8 - row && f < 8 - col; f++) {
//                        if (gamePanel.chessGrids[row + f][col + f].getChessPiece() == null
//                                || gamePanel.chessGrids[row + f][col + f].getChessPiece() == ChessPiece.PINK) {
//                            break;
//                        }
//                        if (gamePanel.chessGrids[row + f][col + f].getChessPiece() == b) {
//
//                            place = true;
//                            break;
//                        }
//                    }
//                }
//            }
//        }
//
//        //左下
//        if (row - 1 >= 0 && col - 1 >= 0) {
//            if (gamePanel.chessGrids[row - 1][col - 1].getChessPiece() != null) {
//                if (gamePanel.chessGrids[row - 1][col - 1].getChessPiece() == a) {
//                    for (int f = 1; row - f >= 0 && col - f >= 0; f++) {
//                        if (gamePanel.chessGrids[row - f][col - f].getChessPiece() == null
//                                || gamePanel.chessGrids[row - f][col - f].getChessPiece() == ChessPiece.PINK) {
//                            break;
//                        }
//                        if (gamePanel.chessGrids[row - f][col - f].getChessPiece() == b) {
//
//                            place = true;
//                            break;
//                        }
//
//                    }
//                }
//            }
//        }
//
//        //
//        if (row + 1 < 8 && col - 1 >= 0) {
//            if (gamePanel.chessGrids[row + 1][col - 1].getChessPiece() != null) {
//                if (gamePanel.chessGrids[row + 1][col - 1].getChessPiece() == a) {
//                    for (int f = 1; col - f >= 0 && row + f < 8; f++) {
//                        if (gamePanel.chessGrids[row + f][col - f].getChessPiece() == null
//                                || gamePanel.chessGrids[row + f][col - f].getChessPiece() == ChessPiece.PINK) {
//                            break;
//                        }
//                        if (gamePanel.chessGrids[row + f][col - f].getChessPiece() == b) {
//
//                            place = true;
//                            break;
//                        }
//
//                    }
//                }
//            }
//        }
//        //
//        if (row - 1 >= 0 && col + 1 < 8) {
//            if (gamePanel.chessGrids[row - 1][col + 1].getChessPiece() != null) {
//                if (gamePanel.chessGrids[row - 1][col + 1].getChessPiece() == a) {
//                    for (int f = 1; row - f >= 0 && col + f < 8; f++) {
//                        if (gamePanel.chessGrids[row - f][col + f].getChessPiece() == null
//                                || gamePanel.chessGrids[row - f][col + f].getChessPiece() == ChessPiece.PINK) {
//                            break;
//                        }
//                        if (gamePanel.chessGrids[row - f][col + f].getChessPiece() == b) {
//
//                            place = true;
//                            break;
//                        }
//
//                    }
//                }
//            }
//        }
//        for (int i = 0; i < 8; i++) {
//            for (int j = 0; j < 8; j++) {
//                gamePanel.chessGrids[i][j].repaint();
//            }
//        }
//        return place;
//
//    }

    public boolean checkClick(int row, int col) {
        boolean place = false;
        //下方
        ChessPiece a;
        if (currentPlayer == ChessPiece.WHITE) {
            a = ChessPiece.BLACK;

        } else {
            a = ChessPiece.WHITE;
        }
        if (row + 1 < 8) {
            if (gamePanel.chessGrids[row + 1][col].getChessPiece() != null) {

                if (gamePanel.chessGrids[row + 1][col].getChessPiece() == a) {
                    for (int f = 1; f < 8 - row; f++) {
                        if (gamePanel.chessGrids[row + f][col].getChessPiece() == null
                                || gamePanel.chessGrids[row + f][col].getChessPiece() == ChessPiece.PINK) {
                            break;
                        }
                        if (gamePanel.chessGrids[row + f][col].getChessPiece() == currentPlayer) {
                            place = true;

                            break;
                        }

                    }
                }
            }
        }


        //上方
        if (row - 1 >= 0) {
            if (gamePanel.chessGrids[row - 1][col].getChessPiece() != null) {
                if (gamePanel.chessGrids[row - 1][col].getChessPiece() == a) {
                    for (int f = 1; row - f >= 0; f++) {
                        if (gamePanel.chessGrids[row - f][col].getChessPiece() == null
                                || gamePanel.chessGrids[row - f][col].getChessPiece() == ChessPiece.PINK) {
                            break;
                        }
                        if (gamePanel.chessGrids[row - f][col].getChessPiece() == currentPlayer) {
                            place = true;
                            break;
                        }

                    }
                }
            }
        }

        //右方
        if (col + 1 < 8) {
            if (gamePanel.chessGrids[row][col + 1].getChessPiece() != null) {
                if (gamePanel.chessGrids[row][col + 1].getChessPiece() == a) {
                    for (int f = 1; f < 8 - col; f++) {
                        if (gamePanel.chessGrids[row][col + f].getChessPiece() == null
                                || gamePanel.chessGrids[row][col + f].getChessPiece() == ChessPiece.PINK) {
                            break;
                        }
                        if (gamePanel.chessGrids[row][col + f].getChessPiece() == currentPlayer) {
                            place = true;
                            break;
                        }

                    }
                }
            }
        }
        //左方
        if (col - 1 >= 0) {
            if (gamePanel.chessGrids[row][col - 1].getChessPiece() != null)
                if (gamePanel.chessGrids[row][col - 1].getChessPiece() == a) {
                    for (int f = 1; col - f >= 0; f++) {
                        if (gamePanel.chessGrids[row][col - f].getChessPiece() == null
                                || gamePanel.chessGrids[row][col - f].getChessPiece() == ChessPiece.PINK) {
                            break;
                        }
                        if (gamePanel.chessGrids[row][col - f].getChessPiece() == currentPlayer) {
                            place = true;
                            break;
                        }

                    }
                }
        }

        if (row + 1 < 8 && col + 1 < 8) {
            if (gamePanel.chessGrids[row + 1][col + 1].getChessPiece() != null) {
                if (gamePanel.chessGrids[row + 1][col + 1].getChessPiece() == a) {
                    //右上
                    for (int f = 1; f < 8 - row && f < 8 - col; f++) {
                        if (gamePanel.chessGrids[row + f][col + f].getChessPiece() == null
                                || gamePanel.chessGrids[row + f][col + f].getChessPiece() == ChessPiece.PINK) {
                            break;
                        }
                        if (gamePanel.chessGrids[row + f][col + f].getChessPiece() == currentPlayer) {
                            place = true;
                            break;
                        }
                    }
                }
            }
        }

        //左下
        if (row - 1 >= 0 && col - 1 >= 0) {
            if (gamePanel.chessGrids[row - 1][col - 1].getChessPiece() != null) {
                if (gamePanel.chessGrids[row - 1][col - 1].getChessPiece() == a) {
                    for (int f = 1; row - f >= 0 && col - f >= 0; f++) {
                        if (gamePanel.chessGrids[row - f][col - f].getChessPiece() == null
                                || gamePanel.chessGrids[row - f][col - f].getChessPiece() == ChessPiece.PINK) {
                            break;
                        }
                        if (gamePanel.chessGrids[row - f][col - f].getChessPiece() == currentPlayer) {
                            place = true;
                            break;
                        }

                    }
                }
            }
        }

        //
        if (row + 1 < 8 && col - 1 >= 0) {
            if (gamePanel.chessGrids[row + 1][col - 1].getChessPiece() != null) {
                if (gamePanel.chessGrids[row + 1][col - 1].getChessPiece() == a) {
                    for (int f = 1; col - f >= 0 && row + f < 8; f++) {
                        if (gamePanel.chessGrids[row + f][col - f].getChessPiece() == null
                                || gamePanel.chessGrids[row + f][col - f].getChessPiece() == ChessPiece.PINK) {
                            break;
                        }
                        if (gamePanel.chessGrids[row + f][col - f].getChessPiece() == currentPlayer) {
                            place = true;
                            break;
                        }

                    }
                }
            }
        }
        //
        if (row - 1 >= 0 && col + 1 < 8) {
            if (gamePanel.chessGrids[row - 1][col + 1].getChessPiece() != null) {
                if (gamePanel.chessGrids[row - 1][col + 1].getChessPiece() == a) {
                    for (int f = 1; row - f >= 0 && col + f < 8; f++) {
                        if (gamePanel.chessGrids[row - f][col + f].getChessPiece() == null
                                || gamePanel.chessGrids[row - f][col + f].getChessPiece() == ChessPiece.PINK) {
                            break;
                        }
                        if (gamePanel.chessGrids[row - f][col + f].getChessPiece() == currentPlayer) {
                            place = true;
                            break;
                        }

                    }
                }
            }
        }
        return place;

    }

    public void setCurrentPlayer(ChessPiece currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public int getBlackScore() {
        return blackScore;
    }

    public int getWhiteScore() {
        return whiteScore;
    }

    public void setBlackScore(int blackScore) {
        this.blackScore = blackScore;
    }

    public void setWhiteScore(int whiteScore) {
        this.whiteScore = whiteScore;
    }
}
