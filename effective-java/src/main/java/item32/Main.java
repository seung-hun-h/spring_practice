package item32;

import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		dangerous(new ArrayList<>());
	}
	static void dangerous(List<String>... stringLists) {
		List<Integer> intList = List.of(42);
		Object[] objects = stringLists;
		objects[0] = intList;
		String s = stringLists[0].get(0);
	}
}
