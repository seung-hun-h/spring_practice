package item02.builder;

public class Main {
	public static void main(String[] args) {
		NutritionFacts nutritionFacts = new NutritionFacts.Builder(1, 2)
															.carbohydrate(1)
															.calories(1)
															.fat(1)
															.sodium(1)
															.build();

		System.out.println("nutritionFacts = " + nutritionFacts);

		NyPizza ny = new NyPizza.Builder(NyPizza.Size.LARGE)
			.addTopping(Pizza.Topping.ONION)
			.addTopping(Pizza.Topping.PEPPER)
			.build();


		Calzone calzone = new Calzone.Builder()
			.addTopping(Pizza.Topping.SAUSAGE)
			.addTopping(Pizza.Topping.MUSHROOM)
			.build();

		System.out.println("calzone = " + calzone);
	}
}
