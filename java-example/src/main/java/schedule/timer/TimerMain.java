package schedule.timer;

import org.junit.Test;

import java.util.Timer;
import java.util.TimerTask;

public class TimerMain {

    //第一种方法：设定指定任务task在指定时间time执行 schedule(TimerTask task, Date time)
//    @Test
    public void schedule1(){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("run schedule1 delay 1000");
            }
        },1000);
    }
    // 第二种方法：设定指定任务task在指定延迟delay后进行固定延迟peroid的执行
    public void schedule2(){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                System.out.println("run schedule2 delay 1000 peroid 1000");
            }
        }, 1000, 1000);
    }

    public void schedule3(){
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                System.out.println("run schedule3 delay 1000 peroid 1000");
            }
        }, 1000, 1000);
    }
    public static void main(String[] args) {
//        (new TimerMain()).schedule1();
//        (new TimerMain()).schedule2();
        (new TimerMain()).schedule3();
    }
}
