package nio.base.demo.channel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioChannelTest {
    public static void main(String[] args) throws Exception {
        File f= new File("D:\\file\\test.txt");
        FileInputStream inputStream = new FileInputStream(f);
        FileChannel fileChannel = inputStream.getChannel();

        ByteBuffer buffer  = ByteBuffer.allocate(1024);

        int i = fileChannel.read(buffer);
        while (i != -1){
            buffer.flip();
            while (buffer.hasRemaining()){
                System.out.print((char)buffer.get());
            }
            buffer.compact();
            i = fileChannel.read(buffer);
        }


    }
}
