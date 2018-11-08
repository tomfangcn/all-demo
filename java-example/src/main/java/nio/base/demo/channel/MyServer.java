package nio.base.demo.channel;

import nio.Server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class MyServer {

    private ServerSocketChannel channel;
    private Selector selector;
    private InetSocketAddress ipAndPort;

    public MyServer(String ip, int port) {

        try {
            channel = ServerSocketChannel.open();
            channel.bind(new InetSocketAddress(ip, port));
            channel.configureBlocking(false);
            selector = Selector.open();
            channel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public void start() {
        System.out.println("MyServer is started!");
        try {
            while (true) {

                int size =selector.select(1000);
                if (size==0){
                    continue;
                }
                Iterator<SelectionKey> it = selector.selectedKeys().iterator();
                while (it.hasNext()){
                    SelectionKey key = it.next();

                    if (key.isAcceptable()){
                        ServerSocketChannel server = (ServerSocketChannel)key.channel();
                        SocketChannel client = server.accept();
                        System.out.println("Remote Client ip : "+client.getRemoteAddress());

                        client.configureBlocking(false);
                        client.register(selector,SelectionKey.OP_READ);
                    }

                    if (key.isReadable()){

                        SocketChannel client = (SocketChannel)key.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        int i = client.read(buffer);
                        while(i!=-1){
                            buffer.flip();
                            while(buffer.hasRemaining()){
                                System.out.print((char) buffer.get());
                            }
                            buffer.clear();
                            i=client.read(buffer);
                        }


                    }

                    if (key.isWritable()){

                    }

                    it.remove();

                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        MyServer server = new MyServer("127.0.0.1",19900);
        server.start();

    }
}
