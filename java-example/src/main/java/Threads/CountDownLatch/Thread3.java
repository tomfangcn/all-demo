package Threads.CountDownLatch;

import java.util.concurrent.CountDownLatch;

public class Thread3 extends Thread{
    CountDownLatch countDownLatch;
    public  Thread3(CountDownLatch countDownLatch){
        this.countDownLatch= countDownLatch;
    }
    @Override
    public void run() {

        try {
            Thread.sleep(10000);
            System.out.println("i am thread3 finish");
            throw new NullPointerException();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally{
            countDownLatch.countDown();
        }



    }
}
