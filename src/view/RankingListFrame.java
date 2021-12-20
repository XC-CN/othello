package view;

import rankinglist.RankingSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;

public class RankingListFrame extends JFrame implements ActionListener{

    private static Font font;

    //导入字体
    static {
        try {
            font = Font.createFont(Font.TRUETYPE_FONT,
                    new FileInputStream("FrozenNeutra.otf"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        font = font.deriveFont(Font.BOLD, 40);
    }


//    Font f1 = new Font("楷书", Font.BOLD, 25);
    Font f1 = new Font("FrozenNeutra.otf",Font.PLAIN,25);
    Font f2 = new Font("Consola",Font.PLAIN,18);

    public static void main(String[] args) {
        new RankingListFrame(600,800);
    }

    public RankingListFrame(int a, int b ){
        JFrame rfm = new JFrame();
        bgPanel2 jp = new bgPanel2(600,800);
        jp.setOpaque(false);
        jp.setLayout(null);

        //标签及文本框
        JLabel rkl = new JLabel("RANKING LIST");
        JLabel id = new JLabel("ID");
        JLabel win = new JLabel("WIN");
        JLabel lose = new JLabel("LOSE");
        JLabel wnr = new JLabel("WINING RATE");
        JLabel ONE = new JLabel();
        JLabel TWO = new JLabel();
        JLabel THREE = new JLabel();
        JLabel FOUR = new JLabel();
        JLabel FIVE = new JLabel();
        JButton close = new JButton();


        //标签读取数据
        RankingSystem.sortWinRate();
        JLabel s11 = new JLabel(RankingSystem.players.get(0).playerName);//name
        JLabel s12 = new JLabel(String.valueOf(RankingSystem.players.get(0).winGames));//win
        JLabel s13 = new JLabel(String.valueOf(RankingSystem.players.get(0).loseGames));//lose
        JLabel s14 = new JLabel(String.format("%.4s", RankingSystem.players.get(0).winRates));//winRate

        JLabel s21 = new JLabel(RankingSystem.players.get(1).playerName);
        JLabel s22 = new JLabel(String.valueOf(RankingSystem.players.get(1).winGames));
        JLabel s23 = new JLabel(String.valueOf(RankingSystem.players.get(1).loseGames));
        JLabel s24 =new JLabel(String.format("%.4s", RankingSystem.players.get(1).winRates));//winRate

        JLabel s31 = new JLabel(RankingSystem.players.get(2).playerName);
        JLabel s32 = new JLabel(String.valueOf(RankingSystem.players.get(2).winGames));
        JLabel s33 = new JLabel(String.valueOf(RankingSystem.players.get(2).loseGames));
        JLabel s34 = new JLabel(String.format("%.4s", RankingSystem.players.get(2).winRates));//winRate

        JLabel s41 = new JLabel(RankingSystem.players.get(3).playerName);
        JLabel s42 = new JLabel(String.valueOf(RankingSystem.players.get(3).winGames));
        JLabel s43 = new JLabel(String.valueOf(RankingSystem.players.get(3).loseGames));
        JLabel s44 =new JLabel(String.format("%.4s", RankingSystem.players.get(3).winRates));//winRate

        JLabel s51 = new JLabel(RankingSystem.players.get(4).playerName);
        JLabel s52 = new JLabel(String.valueOf(RankingSystem.players.get(4).winGames));
        JLabel s53 = new JLabel(String.valueOf(RankingSystem.players.get(4).loseGames));
        JLabel s54 = new JLabel(String.format("%.4s", RankingSystem.players.get(4).winRates));//winRate

        //各组件性质及位置
        rkl.setBounds(160,50,400,100);
        rkl.setFont(font);
        rkl.setForeground(Color.darkGray);
        rkl.setOpaque(false);
        rkl.setBorder(null);

        id.setBounds(120,120,50,100);
        id.setFont(f1);
        id.setForeground(Color.white);
        id.setOpaque(false);
        id.setBorder(null);

        win.setBounds(200,120,150,100);
        win.setFont(f1);
        win.setForeground(Color.white);
        win.setOpaque(false);
        win.setBorder(null);

        lose.setBounds(280,120,150,100);
        lose.setFont(f1);
        lose.setForeground(Color.white);
        lose.setOpaque(false);
        lose.setBorder(null);

        wnr.setBounds(370,120,180,100);
        wnr.setFont(f1);
        wnr.setForeground(Color.white);
        wnr.setOpaque(false);
        wnr.setBorder(null);


        ONE.setBounds(40, 225, 35, 35);
        ONE.setOpaque(true);
        ONE.setBorder(null);
        ONE.setIcon(new ImageIcon("one.jpg"));

        TWO.setBounds(40, 325, 35, 35);
        TWO.setOpaque(true);
        TWO.setBorder(null);
        TWO.setIcon(new ImageIcon("two.jpg"));

        THREE.setBounds(40, 425, 35,35);
        THREE.setOpaque(true);
        THREE.setBorder(null);
        THREE.setIcon(new ImageIcon("three.jpg"));

        FOUR.setBounds(40, 525, 35, 35);
        FOUR.setOpaque(true);
        FOUR.setBorder(null);
        FOUR.setIcon(new ImageIcon("four.jpg"));

        FIVE.setBounds(40, 625, 35, 35);
        FIVE.setOpaque(true);
        FIVE.setBorder(null);
        FIVE.setIcon(new ImageIcon("five.jpg"));

        s11.setBounds(105,193,160,100);
        s11.setForeground(Color.BLACK);
        s11.setFont(f2);
        s11.setOpaque(false);
        s11.setBorder(null);

        s12.setBounds(218,193,160,100);
        s12.setForeground(Color.BLACK);
        s12.setFont(f2);
        s12.setOpaque(false);
        s12.setBorder(null);

        s13.setBounds(300,193,160,100);
        s13.setForeground(Color.BLACK);
        s13.setFont(f2);
        s13.setOpaque(false);
        s13.setBorder(null);

        s14.setBounds(420,193,160,100);
        s14.setForeground(Color.BLACK);
        s14.setFont(f2);
        s14.setOpaque(false);
        s14.setBorder(null);

        s21.setBounds(105,293,160,100);
        s21.setForeground(Color.BLACK);
        s21.setFont(f2);
        s21.setOpaque(false);
        s21.setBorder(null);

        s22.setBounds(218,293,160,100);
        s22.setForeground(Color.BLACK);
        s22.setFont(f2);
        s22.setOpaque(false);
        s22.setBorder(null);

        s23.setBounds(300,293,160,100);
        s23.setForeground(Color.BLACK);
        s23.setFont(f2);
        s23.setOpaque(false);
        s23.setBorder(null);

        s24.setBounds(420,293,160,100);
        s24.setForeground(Color.BLACK);
        s24.setFont(f2);
        s24.setOpaque(false);
        s24.setBorder(null);


        s31.setBounds(105,393,160,100);
        s31.setForeground(Color.BLACK);
        s31.setFont(f2);
        s31.setOpaque(false);
        s31.setBorder(null);

        s32.setBounds(218,393,160,100);
        s32.setForeground(Color.BLACK);
        s32.setFont(f2);
        s32.setOpaque(false);
        s32.setBorder(null);

        s33.setBounds(300,393,160,100);
        s33.setForeground(Color.BLACK);
        s33.setFont(f2);
        s33.setOpaque(false);
        s33.setBorder(null);

        s34.setBounds(420,393,160,100);
        s34.setForeground(Color.BLACK);
        s34.setFont(f2);
        s34.setOpaque(false);
        s34.setBorder(null);


        s41.setBounds(105,493,160,100);
        s41.setForeground(Color.BLACK);
        s41.setFont(f2);
        s41.setOpaque(false);
        s41.setBorder(null);

        s42.setBounds(218,493,160,100);
        s42.setForeground(Color.BLACK);
        s42.setFont(f2);
        s42.setOpaque(false);
        s42.setBorder(null);

        s43.setBounds(300,493,160,100);
        s43.setForeground(Color.BLACK);
        s43.setFont(f2);
        s43.setOpaque(false);
        s43.setBorder(null);

        s44.setBounds(420,493,160,100);
        s44.setForeground(Color.BLACK);
        s44.setFont(f2);
        s44.setOpaque(false);
        s44.setBorder(null);


        s51.setBounds(105,593,160,100);
        s51.setForeground(Color.BLACK);
        s51.setFont(f2);
        s51.setOpaque(false);
        s51.setBorder(null);

        s52.setBounds(218,593,160,100);
        s52.setForeground(Color.BLACK);
        s52.setFont(f2);
        s52.setOpaque(false);
        s52.setBorder(null);

        s53.setBounds(300,593,160,100);
        s53.setForeground(Color.BLACK);
        s53.setFont(f2);
        s53.setOpaque(false);
        s53.setBorder(null);

        s54.setBounds(420,593,160,100);
        s54.setForeground(Color.BLACK);
        s54.setFont(f2);
        s54.setOpaque(false);
        s54.setBorder(null);

        close.setSize(600,800);
        close.setContentAreaFilled(false);
        close.setOpaque(false);
        close.setBorderPainted(false);

        //添加各组件
        rfm.add(jp);
        rfm.setResizable(false);
        rfm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        rfm.setSize(a,b);
        rfm.setLocationRelativeTo(null);
        rfm.setVisible(true);
        jp.add(rkl);
        jp.add(id);
        jp.add(win);
        jp.add(lose);
        jp.add(wnr);
        jp.add(ONE);
        jp.add(TWO);
        jp.add(THREE);
        jp.add(FOUR);
        jp.add(FIVE);
        jp.add(s11);
        jp.add(s12);
        jp.add(s13);
        jp.add(s14);
        jp.add(s21);
        jp.add(s22);
        jp.add(s23);
        jp.add(s24);
        jp.add(s31);
        jp.add(s32);
        jp.add(s33);
        jp.add(s34);
        jp.add(s41);
        jp.add(s42);
        jp.add(s43);
        jp.add(s44);
        jp.add(s51);
        jp.add(s52);
        jp.add(s53);
        jp.add(s54);
        jp.add(close);

        close.addActionListener(e -> {
            rfm.dispose();
            RankingSystem.players.clear();
        });

    }
    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
