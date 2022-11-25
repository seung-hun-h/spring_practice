package item52;

import java.util.List;

public class Overriding {
	static class Wine {
		String name() {
			return "포토주";
		}
	}

	static class SparklingWine extends Wine {
		@Override
		String name() {
			return "발포성 포도주";
		}
	}

	static class Champagne extends Wine {
		@Override
		String name() {
			return "샴페인";
		}
	}

	public static void main(String[] args) {
		List<Wine> wines = List.of(new Wine(), new SparklingWine(), new Champagne());
		for (Wine wine : wines) {
			System.out.println(wine.name());
		}
	}
}
