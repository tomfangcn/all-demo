package Threads.CyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class SubThread1 extends  Thread{
    private CyclicBarrier cyclicBarrier;
    public SubThread1(CyclicBarrier cyclicBarrier){
        this.cyclicBarrier=cyclicBarrier;
    }

    @Override
    public void run() {


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("SubThread1 arrive barrier");
        try {
            System.out.println("1");
            //若程序抛出异常如何处理
            //TODO
            int i = 9/0;
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }catch (Exception e){
//            cyclicBarrier.reset();
        }

        System.out.println("SubThread1 do other");
    }
}
