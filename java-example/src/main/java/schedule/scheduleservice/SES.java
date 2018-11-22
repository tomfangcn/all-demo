package schedule.scheduleservice;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SES {

    static void schedule1(){
        ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
        ses.scheduleAtFixedRate(()->{
            System.out.println("schedule1 running");
        },3,1, TimeUnit.SECONDS);
    }

    static void schedule2() throws InterruptedException {
        ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
        ses.schedule(()->{
            System.out.println("schedule2 running");
        },3, TimeUnit.SECONDS);
        ses.shutdown();
    }

    static void schedule3(){
        ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
        ses.scheduleWithFixedDelay(()->{
            System.out.println("schedule31 running");
        },5,3, TimeUnit.SECONDS);
        ses.scheduleWithFixedDelay(()->{
            System.out.println("schedule32 running");
        },5,3, TimeUnit.SECONDS);
    }

    static void schedule4(){
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(2);
        ses.scheduleWithFixedDelay(()->{
            System.out.println("schedule41 running");
        },5,3, TimeUnit.SECONDS);
        ses.scheduleWithFixedDelay(()->{
            System.out.println("schedule42 running");
        },5,3, TimeUnit.SECONDS);

    }
    public static void main(String[] args) throws InterruptedException {

//        SES.schedule1();
        SES.schedule2();
//        SES.schedule3();
//        SES.schedule4();

    }

}
