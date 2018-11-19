package primary.override;

public class Basic {
    public int i= 1 ;
    public void hello(){
        System.out.println("i am basic hello");
    }
    public static void staticHello(){
        System.out.println("i am basic staticHello");
    }

    public void newHello(){
        System.out.println("new hello");
        hello();
    }
}
