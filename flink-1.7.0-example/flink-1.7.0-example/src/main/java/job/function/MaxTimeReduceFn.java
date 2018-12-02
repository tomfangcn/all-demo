package job.function;

import org.apache.flink.api.common.functions.ReduceFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import source.testdata.Order;

public class MaxTimeReduceFn implements ReduceFunction<Order> {
    private static final Logger LOG = LoggerFactory.getLogger(MaxTimeReduceFn.class);


    @Override
    public Order reduce(Order order, Order t1) throws Exception {

        return order.getTimestamp() >= t1.getTimestamp() ? order : t1;
    }
}
