package job;

import job.function.MaxTimeReduceFn;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.assigners.TumblingProcessingTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import source.testdata.MockTestData;
import source.testdata.Order;

public class TestJob {
    private static final Logger LOG = LoggerFactory.getLogger(TestJob.class);

    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

//        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);

        DataStreamSource<Order> source = env.fromCollection(MockTestData.getOrders(100, 5));

        source.map(s -> {
//            System.out.println(s.getOrderId()+":"+s.getDesc());
            return s;
        }).keyBy(e -> e.getUid())
                .window(TumblingProcessingTimeWindows.of(Time.milliseconds(1000)))
                //e2.getTimestamp()?e1.getUid()+e1.getOrderId():e2.getUid()+e2.getOrderId()
//                .reduce((e1, e2) -> e1.getTimestamp() >= e2.getTimestamp() ? e1 : e2)

                .reduce(new MaxTimeReduceFn())
                .map(s -> {
                    LOG.warn(s.toString());
                    return s.getUid() + ":" + s.getOrderId();
                });


        env.execute("Flink Streaming Java API Skeleton");
    }
}
