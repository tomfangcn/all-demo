package mutilProducer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class ProducerTask implements Runnable {

	private KafkaProducer<String, String> producer;

	public ProducerTask(KafkaProducer<String, String> producer) {
		super();
		this.producer = producer;
	}

	public void run() {
		// TODO Auto-generated method stub

		for (int i = 0; i < 1000; i++) {
			long startTime = System.currentTimeMillis();
			String key = Thread.currentThread().getName() + i;
			String message = Thread.currentThread().getName() + "message" + i;
			producer.send(new ProducerRecord<String, String>("ftopic", key, message),
					new DemoCallBack(startTime, key, message));
		}

	}

}
