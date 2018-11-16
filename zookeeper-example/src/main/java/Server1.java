import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.ACL;

import java.io.IOException;

public class Server1 {

    public static void main(String[] args) throws Exception {

//        if (args.length < 4) {
//            System.err.println("USAGE: Executor hostPort znode filename program [args ...]");
//            System.exit(2);
//        }
//        String hostPort = args[0];
//        String znode = args[1];
//        String filename = args[2];
//        String exec[] = new String[args.length - 3];
//        System.arraycopy(args, 3, exec, 0, exec.length);

        ZooKeeper zk = new ZooKeeper("127.0.0.1",2181,null);

        zk.create("/test","hello zookeeper1".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

//        zk.create("/test/ch","hello zookeeper2".getBytes(),null, CreateMode.PERSISTENT);
        zk.close();
    }
}
