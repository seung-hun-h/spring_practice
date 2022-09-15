package item10.extend;

public class SmellPoint extends Point {
	private final Smell smell;

	public SmellPoint(int x, int y, Smell smell) {
		super(x, y);
		this.smell = smell;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Point)) {
			return false;
		}

		if (!(obj instanceof SmellPoint p)) {
			return obj.equals(this);
		}

		return super.equals(obj) && p.smell == this.smell;
	}
}
