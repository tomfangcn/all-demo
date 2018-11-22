package schedule.scheduleservice;

public class SCHED1 {
    public static void main(String[] args) {
        final long tiv = 2000l;
        new Thread(()->{
            while (true){
                System.out.println("SCHED1 fixed rate running");
                try {
                    Thread.sleep(tiv);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
