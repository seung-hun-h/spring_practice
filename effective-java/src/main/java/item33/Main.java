package item33;

public class Main {
	public static void main(String[] args) {
		Favorites f = new Favorites();

		f.putFavorites(String.class, "Java");
		f.putFavorites(Integer.class, 0xcafebabe);
		f.putFavorites(Class.class, Favorites.class);

		String favoriteString = f.getFavorite(String.class);
		Integer favoriteInteger = f.getFavorite(Integer.class);
		Class<?> favoriteClass = f.getFavorite(Class.class);

		System.out.printf("%s %x %s%n", favoriteString, favoriteInteger, favoriteClass.getName());
	}
}
