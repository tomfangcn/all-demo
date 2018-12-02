package threads.CountDownLatch;

import java.util.concurrent.CountDownLatch;

public class Thread1 extends Thread{

    CountDownLatch countDownLatch;
    public  Thread1(CountDownLatch countDownLatch){
        this.countDownLatch= countDownLatch;
    }
    @Override
    public void run() {


        try {
            Thread.sleep(1000);
            System.out.println("i am thread1 finish");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally{
            //写在finally 防止 异常出现死锁
            countDownLatch.countDown();
        }

    }
}
