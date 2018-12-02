package job.function.richfunction;

import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.api.common.functions.RichReduceFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import source.testdata.Order;

public class MaxTimeRichReduceFn extends RichReduceFunction<Order> {
    private static final Logger LOG = LoggerFactory.getLogger(MaxTimeRichReduceFn.class);


    @Override
    public Order reduce(Order order, Order t1) throws Exception {
        LOG.warn(getRuntimeContext().getTaskName());
        LOG.warn(getRuntimeContext().getTaskNameWithSubtasks());
        return order.getTimestamp() >= t1.getTimestamp() ? order : t1;
    }
}
