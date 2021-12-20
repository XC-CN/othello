package view;

import controller.GameController;
import view.GameFrame;
import view.Music;
import view.SecondFrame;
import view.StatusPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;

public class OriginalFrame extends JFrame implements ActionListener {
    private static Font font;
    private static Font font2;

    //导入字体
    static {
        try {
            font = Font.createFont(Font.TRUETYPE_FONT,
                    new FileInputStream("FrozenNeutra.otf"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        font = font.deriveFont(Font.PLAIN, 30);
        font2 =font.deriveFont(Font.BOLD,40);
    }

    Font f1 = new Font("楷书", Font.BOLD, 25);

    //运行
    public static void main(String[] args) {
        new OriginalFrame();
    }

    public OriginalFrame() {
        Music music=new Music();
        music.run();
        JFrame fm = new JFrame();
        ImageIcon imageGif = new ImageIcon("ghost.gif");
        //把jl设置为底层背景
        ImageIcon bg = new ImageIcon("orifra.jpg");
        JLabel jl = new JLabel(bg);
        jl.setSize(bg.getIconWidth(), bg.getIconHeight());
        fm.getLayeredPane().add(jl, new Integer(Integer.MIN_VALUE));
        //gif添加
        JLabel jlGif = new JLabel(imageGif);
        jlGif.setOpaque(false);
        jlGif.setSize(50, 50);
        jlGif.setBounds(50, 50, 50, 50);

        //jp是表层透明玻璃板
        JPanel jp = (JPanel) fm.getContentPane();
        jp.setOpaque(false);
        jp.setLayout(null);


        //按钮初始化
        JButton newGame = new JButton("NewGame");
        JButton conti = new JButton("Continue");
        JButton rankingList = new JButton("RankingList");
        JLabel title = new JLabel("Let'sPlayOthello!");



        //“NewGame”Button
        newGame.setBounds(700, 200, 200, 70);//位置
        newGame.setFocusPainted(false);
        newGame.setBorder(null);
        newGame.setBorderPainted(false);
        newGame.setMargin(new Insets(0, 0, 0, 0));

        //“Continue"Button
        conti.setBounds(700, 350, 200, 70);
        conti.setFocusPainted(false);
        conti.setBorderPainted(false);
        conti.setBorder(null);
        conti.setBackground(null);
        conti.setFont(f1);


        //“Ranking List”Button
        rankingList.setBounds(700, 500, 200, 70);
        rankingList.setBorder(null);
        rankingList.setContentAreaFilled(true);
        rankingList.setMargin(new Insets(5, 5, 5, 5));

        title.setBounds(650,50,400,100);
        title.setForeground(Color.white);
        title.setBorder(null);
        title.setFont(font2);
        title.setOpaque(false);



        //设置鼠标监听
        newGame.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                music.stopp();
                System.out.println("You start a new game");
                SecondFrame frame = new SecondFrame();
                fm.dispose();
                //调用第二个界面
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                newGame.setBackground(Color.green);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                newGame.setBackground(Color.YELLOW);
            }
        });

        conti.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("You choose continue");
                String filePath = JOptionPane.showInputDialog(this,"insert the file name");
                GameFrame mainframe = new GameFrame(800,StatusPanel.BlackPlayer,StatusPanel.WhitePlayer);
                mainframe.setVisible(true);
                GameFrame.controller.readFileData(filePath);
                mainframe.getStatusPanel().setPlayerText(StatusPanel.BlackPlayer);
                fm.setVisible(false);

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                conti.setBackground(Color.green);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                conti.setBackground(Color.LIGHT_GRAY);
            }
        });

        rankingList.addActionListener(e -> {
            RankingListFrame rankingListFrame = new RankingListFrame(600,800);
        });

        //字体
        newGame.setFont(font);
        conti.setFont(font);
        rankingList.setFont(font);
        newGame.setForeground(Color.yellow);
        conti.setForeground(Color.yellow);
        rankingList.setForeground(Color.yellow);

        //取消边框
        newGame.setContentAreaFilled(false);
        conti.setContentAreaFilled(false);
        rankingList.setContentAreaFilled(false);

        //添加各组件
        jp.add(newGame);
        jp.add(conti);
        jp.add(rankingList);
        jp.add(title);
        fm.setResizable(false);
        fm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fm.setSize(bg.getIconWidth(), bg.getIconHeight());
        fm.setLocationRelativeTo(null);
        fm.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}



