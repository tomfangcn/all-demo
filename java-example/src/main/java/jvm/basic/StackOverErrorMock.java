package jvm.basic;

public class StackOverErrorMock {

    public long i = 0;

    public void call() {
        i++;
        call();
    }

    public static void main(String[] args) {
        StackOverErrorMock t = new StackOverErrorMock();
        try {
            t.call();
        } catch (StackOverflowError e) {
            e.printStackTrace();
            System.out.println(t.i);
        }
    }
}
