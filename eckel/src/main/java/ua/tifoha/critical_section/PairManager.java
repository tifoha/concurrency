package ua.tifoha.critical_section;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

/**
 * Created by Vitaliy Sereda on 26.08.17.
 */
public abstract class PairManager {
	protected Pair prototype = new Pair();

	private Collection<Pair> storage = Collections.synchronizedCollection(new ArrayList<>());

	public Pair getNewPair() {
		return new Pair(prototype.getX(), prototype.getY());
	}

	public abstract Pair increment();

	public void store(Pair pair) {
		storage.add(pair);

		try {
			TimeUnit.MILLISECONDS.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class UnsynchronysedPairManager extends PairManager{

	@Override
	public Pair increment() {
		Pair pair = getNewPair();
		prototype.incrementX();
		prototype.incrementY();
		store(pair);
		return pair;
	}
}

class SynchronizedPairManager extends UnsynchronysedPairManager {

	@Override
	public synchronized Pair increment() {
		return super.increment();
	}
}

class EnhancedPairManager extends PairManager {
	@Override
	public Pair increment() {
		Pair pair;
		synchronized (this) {
			pair = getNewPair();
			prototype.incrementX();
			prototype.incrementY();
		}
		store(pair);
		return pair;
	}
}