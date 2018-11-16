package Threads.CountDownLatch;


import java.util.concurrent.CountDownLatch;

/*
*Main thread start
*Create CountDownLatch for N threads
*Create and start N threads
*Main thread wait on latch
*N threads completes there tasks are returns
*Main thread resume execution
*/
public class CountDownLatchMain {

    private CountDownLatch countDownLatch = new CountDownLatch(3);


    public void service(){

        System.out.println("waiting thread 1 2 3 finish");

        new Thread1(countDownLatch).start();
        new Thread2(countDownLatch).start();
        new Thread3(countDownLatch).start();
//        new Thread4(countDownLatch).start();
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("get all info");

    }

    public static void main(String[] args) {

        new CountDownLatchMain().service();
    }
}
