package mutilProducer;

import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.kafka.clients.producer.KafkaProducer;

public class TestMutilProducer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Properties pro = new Properties();

		pro.setProperty("bootstrap.servers", "localhost:9092");
		pro.setProperty("client.id", "mutilProducer");
		pro.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		pro.setProperty("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		pro.setProperty("interceptor.classes", "mutilProducer.MyProducerInterceptor");
		KafkaProducer<String, String> producer = new KafkaProducer<String, String>(pro);

		ExecutorService executorService = Executors.newCachedThreadPool();
		for (int i = 1; i <= 5; i++) {
			executorService.submit(new ProducerTask(producer));
		}
		executorService.shutdown();

	}

}
