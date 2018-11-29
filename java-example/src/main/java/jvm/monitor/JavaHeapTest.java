package jvm.monitor;

public class JavaHeapTest {
    private final static int  OUTOFMEMORY = 2000000000;

    private String oom;

    private int length;

    StringBuffer tmpOOM = new StringBuffer();

    public JavaHeapTest(int length){
        this.length = length;

        int i = 0 ;
        while(i < length){
            i++;
            try{
                tmpOOM.append("a");
            }catch (OutOfMemoryError e){
                e.printStackTrace();
                break;
            }

        }
        this.oom = tmpOOM.toString();
    }

    public String getOom(){
        return oom;
    }

    public int getLenth(){
        return length;
    }

    public static void main(String[] args) {
        JavaHeapTest javaHeapTest = new JavaHeapTest(OUTOFMEMORY);
        System.out.println(javaHeapTest.getOom().length());
    }
}
