package jvm.monitor;

public class LockThread1 extends Thread {
    private Resource r1, r2;

    public LockThread1(Resource r1, Resource r2) {
        this.r1 = r1;
        this.r2 = r2;
    }

    @Override
    public void run() {
        int j = 0;
        while (true) {
            synchronized (r1) {
                System.out.println("The first thread got r1's lock " + j);
                synchronized (r2) {
                    System.out.println("The first thread got r2's lock  " + j);
                }
            }
            j++;
        }
    }
}
