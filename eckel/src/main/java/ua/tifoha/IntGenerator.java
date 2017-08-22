package ua.tifoha;

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
		value++;
		Thread.yield();
		value++;

		return value;
	}

	public boolean isCanceled() {
		return canceled;
	}

	public void cancel() {
		canceled = true;
	}
}

