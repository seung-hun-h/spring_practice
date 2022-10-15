package item30;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
	public static void main(String[] args) {
		List<Integer> numbers = new ArrayList<Integer>();

		for (int i = 0; i < 10; i++) {
			int number = new Random().nextInt(100);
			numbers.add(number);
		}

		Integer max = MyCollections.max(numbers);
		System.out.println("numbers = " + numbers);
		System.out.println("max = " + max);
	}
}
