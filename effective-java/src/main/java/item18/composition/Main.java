package item18.composition;

import java.util.HashSet;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		InstrumentSet<Integer> ints = new InstrumentSet<>(new HashSet<>());
		ints.addAll(List.of(1, 2, 3, 4, 5));

		System.out.println("ints = " + ints.getAddCount());
	}
}
