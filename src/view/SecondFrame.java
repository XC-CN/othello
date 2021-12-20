package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;

public class SecondFrame extends JFrame implements ActionListener {
    private static Font font;

    //导入字体
    static {
        try {
            font = Font.createFont(Font.TRUETYPE_FONT,
                    new FileInputStream("FrozenNeutra.otf"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        font = font.deriveFont(Font.PLAIN, 30);
    }

    Font f1 = new Font("楷书", Font.BOLD, 25);

    public static void main(String[] args) {new SecondFrame();
    }

    public SecondFrame() {
        JFrame fm = new JFrame();
        ImageIcon imageGif = new ImageIcon("bg.gif");
        //把jl设置为底层背景
//        ImageIcon bg = new ImageIcon("bg.jpg");
        //jp是表层透明玻璃板
        bgPanel jp = new bgPanel(800,800);
        jp.setOpaque(false);
        jp.setLayout(null);

        // 按钮初始化
        JButton PVP = new JButton("PVP");
        JButton PVC = new JButton("PVC");
        ImageIcon theme1 = new ImageIcon("theme1.jpg");
        ImageIcon theme2 = new ImageIcon("theme2.jpg");
        ImageIcon theme3 = new ImageIcon("theme3.jpg");
        ImageIcon theme4 = new ImageIcon("theme4.jpg");
        JLabel THEME1 = new JLabel(theme1);
        JLabel THEME2 = new JLabel(theme2);
        JLabel THEME3 = new JLabel(theme3);
        JLabel THEME4 = new JLabel(theme4);
        JLabel select = new JLabel("THEME SELECTION");
        JButton t1 = new JButton();
        JButton t2 = new JButton();
        JButton t3 = new JButton();
        JButton t4 = new JButton();


        //"PVP"与"PVC"按钮设置
        PVP.setBounds(550, 350, 200, 70);//位置
        PVP.setFocusPainted(false);
        PVP.setBorder(null);
        PVP.setBorderPainted(false);
        PVP.setMargin(new Insets(0, 0, 0, 0));
        PVP.setFont(font);
        PVP.setForeground(Color.darkGray);
        PVP.setContentAreaFilled(false);


        PVC.setBounds(550, 450, 200, 70);//位置
        PVC.setFocusPainted(false);
        PVC.setBorder(null);
        PVC.setBorderPainted(false);
        PVC.setMargin(new Insets(0, 0, 0, 0));
        PVC.setFont(font);
        PVC.setForeground(Color.darkGray);
        PVC.setContentAreaFilled(false);

        //主题按钮设置
        select.setBounds(250,1,400,200);
        select.setOpaque(true);
        select.setBorder(null);
        select.setFont(font);





        THEME1.setBounds(68, 213, 200, 200);
        THEME1.setOpaque(true);
        THEME1.setBorder(null);
        THEME1.setIcon(new ImageIcon("theme1.jpg"));

        THEME2.setBounds(68, 500, 200, 200);
        THEME2.setOpaque(true);
        THEME2.setBorder(null);
        THEME2.setIcon(new ImageIcon("theme2.jpg"));


        THEME3.setBounds(318, 213, 200, 200);
        THEME3.setOpaque(true);
        THEME3.setBorder(null);
        THEME3.setIcon(new ImageIcon("theme3.jpg"));

        THEME4.setBounds(318,500,200,200);
        THEME4.setOpaque(true);
        THEME4.setBorder(null);
        THEME4.setIcon(new ImageIcon("theme4.jpg"));



        ImageIcon image = new ImageIcon("button.jpg");
        ImageIcon image1 = new ImageIcon("button1.jpg");
        ImageIcon image2 = new ImageIcon("forbid.jpg");
        t1.setBounds(148,430,50,50);
        t2.setBounds(148,713,50,50);
        t3.setBounds(388,430,50,50);
        t4.setBounds(388,713,50,50);
        t1.setIcon(image);
        t2.setIcon(image);
        t3.setIcon(image);
        t4.setIcon(image);






        //监听
        t1.addActionListener(e -> {
            t1.setIcon(image1);
            t2.setIcon(image2);
            t3.setIcon(image2);
            t4.setIcon(image2);
            GameFrame.cc=0;
        });


        t2.addActionListener(e -> {
            t2.setIcon(image1);
            t1.setIcon(image2);
            t3.setIcon(image2);
            t4.setIcon(image2);
            GameFrame.cc=1;
        });

        t3.addActionListener(e -> {
            t3.setIcon(image1);
            t1.setIcon(image2);
            t2.setIcon(image2);
            t4.setIcon(image2);
            GameFrame.cc=2;
        });

        t4.addActionListener(e -> {
            t4.setIcon(image1);
            t1.setIcon(image2);
            t2.setIcon(image2);
            t3.setIcon(image2);
            GameFrame.cc=3;
        });

        PVC.addActionListener(e -> {
//            GameFrame.a=1;
            ThirdFrame1 thirdFrame1 = new ThirdFrame1();
            fm.dispose();


        });

        PVP.addActionListener(e -> {
            GameFrame.a=0;
            ThirdFrame thirdFrame = new ThirdFrame();
            fm.dispose();
        });















//添加各组件
        jp.add(PVP);
        jp.add(PVC);
        jp.add(THEME1);
        jp.add(THEME2);
        jp.add(THEME3);
        jp.add(THEME4);
        jp.add(select);
        jp.add(t1);
        jp.add(t2);
        jp.add(t3);
        jp.add(t4);
        fm.add(jp);
        fm.setResizable(false);
        fm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fm.setSize(800, 800);
        fm.setLocationRelativeTo(null);
        fm.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}