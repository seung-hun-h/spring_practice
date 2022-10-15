package item31.wildcard;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import item31.MyInteger;

public class Main {
	public static void main(String[] args) {
		List<MyInteger> numbers = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			int number = new Random().nextInt(100);
			numbers.add(new MyInteger(number));
		}

		MyInteger max = MyCollections.max(numbers);
		System.out.println("numbers = " + numbers);
		System.out.println("max = " + max);
	}
}
