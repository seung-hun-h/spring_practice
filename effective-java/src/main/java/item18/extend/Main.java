package item18.extend;

import java.util.List;

public class Main {
	public static void main(String[] args) {
		InstrumentHashSet<Integer> ints = new InstrumentHashSet<>();
		ints.addAll(List.of(1, 2, 3, 4, 5));

		System.out.println("ints = " + ints.getAddCount());
	}
}
