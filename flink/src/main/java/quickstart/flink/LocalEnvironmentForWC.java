package quickstart.flink;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class LocalEnvironmentForWC {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		final StreamExecutionEnvironment env = StreamExecutionEnvironment.createLocalEnvironment();

		DataStream<Long> nums = env.generateSequence(1, 1000);
		// DataStream<String> text = env.fromElements("-4");
		env.setParallelism(1);
		DataStream<Long> vals = nums.map(new MapFunction<Long, Long>() {
			public Long map(Long value) {
				return Long.valueOf(value);
			}
		}).rebalance();

		// nums.map();
		vals.print().setParallelism(4);

		// IterativeStream<Long> iteration = vals.iterate();
		// DataStream<Long> iterationBody = iteration.map(new MapFunction<Long,
		// Long>() {
		//
		// public Long map(Long value) {
		// value = value - 1;
		// return value;
		// }
		// });
		// DataStream<Long> feedback = iterationBody.filter(new
		// FilterFunction<Long>() {
		// public boolean filter(Long value) throws Exception {
		// return value <= 0;
		// }
		// });
		//
		// iteration.closeWith(feedback);
		//
		// DataStream<Long> output = iterationBody;
		//
		// output.print();

		env.execute();
	}

}
