package ua.tifoha;

import java.util.ArrayList;

/**
 * Created by Vitaliy Sereda on 25.08.17.
 */
public class CircularList<E> extends ArrayList<E> {
	private final int size;
	private int currentIndex;

	public CircularList(int size) {
		super(size);
		this.size = size;
	}

	@Override
	public synchronized boolean add(E e) {
		int index = currentIndex++ % size;
		super.add(index, e);
		return true;
	}
}
