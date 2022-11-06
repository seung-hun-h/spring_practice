package item33;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {
	public static void main(String[] args) {
		Favorites f = new Favorites();

		f.putFavorites(String.class, "Java");
		f.putFavorites(Integer.class, 0xcafebabe);
		f.putFavorites(Class.class, Favorites.class);

		String favoriteString = f.getFavorite(String.class);
		Integer favoriteInteger = f.getFavorite(Integer.class);
		Class<?> favoriteClass = f.getFavorite(Class.class);

		// System.out.printf("%s %x %s%n", favoriteString, favoriteInteger, favoriteClass.getName());

		Map<String, List<?>> helloMap = Map.of("hello", List.of("hello"));
		List<String> helloList = List.of("hello");
		List<List<String>> helloList2 = List.of(List.of("hello"));
		// hello(helloMap, helloList, helloList2);
	}

	public static void hello(Map<?, List<?>> map, List<?> list, List<List<?>> list2) {
		System.out.println("map = " + map);
		System.out.println("list = " + list);
		System.out.println("list2 = " + list2);
	}
}
