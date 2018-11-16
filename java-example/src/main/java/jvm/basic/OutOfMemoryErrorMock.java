package jvm.basic;

import java.util.ArrayList;
import java.util.List;

public class OutOfMemoryErrorMock {
    public static void main(String[] args) {
        List<byte[]> list = new ArrayList<byte[]>();

        int i = 0;
        boolean flag = true;
        while (flag) {
            try {
                i++;
                list.add(new byte[1024 * 1024]);
            } catch (OutOfMemoryError e) {
                e.printStackTrace();
                flag = false;
                System.out.println("count :" + i);
            }
        }
        list.clear();
    }
}
