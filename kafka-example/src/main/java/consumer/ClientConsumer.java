package consumer;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import consumer.task.ConsumerTask;

public class ClientConsumer {

	public static void main(String[] args) {

		// Thread consumer = new Thread(new
		// ConsumerTask(Arrays.asList("ftopic")));
		// consumer.start();

		ExecutorService pools = Executors.newCachedThreadPool();
		pools.submit(new ConsumerTask(Arrays.asList("ftopic")));
		pools.shutdown();
	}
}
