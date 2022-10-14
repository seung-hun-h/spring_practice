package item28;

import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Chooser<T> {
	private final T[] choiceArray;

	public Chooser(Collection<T> choices) {
		this.choiceArray = (T[])choices.toArray();
	}

	public T choose() {
		Random rnd = ThreadLocalRandom.current();
		return choiceArray[rnd.nextInt(choiceArray.length)];
	}

	public static void main(String[] args) {
		List<String> strings = List.of("one", "two", "three", "four", "five", "six");
		Chooser<String> chooser = new Chooser<>(strings);
		String choose = chooser.choose();

		System.out.println("choose = " + choose);
	}
}
