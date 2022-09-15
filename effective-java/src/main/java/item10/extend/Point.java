package item10.extend;

public class Point {
	private final int x;
	private final int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Point p)) {
			return false;
		}
		return p.x == this.x && p.y == this.y;
	}
}
