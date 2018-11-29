package jvm.monitor;

public class DeadLock {

    public static void main(String[] args) {
        Resource r1 = new Resource();
        Resource r0 = new Resource();

        Thread myTh1 = new LockThread1(r1, r0);
        Thread myTh0 = new LockThread0(r1, r0);

        myTh1.setName("DeadLock-1 ");
        myTh0.setName("DeadLock-0 ");

        myTh1.start();
        myTh0.start();
    }
}
