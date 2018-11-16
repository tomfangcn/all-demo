package Threads.CountDownLatch;

import java.util.concurrent.CountDownLatch;

public class Thread2 extends Thread{
    CountDownLatch countDownLatch;
    public  Thread2(CountDownLatch countDownLatch){
        this.countDownLatch= countDownLatch;
    }
    @Override
    public void run() {


        try {
            Thread.sleep(5000);
            System.out.println("i am thread2 finish");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally{
            countDownLatch.countDown();
        }


    }
}
