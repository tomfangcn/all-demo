package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Iterator;
import java.util.Set;

public class Server implements Runnable {
    private ServerSocketChannel server;
    private Selector selector;
    private SelectionKey selectionKey;

    private volatile boolean isRun;

    private void init() throws IOException {
        server = ServerSocketChannel.open();
        server.socket().bind(new InetSocketAddress(19900));
        server.configureBlocking(false);

        selector = Selector.open();
        selectionKey = server.register(selector, SelectionKey.OP_ACCEPT);

        System.out.println("Server start ...");
    }

    @Override
    public void run() {

        try {
            while (isRun) {
                int n = selector.select();
                if (n > 0) {
                    Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
                    while (iter.hasNext()){
                        SelectionKey key = iter.next();
                        if (key.isAcceptable()){
                            iter.remove();
                            ServerSocketChannel serverChannel = (ServerSocketChannel)key.channel();
                            SocketChannel channel = serverChannel.accept();
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {

    }

}
