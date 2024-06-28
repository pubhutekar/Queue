package Queue;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

class DelayedElement implements Delayed {
	private final String element;
	private final long expiryTime;

	public DelayedElement(String element, long delay, TimeUnit unit) {
		this.element = element;
		this.expiryTime = System.currentTimeMillis() + unit.toMillis(delay);
	}

	@Override
	public long getDelay(TimeUnit unit) {
		long diff = expiryTime - System.currentTimeMillis();
		return unit.convert(diff, TimeUnit.MILLISECONDS);
	}

	@Override
	public int compareTo(Delayed other) {
		if (this.expiryTime < ((DelayedElement) other).expiryTime) {
			return -1;
		}
		if (this.expiryTime > ((DelayedElement) other).expiryTime) {
			return 1;
		}
		return 0;
	}

	@Override
	public String toString() {
		return element + ": " + expiryTime;
	}
}

public class DelayQueueExample {
	public static void main(String[] args) throws InterruptedException {
		DelayQueue<DelayedElement> queue = new DelayQueue<>();

		queue.put(new DelayedElement("Element1", 5, TimeUnit.SECONDS));
		queue.put(new DelayedElement("Element2", 3, TimeUnit.SECONDS));
		queue.put(new DelayedElement("Element3", 10, TimeUnit.SECONDS));

		System.out.println("Elements added to the DelayQueue");

		while (!queue.isEmpty()) {
			DelayedElement element = queue.take();
			System.out.println("Processing: " + element);
		}
	}
}
