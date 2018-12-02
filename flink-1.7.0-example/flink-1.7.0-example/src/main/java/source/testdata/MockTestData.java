package source.testdata;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class MockTestData {

    private static final Logger LOG = LoggerFactory.getLogger(MockTestData.class);
    private static List<Order> orders = new ArrayList<>();

    public static List<Order> getOrders(int count,int pc){
        for (int i = 0 ; i <count ; i++){

            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Order tmp = new Order();
            tmp.setOrderId(i);
            tmp.setBuyVol(i*100);
            tmp.setBuyPrice(i*1.5);
            tmp.setDesc("产品"+i);
            tmp.setUid(i%pc);
            tmp.setUserName("顾客"+i%pc);
            tmp.setTimestamp(System.currentTimeMillis());
            LOG.warn(tmp.toString());
            orders.add(tmp);
        }
        return  orders;
    }
}
