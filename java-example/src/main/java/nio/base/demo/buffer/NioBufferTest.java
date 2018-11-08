package nio.base.demo.buffer;

import java.nio.CharBuffer;

public class NioBufferTest {

    public static void main(String[] args) {
        CharBuffer buffer = CharBuffer.allocate(10);

        char b = buffer.get();
        System.out.println(b);
        buffer.put('1');
        char c = buffer.get();
        System.out.println(c);
        buffer.flip();
        char d = buffer.get();
        System.out.println(d);
        char e = buffer.get();
        System.out.println(e);

    }
}
