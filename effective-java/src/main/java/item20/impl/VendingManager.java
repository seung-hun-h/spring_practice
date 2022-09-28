package item20.impl;

public class VendingManager {
	public static void main(String[] args) {
		Invending candyInvending = new CandyInvending();
		Invending drinkInvending = new DrinkInvending();

		candyInvending.process();
		drinkInvending.process();
	}
}
