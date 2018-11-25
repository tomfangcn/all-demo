package utils;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class ZKConnection {

    private static ZooKeeper zk;
    private static CountDownLatch _latch = new CountDownLatch(1);

    private ZKConnection(){}

    public static ZooKeeper connect(String host, int port) throws IOException, InterruptedException{
        zk = new ZooKeeper(host, port, (we) -> {
            if (we.getState() == Watcher.Event.KeeperState.SyncConnected) _latch.countDown();
        });
        _latch.await();
        System.out.println("zk connected");
        return zk;
    }
}
