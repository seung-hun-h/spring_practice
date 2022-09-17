package item07;

import java.util.Arrays;
import java.util.EmptyStackException;

public class Stack {
	private static final int DEFAULT_INITIAL_CAPACITY = 16;

	private Object[] elements;
	private int size = 0;

	public Stack() {
		elements = new Object[DEFAULT_INITIAL_CAPACITY];
	}

	public void push(Object object) {
		ensureCapacity();
		elements[size++] = object;
	}

	public Object pop() {
		if (size == 0) {
			throw new EmptyStackException();
		}
		Object result = elements[size];
		elements[size--] = null;
		return result;
	}

	private void ensureCapacity() {
		if (elements.length == size) {
			elements = Arrays.copyOf(elements, 2 * size + 1);
		}
	}

	@Override
	public Stack clone() {
		try {
			Stack result = (Stack)super.clone();
			result.elements = elements.clone();
			return result;
		} catch (CloneNotSupportedException e) {
			throw new AssertionError();
		}
	}
}
