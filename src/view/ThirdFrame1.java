package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class ThirdFrame1 {
    JFrame frame = null;

    public static void main(String[] args) {
        ThirdFrame1 thirdFrame1 = new ThirdFrame1();
    }


    public ThirdFrame1() {
        // 创建窗口
        frame = new JFrame("Login");
        // 设置大小
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //创建容器
        JPanel panel = new JPanel();
        // 添加容器
        frame.add(panel);

        //添加组件
        placeComponents(panel);

        // 设置窗口可见
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    private void placeComponents(JPanel panel) {

        //设置布局
        panel.setLayout(null);


        // 创建标签
        JLabel jl1 = new JLabel("Player:");
        jl1.setBounds(10,20,80,25);
        panel.add(jl1);

        JLabel jl2 = new JLabel("Computer");
        jl2.setBounds(10,50,80,25);
        panel.add(jl2);

        //创建文本框
        JTextField jt1 = new JTextField(20);
        jt1.setBounds(100,20,165,25);
        panel.add(jt1);

        // 创建按钮


        JButton easyButton = new JButton("EASY");
        easyButton.setBounds(95,50,80,25);
        panel.add(easyButton);
        easyButton.addActionListener(e -> {
            GameFrame.a = 1;
        });


        JButton hardButton = new JButton("HARD");
        hardButton.setBounds(185,50,80,25);
        panel.add(hardButton);
        hardButton.addActionListener(e -> {
            GameFrame.a = 2;
        });

        JButton OKButton = new JButton("OK!");
        OKButton.setBounds(110, 105, 80, 25);
        panel.add(OKButton);
        OKButton.addActionListener(e -> {
            String sb1=jt1.getText();//获取文本框信息
            String sb2="Computer";
                System.out.println(sb1);
                GameFrame mainFrame = new GameFrame(800,sb1,sb2);
                mainFrame.setVisible(true);
                ThirdFrame1.this.frame.dispose();


        });
    }

}