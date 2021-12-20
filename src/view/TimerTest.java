package view;
import javax.swing.*;
import java.awt.*;

public class TimerTest extends Thread{
    public static int time;
    private Thread t;
    public static boolean aa=true;
    public TimerTest(int number){
        this.time=number;
    }

    public  void run() {
        int refresh=time;
        JFrame a = new JFrame();//窗口
        a.setSize(300, 100);
        JPanel b=new JPanel();//容器
        b.setSize(300,200);
        JLabel jl=new JLabel();//标签
        jl.setSize(100,50);
        jl.setFont(new Font("宋体",Font.BOLD,40));
        b.add(jl);
        a.add(b,BorderLayout.CENTER);
//        a.setLocationRelativeTo(null);//设置窗口居中
        a.setLocation(100,150);
        a.setVisible(true);
        //倒计十秒

        while(aa){int w=0;
            String mTime=time%100+"";
            if (time%100<10){mTime="0"+""+time%100;}
            jl.setText(time/100+"."+mTime+"s");
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            time--;
            if(time<0){
                jl.setText("time out!");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                jl.setText("Next Player!");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                GameFrame.controller.swapPlayer();
                GameFrame.controller.getGamePanel().chessGrids[1][1].reTips();
                GameFrame.controller.getGamePanel().chessGrids[1][1].tips();
                time=refresh;

            }
        }
        a.dispose();
    }


    public void start () {
        if (t == null) {
            t = new Thread (this, String.valueOf(time));
            t.start ();
        }
    }


    public static void main(String[] args) throws InterruptedException {
        TimerTest a=new TimerTest(500);
        a.run();
    }
}