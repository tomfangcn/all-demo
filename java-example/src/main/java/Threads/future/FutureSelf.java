package Threads.future;

public class FutureSelf implements Runnable{
    private volatile String  result;
    private volatile String  state ;
    public FutureSelf(){
        this.state = "NEW";
    }
    public String get(){
        while(true){
            if (state().equals("COMPLETED")){
               return result;
            }
        }
    }
    public String state(){
        return state;
    }

    @Override
    public void run() {
        if (!state.equals("NEW")){return;}
        System.out.println("从数据库取数据。。。");
        try {
            state="COMPLETING";
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        result = "mysqldata";
        state="COMPLETED";
    }

    public static void main(String[] args) {
        FutureSelf r = new FutureSelf();
        Thread t = new Thread(r);
        t.start();
        System.out.println("主线程开始做事");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("get :"+ r.get());

        }


}
