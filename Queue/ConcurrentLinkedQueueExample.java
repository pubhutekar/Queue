package Queue;

import java.util.concurrent.ConcurrentLinkedQueue;

public class ConcurrentLinkedQueueExample {
	public static void main(String[] args) {

		ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();

		queue.add("First");
		queue.add("Second");
		queue.add("Third");

		System.out.println("Queue: " + queue);

		String head = queue.peek();
		System.out.println("Head of the queue: " + head);

		String removedElement = queue.poll();
		System.out.println("Removed Element: " + removedElement);
		System.out.println("Queue after polling: " + queue);

		boolean containsSecond = queue.contains("Second");
		System.out.println("Queue contains 'Second': " + containsSecond);

		System.out.println("Iterating over queue elements:");
		for (String element : queue) {
			System.out.println(element);
		}
	}
}
