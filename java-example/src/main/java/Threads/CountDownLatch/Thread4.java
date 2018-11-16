package Threads.CountDownLatch;

import java.util.concurrent.CountDownLatch;

public class Thread4 extends Thread{
    CountDownLatch countDownLatch;
    public Thread4(CountDownLatch countDownLatch){
        this.countDownLatch= countDownLatch;
    }
    @Override
    public void run() {

        try {
            Thread.sleep(15000);
            System.out.println("i am thread4 finish");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            countDownLatch.countDown();
        }


    }
}
