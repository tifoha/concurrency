package ua.tifoha;

/**
 * Created by Vitaliy Sereda on 25.08.17.
 */
public class IdGenerator implements IntGenerator {
	private int id = 0;

	private volatile boolean canceled = false;

	@Override
	public int next() {
		return id++;
	}

	@Override
	public boolean isCanceled() {
		return canceled;
	}

	@Override
	public void cancel() {
		canceled = true;
	}
}
