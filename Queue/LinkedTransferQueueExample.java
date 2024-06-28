package Queue;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

public class LinkedTransferQueueExample {

	public static void main(String[] args) {
		TransferQueue<String> transferQueue = new LinkedTransferQueue<>();

		// Producer thread
		Thread producer = new Thread(() -> {
			try {
				String[] messages = { "Message1", "Message2", "Message3" };
				for (String msg : messages) {
					System.out.println("Produced: " + msg);
					transferQueue.transfer(msg); // Will wait until a consumer receives the message
				}
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				System.err.println("Producer interrupted");
			}
		});

		// Consumer thread
		Thread consumer = new Thread(() -> {
			try {
				while (true) {
					String msg = transferQueue.take(); // Will wait until a producer sends a message
					System.out.println("Consumed: " + msg);
				}
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				System.err.println("Consumer interrupted");
			}
		});

		producer.start();
		consumer.start();

		try {
			producer.join();
			consumer.interrupt(); // Stop the consumer thread after producer finishes
			consumer.join();
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			System.err.println("Main thread interrupted");
		}
	}
}
