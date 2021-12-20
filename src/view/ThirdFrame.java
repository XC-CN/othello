package view;

import rankinglist.Player;
import rankinglist.RankingSystem;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ThirdFrame {
    JFrame frame = null;

    public static void main(String[] args) {
        ThirdFrame thirdFrame = new ThirdFrame();
    }

   public static TimerTest timerTest = new TimerTest(500);
    public ThirdFrame() {
        // 创建窗口
        frame = new JFrame("Login Example");
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
        JLabel jl1 = new JLabel("Player1:");
        jl1.setBounds(10,20,80,25);
        panel.add(jl1);

        JLabel jl2 = new JLabel("Player2:");
        jl2.setBounds(10,50,80,25);
        panel.add(jl2);

        //创建文本框
        JTextField jt1 = new JTextField(20);
        jt1.setBounds(100,20,165,25);
        panel.add(jt1);

        JTextField jt2 = new JTextField(20);
        jt2.setBounds(100,50,165,25);
        panel.add(jt2);

        // 创建按钮
        JButton OKButton = new JButton("OK!");
        OKButton.setBounds(110, 105, 80, 25);
        panel.add(OKButton);
        OKButton.addActionListener(e -> {
            String sb1=jt1.getText();//获取文本框信息
            String sb2=jt2.getText();
            //rankinglist添加
            RankingSystem.addPlayer(sb1);
            RankingSystem.addPlayer(sb2);
            //
            GameFrame mainFrame = new GameFrame(800,sb1,sb2);
            timerTest.start();
            mainFrame.setVisible(true);
            ThirdFrame.this.frame.dispose();

        });
    }

}