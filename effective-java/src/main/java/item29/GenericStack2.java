package item29;

import java.util.Arrays;
import java.util.EmptyStackException;

public class GenericStack2<E> {
	private static final int DEFAULT_INITIAL_CAPACITY = 16;

	private Object[] elements;
	private int size = 0;

	public GenericStack2() {
		elements = new Object[DEFAULT_INITIAL_CAPACITY];
	}

	public void push(Object object) {
		ensureCapacity();
		elements[size++] = object;
	}

	public E pop() {
		if (size == 0) {
			throw new EmptyStackException();
		}
		@SuppressWarnings("unchecked") E result = (E)elements[--size];
		elements[size] = null;
		return result;
	}

	private void ensureCapacity() {
		if (elements.length == size) {
			elements = Arrays.copyOf(elements, 2 * size + 1);
		}
	}

	public static void main(String[] args) {
		GenericStack2<String> stack = new GenericStack2<>();
		stack.push("one");
		stack.push("two");

		System.out.println(Arrays.toString(stack.elements));
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(Arrays.toString(stack.elements));
		System.out.println(stack.size);
	}
}
