package item26;

import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		List<String> strings = new ArrayList<>();
		unsafeAdd(strings, Integer.valueOf(42));
		String s = strings.get(0);
		System.out.println("s = " + s);
	}

	private static void unsafeAdd(List<Object> list, Integer value) {
		list.add(value);
	}
}
