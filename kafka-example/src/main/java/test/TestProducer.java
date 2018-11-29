package test;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.PartitionInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestProducer {
	public static void main(String[] args) {

		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9092");
		props.put("acks", "all");
		props.put("retries", 0);
		props.put("batch.size", 16384);
		props.put("linger.ms", 1);
		props.put("buffer.memory", 33554432);
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		String topic = "topic01";
		Producer<String, String> procuder = new KafkaProducer<String, String>(props);
		for (int i = 1; i <= 10; i++) {
			String value = "value_" + i;
			ProducerRecord<String, String> msg = new ProducerRecord<String, String>(topic, value);
			procuder.send(msg);
		}
		List<PartitionInfo> partitions = new ArrayList<PartitionInfo>();
		partitions = procuder.partitionsFor(topic);
		for (PartitionInfo p : partitions) {
			System.out.println(p);
		}

		System.out.println("send message over.");
		procuder.close(100, TimeUnit.MILLISECONDS);
	}
}
