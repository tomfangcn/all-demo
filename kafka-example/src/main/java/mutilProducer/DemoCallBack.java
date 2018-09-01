package mutilProducer;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;

public class DemoCallBack implements Callback {

	private final long startTime;
	private final String key;
	private final String message;

	public DemoCallBack(long startTime, String key, String message) {
		super();
		this.startTime = startTime;
		this.key = key;
		this.message = message;
	}

	public void onCompletion(RecordMetadata metadata, Exception exception) {
		// TODO Auto-generated method stub

		long elapsedTime = System.currentTimeMillis() - startTime;

		if (null != metadata) {
			System.out.println("key=" + key + ",message=" + message + ",partition=" + metadata.partition() + ",offset="
					+ metadata.offset() + ",elapsedTime=" + elapsedTime);
		} else {
			exception.printStackTrace();
		}

	}

}
