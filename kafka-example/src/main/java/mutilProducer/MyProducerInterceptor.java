package mutilProducer;

import java.util.Map;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

public class MyProducerInterceptor implements ProducerInterceptor<String, String> {

	public void configure(Map<String, ?> configs) {
		// TODO Auto-generated method stub

	}

	public ProducerRecord<String, String> onSend(ProducerRecord<String, String> record) {
		// TODO Auto-generated method stub
		if (record.key().contains("ignore")) {
			return null;
		}
		return record;
	}

	public void onAcknowledgement(RecordMetadata metadata, Exception exception) {
		// TODO Auto-generated method stub

		if (metadata != null && metadata.topic().equalsIgnoreCase("ftopic")) {
			System.out.println("Get the ack of ftopic !");
		}

	}

	public void close() {
		// TODO Auto-generated method stub

	}

}
