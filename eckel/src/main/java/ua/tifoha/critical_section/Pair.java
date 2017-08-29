package ua.tifoha.critical_section;

/**
 * Created by Vitaliy Sereda on 26.08.17.
 */
public class Pair {
	private int x;

	private int y;

	public Pair() {
	}

	public Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void incrementX() {
		x++;
	}

	public void incrementY() {
		y++;
	}

	public boolean isValid() {
		return x == y;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("P{")
		.append(x)
		.append(y)
		.append('}');
		return sb.toString();
	}
}
