package item34;

public enum Operation {
	PLUS, MINUS, TIMES, DIVIDE;

	public double apply(double x, double y) {
		switch (this) {
			case PLUS -> {
				return x + y;
			}
			case MINUS -> {
				return x - y;
			}
			case TIMES -> {
				return x * y;
			}
			case DIVIDE -> {
				return x / y;
			}
			default -> throw new AssertionError("지원하지 않는 연산입니다." + this);
		}
	}
}
