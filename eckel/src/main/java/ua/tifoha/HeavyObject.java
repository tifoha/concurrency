package ua.tifoha;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by Vitaliy Sereda on 26.08.17.
 */
public abstract class HeavyObject {
	protected long[] array = new long[10 * 1024 * 1024];

	abstract public long get(int index);

	abstract public void set(int index, long value);
}

class SynchronyzedObject extends HeavyObject {

	@Override
	public synchronized long get(int index) {
		return array[index];
	}

	@Override
	public synchronized void set(int index, long value) {
		array[index] = value;
	}
}

class SynchronyzedByObject extends HeavyObject {
	private final Object lock = new Object();
	@Override
	public long get(int index) {
		synchronized (lock) {
			return array[index];
		}
	}

	@Override
	public void set(int index, long value) {
		synchronized (lock) {
			array[index] = value;
		}
	}
}

class LockedObject extends HeavyObject {
	ReentrantLock lock = new ReentrantLock();
	@Override
	public long get(int index) {
		lock.lock();
		try {
			return array[index];
		} finally {
			lock.unlock();
		}
	}

	@Override
	public void set(int index, long value) {
		lock.lock();
		try {
			array[index] = value;
		} finally {
			lock.unlock();
		}
	}
}

class RWLockedObject extends HeavyObject {
	private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	@Override
	public long get(int index) {
		Lock lock = readWriteLock.readLock();
//		lock.lock();
		try {
			return array[index];
		} finally {
			lock.unlock();
		}
	}

	@Override
	public void set(int index, long value) {
		Lock lock = readWriteLock.writeLock();
//		lock.lock();
		try {
			array[index] = value;
		} finally {
			lock.unlock();
		}
	}
}