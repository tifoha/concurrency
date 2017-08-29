package ua.tifoha;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Vitaliy Sereda on 22.08.17.
 */
public interface IntGenerator {
	int next();

	boolean isCanceled();

	void cancel();
}

class UnsynchronizedIntGenerator implements IntGenerator {
	private int value = 0;

	private volatile boolean canceled = false;

	public int next() {
//		value++;
//		Thread.yield();
//		value++;
		value +=2;

		return value;
	}

	public boolean isCanceled() {
		return canceled;
	}

	public void cancel() {
		canceled = true;
	}
}

class VolatileIntGenerator implements IntGenerator {
	private volatile int value = 0;

	private volatile boolean canceled = false;

	public int next() {
//		value++;
//		Thread.yield();
//		value++;
		value +=2;

		return value;
	}

	public boolean isCanceled() {
		return canceled;
	}

	public void cancel() {
		canceled = true;
	}
}

class SynchronizedIntGenerator implements IntGenerator {
	private int value = 0;

	private volatile boolean canceled = false;

	public synchronized int next() {
//		value++;
//		Thread.yield();
//		value++;
		value +=2;

		return value;
	}

	public boolean isCanceled() {
		return canceled;
	}

	public void cancel() {
		canceled = true;
	}
}

class AtomicIntGenerator implements IntGenerator {
	private AtomicInteger value = new AtomicInteger();

	private volatile boolean canceled = false;

	public int next() {
		return value.getAndAdd(2);
	}

	public boolean isCanceled() {
		return canceled;
	}

	public void cancel() {
		canceled = true;
	}
}



