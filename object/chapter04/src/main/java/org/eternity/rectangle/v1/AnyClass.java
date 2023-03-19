package org.eternity.rectangle.v1;

public class AnyClass {
	void enlarge(Rectangle rectangle, int multiple) {
		rectangle.setRight(rectangle.getRight() * multiple);
		rectangle.setBottom(rectangle.getBottom() * multiple);
	}
}
