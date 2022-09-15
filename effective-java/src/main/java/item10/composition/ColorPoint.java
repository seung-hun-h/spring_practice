package item10.composition;

public class ColorPoint {
	private final Point point;
	private final Color color;

	public ColorPoint(Point point, Color color) {
		this.point = point;
		this.color = color;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof ColorPoint cp)) {
			return false;
		}
		return cp.point.equals(point) && cp.color == color;
	}
}
