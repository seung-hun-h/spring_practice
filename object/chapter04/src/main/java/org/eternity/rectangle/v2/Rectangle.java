package org.eternity.rectangle.v2;

public class Rectangle {
	private int left;
	private int top;
	private int right;
	private int bottom;

	public void enlarge(int multiple) {
		right *= multiple;
		bottom *= multiple;
	}
}
