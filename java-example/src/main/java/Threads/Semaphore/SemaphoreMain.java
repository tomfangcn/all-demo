package Threads.Semaphore;

import java.sql.Time;
import java.util.concurrent.*;

public class SemaphoreMain {

    //公平模式
//    Semaphore semaphore = new Semaphore(3,true);
    //非公平模式，若是 非公平模式 是否会发生，A线程抢到1许可证，B线程抢到2个许可证，导致死锁
    Semaphore semaphore = new Semaphore(3, false);

//使用3线程测试 公平模式，测试功能
//    public SemaphoreMain(){
//        new Thread(()->{try {
//            System.out.println("enter thread1");
//            semaphore.acquire(1);
//            System.out.println("thread1 doSomething");
//            Thread.sleep(2000);
//            System.out.println("thread1 finish");
//            semaphore.release(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }}).start();
//
//        new Thread(()->{try {
//            System.out.println("enter thread2");
//            semaphore.acquire(3);
//            System.out.println("thread2 doSomething");
//            Thread.sleep(4000);
//            System.out.println("thread2 finish");
//            semaphore.release(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }}).start();
//    }

    //使用20线程测试 非公平模式，是否会发生死锁
    public SemaphoreMain() {
        ExecutorService exec = Executors.newFixedThreadPool(20);
        for (int i = 0; i < 20; i++) {

            int finalI = i;
            exec.submit(() -> {
                try {

                    System.out.println("enter thread" + finalI);
                    semaphore.acquire(3);
                    System.out.println("thread" + finalI + "+doSomething");
                    Thread.sleep(2000);
                    System.out.println("thread" + finalI + " finish");
                    semaphore.release(3);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            });
        }

        exec.shutdown();

        try {
            exec.awaitTermination(6000000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
        public void doSomething () {

            try {
                System.out.println("enter SemaphoreMain");
                //虽然许可证不够，但是还是会先消费2个许可证
                semaphore.acquire(3);
                Thread.sleep(6000);
                System.out.println("SemaphoreMain finish");
                semaphore.release(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("SemaphoreMain do something");

        }

        public static void main (String[]args){
//            new SemaphoreMain().doSomething();
            new SemaphoreMain();
        }
    }
