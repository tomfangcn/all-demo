package jvm.monitor;

public class MemoryCpuTest {


    public static void main(String[] args) throws InterruptedException {
        cpuFix();
    }

    public static void cpuFix() throws InterruptedException {
        int busyTime = 8;
        int idelTime = 2;
        long startTime = 0;
        while (true) {
            // 开始时间
            startTime = System.currentTimeMillis();

            /*
             * 运行时间
             */
            while (System.currentTimeMillis() - startTime < busyTime) {
                ;
            }

            // 休息时间
            Thread.sleep(idelTime);
        }
    }
}
