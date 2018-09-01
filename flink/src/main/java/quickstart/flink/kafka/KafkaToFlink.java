package quickstart.flink.kafka;

import java.util.Properties;

import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer010;

import quickstart.flink.redis.RedisSink;

public class KafkaToFlink {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
		Properties properties = new Properties();
		properties.setProperty("bootstrap.servers", "192.168.206.129:9092");
		// only required for Kafka 0.8
		properties.setProperty("zookeeper.connect", "192.168.206.129:2181");
		properties.setProperty("group.id", "test");
		DataStream<String> stream = env
				.addSource(new FlinkKafkaConsumer010<String>("test1", new SimpleStringSchema(), properties));
		stream.print();
		stream.addSink(new RedisSink());
		env.execute("Write into Redis");
	}

}
