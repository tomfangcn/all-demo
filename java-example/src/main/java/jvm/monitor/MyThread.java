package jvm.monitor;

public class MyThread extends Thread{

    public static void main(String[] args) {

        MyThread mt1 = new MyThread();
        MyThread mt2 = new MyThread();

        mt1.setName("My-Thread-1 ");
        mt2.setName("My-Thread-2 ");

        mt1.start();
        mt2.start();
    }


    @Override
    public void run() {

        while (true) {

        }
    }
}
