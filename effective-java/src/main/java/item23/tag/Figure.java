package item23.tag;

public class Figure {
	enum Shape {RECTANGLE, CIRCLE,}

	// 태그 필드 - 현재 모양을 나타낸다
	final Shape shape;

	// 모양이 사각형일 때만 쓰인다
	double length;
	double width;

	// 모양이 원일 떄만 쓰인다
	double radius;

	// 원용 생성자
	public Figure(double radius) {
		this.shape = Shape.CIRCLE;
		this.radius = radius;
	}

	// 사각형용 생성자
	public Figure(double length, double width) {
		this.shape = Shape.RECTANGLE;
		this.length = length;
		this.width = width;
	}

	double area() {
		return switch (shape) {
			case CIRCLE -> Math.PI * (radius * radius);
			case RECTANGLE -> length * width;
		};
	}
}
