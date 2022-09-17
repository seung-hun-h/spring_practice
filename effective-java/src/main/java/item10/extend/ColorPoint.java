package item10.extend;

public class ColorPoint extends Point {
	private final Color color;

	public ColorPoint(int x, int y, Color color) {
		super(x, y);
		this.color = color;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Point)) {
			return false;
		}

		// obj가 일반 Point면 색상을 무시하고 비교한다
		if (!(obj instanceof ColorPoint p)) {
			return obj.equals(this);
		}

		return super.equals(obj) && p.color == this.color;
	}

	@Override
	public ColorPoint clone() {
		try {
			return (ColorPoint)super.clone();
		} catch (CloneNotSupportedException e) {
			throw new AssertionError();
		}
	}
}
