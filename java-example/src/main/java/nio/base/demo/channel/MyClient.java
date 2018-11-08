package nio.base.demo.channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class MyClient {

    private SocketChannel client;


    public MyClient(String ip,int port){
        try {
            client = SocketChannel.open();
            client.configureBlocking(false);
            client.connect(new InetSocketAddress(ip,port));
            System.out.println("MyClient started!");
            while(true){
                if (!client.isConnected()){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    continue;
                }else{
                    System.out.println("MyClient is connected!");
                    break;
                }

            }
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            buffer.put("MyClient MSG".getBytes());
            client.write(buffer);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void sendMsg(String msg) throws IOException {

        while(!client.finishConnect()){

        }
        client.write(ByteBuffer.wrap(msg.getBytes()));

    }

    public String receiveMsg() throws IOException {
        String msg = null;
        ByteBuffer buffer  = ByteBuffer.allocate(1024);
        buffer.clear();
        StringBuilder sb = new StringBuilder();
        int count ;
       while (( count = client.read(buffer))>0){
           sb.append(new String(buffer.array(),0,count));
       }
       if (sb.length()>0){
           msg = sb.toString();
           if ("close".equals(msg)){
               msg = null;
               client.close();
               client.socket().close();
           }
       }
        return msg;
    }
    public static void main(String[] args) throws IOException {
        MyClient client = new MyClient("127.0.0.1",19900);

        client.sendMsg("hello world!");

        client.receiveMsg();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
