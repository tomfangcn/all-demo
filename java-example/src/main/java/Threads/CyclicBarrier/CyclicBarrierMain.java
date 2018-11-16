package Threads.CyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierMain {

    private CyclicBarrier cyclicBarrier = new CyclicBarrier(4);

    public void doSomething(){
        new SubThread1(cyclicBarrier).start();
        new SubThread2(cyclicBarrier).start();
        new SubThread3(cyclicBarrier).start();
        System.out.println("CyclicBarrierMain arrive barrier");
        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println("CyclicBarrierMain is doSomething");
    }

    public static void main(String[] args) {
        new CyclicBarrierMain().doSomething();
    }
}
